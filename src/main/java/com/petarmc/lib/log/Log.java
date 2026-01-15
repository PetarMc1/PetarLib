package com.petarmc.lib.log;

/**
 * Utility class for obtaining logger instances.
 * Supports injecting a custom {@link PLogger} implementation or creating default {@link PLog} instances.
 */
public class Log {

    private static PLogger injected = null;

    /**
     * Injects a custom logger to be returned by all {@link #get} calls.
     *
     * @param logger the logger to inject
     */
    public static void inject(PLogger logger) {
        injected = logger;
    }

    /**
     * Returns a logger instance for the specified class with a  prefix.
     *
     * @param clazz  the class for which the logger is requested
     * @param prefix the prefix to apply to this logger's messages
     * @return a logger instance
     */
    public static PLogger get(Class<?> clazz, String prefix) {
        if (injected != null)
            return injected;

        return new PLog(clazz.getSimpleName(), prefix);
    }

    /**
     * Returns a logger instance with the specified name and prefix.
     *
     * @param name   the name of the logger
     * @param prefix the prefix to apply to this logger's messages
     * @return a logger instance
     */
    public static PLogger get(String name, String prefix) {
        if (injected != null)
            return injected;

        return new PLog(name, prefix);
    }
}
