package com.petarmc.lib.net;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.*;

/**
 * A simple wrapper around Java's HttpClient with support for retries and asycn execution.
 */
public class HttpClientWrapper {

    private static final PLogger log = new PLog("HttpClientWrapper");

    private final HttpClient client;
    private final int maxRetries;
    private final ExecutorService executor;

    /**
     * Creates a new HttpClientWrapper with a specified maximum number of retries.
     *
     * @param maxRetries maximum number of attempts for failed reqs, minimum 1
     */
    public HttpClientWrapper(int maxRetries) {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.executor = Executors.newCachedThreadPool();
        this.maxRetries = Math.max(1, maxRetries);
    }

    /**
     * Sends an async GET request to the specified URL.
     *
     * @param url the target URL
     * @return a CompletableFuture containing the response body
     */
    public CompletableFuture<String> get(String url) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(20))
                .build();
        return sendWithRetry(req, 1);
    }

    /**
     * Sends an async POST request using the provided HttpRequest.
     *
     * @param request the HttpRequest to send
     * @return a CompletableFuture containing the response body
     */
    public CompletableFuture<String> post(HttpRequest request) {
        return sendWithRetry(request, 1);
    }

    private CompletableFuture<String> sendWithRetry(HttpRequest req, int attempt) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.debug("HttpClient attempt " + attempt + " → " + req.uri());
                HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                return resp.body();
            } catch (Exception e) {
                if (attempt >= maxRetries) {
                    log.error("HttpClient failed after retries: " + e.getMessage());
                    throw new CompletionException(e);
                }
                log.warn("HttpClient attempt " + attempt + " failed: " + e.getMessage());
                return sendWithRetry(req, attempt + 1).join();
            }
        }, executor);
    }

    /**
     * Shuts down the executor used for async HTTP requests.
     */
    public void shutdown() {
        log.info("Shutting down HttpClient executor");
        executor.shutdownNow();
    }
}
