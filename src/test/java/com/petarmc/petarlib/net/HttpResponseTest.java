package com.petarmc.petarlib.net;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseTest {

    @Test
    void constructorHandlesNullsAndIsSuccess() {
        HttpResponse r1 = new HttpResponse(200, null, null);
        assertEquals(200, r1.statusCode);
        assertEquals("", r1.body);
        assertNotNull(r1.headers);

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("x", Collections.singletonList("v"));
        HttpResponse r2 = new HttpResponse(404, "not found", headers);
        assertEquals(404, r2.statusCode);
        assertEquals("not found", r2.body);
        assertEquals(1, r2.headers.size());

        assertTrue(new HttpResponse(250, "ok", null).isSuccess());
        assertFalse(new HttpResponse(199, "no", null).isSuccess());
        assertFalse(new HttpResponse(300, "no", null).isSuccess());
    }

    @Test
    void toStringContainsStatusAndBodyLen() {
        HttpResponse r = new HttpResponse(201, "abc", null);
        String s = r.toString();
        assertTrue(s.contains("statusCode=201"));
        assertTrue(s.contains("bodyLen=3"));
    }
}

