package com.petarmc.lib.task;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;

import java.util.concurrent.*;

/**
 * Schedules and executes tasks async or with a delay.
 * Provides safe handling of exceptions and logging.
 */
public class TaskScheduler {

    private static final PLogger log = new PLog("TaskScheduler");
    private final ScheduledExecutorService scheduler;

    /**
     * Creates a TaskScheduler with a fixed number of threads.
     *
     * @param threads the number of threads for asynchronous tasks; minimum 1
     */
    public TaskScheduler(int threads) {
        scheduler = Executors.newScheduledThreadPool(Math.max(1, threads));
    }

    /**
     * Runs a task asynchronously.
     * Exceptions thrown by the task are caught and logged.
     *
     * @param r the Runnable task to execute
     */
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

    /**
     * Runs a task after a specified delay in ms.
     * Exceptions thrown by the task are caught and logged.
     *
     * @param r       the runnable task to execute
     * @param delayMs the delay in ms before executing the task
     */
    public void runDelayed(Runnable r, long delayMs) {
        scheduler.schedule(() -> {
            try {
                r.run();
            } catch (Throwable t) {
                log.error("Delayed task threw: ", t);
            }
        }, delayMs, TimeUnit.MILLISECONDS);
    }

    /**
     * Shuts down the scheduler, waiting up to 5 seconds
     * before forcing shutdown. Logs shutdown progress.
     */
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
