package com.petarmc.petarlib.chat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ChatPatternMatcherTest {

    private final ChatPatternMatcher matcher = new ChatPatternMatcher();

    @AfterEach
    void tearDown() {
        matcher.clear();
    }

    @Test
    void regexMatchesCallHandler() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        matcher.registerPattern("p1", "hello (\\w+)", match -> {
            assertEquals("hello world", match.getOriginalMessage());
            assertEquals("p1", match.getPatternId());
            assertEquals("world", match.getGroup(1));
            latch.countDown();
        });

        matcher.processMessage("hello world");
        assertTrue(latch.await(500, TimeUnit.MILLISECONDS), "Handler should be called for regex match");
    }

    @Test
    void exactMatchesCallHandler() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        matcher.registerExactMatch("m1", "ping", (id, msg) -> {
            assertEquals("ping", id);
            assertEquals("ping", msg);
            latch.countDown();
        });

        matcher.processMessage("ping");
        assertTrue(latch.await(500, TimeUnit.MILLISECONDS), "Exact match handler should be called");
    }

    @Test
    void removeAndClearBehaviors() {
        matcher.registerPattern("p2", "foo", match -> fail("Should not be called after removal"));
        matcher.removePattern("p2");

        matcher.registerExactMatch("m2", "bar", (id, msg) -> fail("Should not be called after clear"));
        matcher.clear();

        // Should not throw and should not call handlers
        matcher.processMessage("foo");
        matcher.processMessage("bar");
    }
}
