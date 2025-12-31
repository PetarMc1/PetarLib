package com.petarmc.lib.log;

/**
 * Configuration settings for the logging system.
 * Allows controlling log levels, output format, and file logging options.
 */
public class LogConfig {

    /**
     * The global log level.
     * Messages below this level will be ignored if {@link #enforceLevel} is true.
     * Default is {@link LogLevel#INFO}.
     */
    public static LogLevel globalLevel = LogLevel.INFO;

    /**
     * If true, log messages will also be written to a file.
     * Default is false.
     */
    public static boolean logToFile = false;

    /**
     * The path of the log file when {@link #logToFile} is true.
     * Default is "petarlib.log".
     */
    public static String logFilePath = "petarlib.log";

    /**
     * A prefix to add to every log message, e.g., "[MyMod]".
     * Default is an empty string.
     */
    public static String globalPrefix = "";

    /**
     * If true, timestamps will be included in log messages.
     * Default is true.
     */
    public static boolean includeTimestamp = true;

    /**
     * If true, the current thread name will be included in log messages.
     * Default is false.
     */
    public static boolean includeThread = false;

    /**
     * If true, log messages will be filtered based on the global log level.
     * If false, all messages will be logged regardless of level.
     * Default is true.
     */
    public static boolean enforceLevel = true;
}
