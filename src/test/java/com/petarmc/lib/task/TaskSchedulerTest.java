package com.petarmc.lib.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSchedulerTest {

    private TaskScheduler scheduler = new TaskScheduler(1);

    @AfterEach
    void tearDown() {
        scheduler.shutdown();
    }

    @Test
    void runAsyncExecutesRunnable() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        scheduler.runAsync(latch::countDown);
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void runDelayedExecutesAfterDelay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        scheduler.runDelayed(latch::countDown, 200);
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 150, "Should wait approximately the delay");
    }

    @Test
    void exceptionsInTasksDoNotPropagate() throws InterruptedException {
        AtomicBoolean ran = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        scheduler.runAsync(() -> {
            ran.set(true);
            latch.countDown();
            throw new RuntimeException("boom");
        });
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(ran.get());
    }
}

