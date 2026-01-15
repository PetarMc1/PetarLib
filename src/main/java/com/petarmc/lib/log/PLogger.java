package com.petarmc.lib.log;

/**
 * Interface for a simple logger with multiple log levels.
 */
public interface PLogger {

    /**
     * Returns the name of this logger, typically representing
     * the source or context of the log messages.
     *
     * @return the logger name
     */
    String getName();

    /**
     * Logs a debug-level message.
     *
     * @param msg the message to log
     */
    void debug(String msg);

    /**
     * Logs an info-level msg.
     *
     * @param msg the message to log
     */
    void info(String msg);

    /**
     * Logs a warning-level message.
     *
     * @param msg the message to log
     */
    void warn(String msg);

    /**
     * Logs an error-level message.
     * Indicates a failure.
     *
     * @param msg the message to log
     */
    void error(String msg);

    /**
     * Logs an error-level message along with a Throwable.
     *
     * @param msg the message to log
     * @param t   the Throwable to include in the log
     */
    void error(String msg, Throwable t);
}
