package com.petarmc.petarlib.log;

/**
 * Represents the different log levels supported by the logging system.
 * The log levels are ordered from most verbose to least verbose.
 */
public enum LogLevel {

    /**
     * Debug-level messages, intended for detailed developer debugging.
     */
    DEBUG,

    /**
     * Info-level messages, for general informational purposes.
     */
    INFO,

    /**
     * Warning-level messages, indicating a potential problem.
     */
    WARN,

    /**
     * Error-level messages, indicating a failure or critical issue.
     */
    ERROR,

    /**
     * No logging. All messages are suppressed.
     */
    NONE
}
