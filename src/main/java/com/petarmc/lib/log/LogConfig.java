package com.petarmc.lib.log;

public class LogConfig {

    public static LogLevel globalLevel = LogLevel.INFO;

    public static boolean logToFile = false;
    public static String logFilePath = "petarlib.log";
    public static String globalPrefix = "";
    public static boolean includeTimestamp = true;
    public static boolean includeThread = false;
}
