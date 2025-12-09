package com.petarmc.lib.net;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public final class HttpResponse {
    public final int statusCode;
    public final String body;
    public final Map<String, List<String>> headers;

    public HttpResponse(int statusCode, String body, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body == null ? "" : body;
        this.headers = headers == null ? Collections.emptyMap() : Collections.unmodifiableMap(headers);
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", bodyLen=" + (body == null ? 0 : body.length()) +
                '}';
    }
}
