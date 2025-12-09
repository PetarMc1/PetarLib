package com.petarmc.lib.task;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;

import java.util.concurrent.*;

public class TaskScheduler {

    private static final PLogger log = new PLog("TaskScheduler");

    private final ScheduledExecutorService scheduler;

    public TaskScheduler(int threads) {
        scheduler = Executors.newScheduledThreadPool(Math.max(1, threads));
    }

    public void runAsync(Runnable r) {
        try {
            scheduler.submit(() -> {
                try {
                    r.run();
                } catch (Throwable t) {
                    log.error("TaskScheduler task threw: ", t);
                }
            });
        } catch (RejectedExecutionException e) {
            log.error("TaskScheduler.runAsync rejected: " + e.getMessage());
        }
    }

    public void runDelayed(Runnable r, long delayMs) {
        scheduler.schedule(() -> {
            try {
                r.run();
            } catch (Throwable t) {
                log.error("Delayed task threw: ", t);
            }
        }, delayMs, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        log.info("Shutting down TaskScheduler...");
        scheduler.shutdown();

        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                log.info("Forcing TaskScheduler shutdown...");
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        log.info("TaskScheduler stopped");
    }
}
