package com.petarmc.lib.net;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.*;

public class HttpClientWrapper {

    private static final PLogger log = new PLog("HttpClientWrapper");

    private final HttpClient client;
    private final int maxRetries;
    private final ExecutorService executor;

    public HttpClientWrapper(int maxRetries) {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.executor = Executors.newCachedThreadPool();
        this.maxRetries = Math.max(1, maxRetries);
    }


    public CompletableFuture<String> get(String url) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(20))
                .build();
        return sendWithRetry(req, 1);
    }


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

    public void shutdown() {
        log.info("Shutting down HttpClient executor");
        executor.shutdownNow();
    }
}
