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
     * Returns a logger instance for the specified class.
     * If a logger has been injected via {@link #inject}, it returns the injected logger.
     * Otherwise, it returns a new {@link PLog} named after the class.
     *
     * @param clazz the class for which the logger is requested
     * @return a logger instance
     */
    public static PLogger get(Class<?> clazz) {
        if (injected != null)
            return injected;

        return new PLog(clazz.getSimpleName());
    }

    /**
     * Returns a logger instance with the specified name.
     * If a logger has been injected via {@link #inject}, it returns the injected logger.
     * Otherwise, it returns a new {@link PLog} with the given name.
     *
     * @param name the name of the logger
     * @return a logger instance
     */
    public static PLogger get(String name) {
        if (injected != null)
            return injected;

        return new PLog(name);
    }
}
