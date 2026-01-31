package com.petarmc.petarlib.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * PerformanceLog implementation that logs messages to the console and optionally to a file.
 * Designed to track debug, info, warning, and error messages with optional thread information.
 *
 */
public class PLog implements PLogger {

    private final String name;
    private final String prefix;

    /**
     * Creates a new PerformanceLog instance with the given name and an explicit prefix.
     * Per-logger prefixes are required; there is no global prefix.
     *
     * @param name   the name of the logger
     * @param prefix the prefix to apply to this logger's messages (e.g. "[MyMod]")
     */
    public PLog(String name, String prefix) {
        this.name = name;
        this.prefix = prefix == null ? "" : prefix;
    }

    /**
     * Returns the name of this logger.
     *
     * @return the logger name
     */
    @Override
    public String getName() {
        return name;
    }

    private String format(String level, String msg) {
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(level).append("] ");

        if (prefix != null && !prefix.isEmpty()) {
            sb.append(prefix).append(" ");
        }

        if (LogConfig.includeThread) {
            sb.append("[").append(Thread.currentThread().getName()).append("] ");
        }

        sb.append(msg);

        return sb.toString();
    }

    private void writeToFile(String line) {
        if (!LogConfig.logToFile) return;
        try (FileWriter fw = new FileWriter(LogConfig.logFilePath, true)) {
            fw.write(line + "\n");
        } catch (IOException ignored) {}
    }

    private boolean allowed(LogLevel level) {
        return !LogConfig.enforceLevel || level.ordinal() >= LogConfig.globalLevel.ordinal();
    }

    /**
     * Logs a debug-level message.
     *
     * @param msg the message to log
     */
    @Override
    public void debug(String msg) {
        if (!allowed(LogLevel.DEBUG)) return;
        String line = format("DEBUG", msg);
        System.out.println(line);
        writeToFile(line);
    }

    /**
     * Logs an info-level message.
     * Ignored if {@link LogConfig#enforceLevel} is true and the global log level is higher than INFO.
     *
     * @param msg the message to log
     */
    @Override
    public void info(String msg) {
        if (!allowed(LogLevel.INFO)) return;
        String line = format("INFO", msg);
        System.out.println(line);
        writeToFile(line);
    }

    /**
     * Logs a warning-level message.
     *
     * @param msg the message to log
     */
    @Override
    public void warn(String msg) {
        if (!allowed(LogLevel.WARN)) return;
        String line = format("WARN", msg);
        System.out.println(line);
        writeToFile(line);
    }

    /**
     * Logs an error-level message.
     *
     * @param msg the message to log
     */
    @Override
    public void error(String msg) {
        if (!allowed(LogLevel.ERROR)) return;
        String line = format("ERROR", msg);
        System.err.println(line);
        writeToFile(line);
    }

    /**
     * Logs an error-level message along with a Throwable.
     * Prints the exception's stack trace and message.
     *
     * @param msg the message to log
     * @param t   the Throwable to include in the log
     */
    public void error(String msg, Throwable t) {
        if (!allowed(LogLevel.ERROR)) return;
        String line = format("ERROR", msg + " :: " + t.getMessage());
        System.err.println(line);
        t.printStackTrace(System.err);
        try {
            try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
                t.printStackTrace(pw);
                writeToFile(line + "\n" + sw.toString());
            }
        } catch (IOException ignored) {}
    }
}
