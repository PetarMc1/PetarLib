package com.petarmc.lib.log;

import java.io.FileWriter;
import java.io.IOException;

public class PLog implements PLogger {

    private final String name;

    public PLog(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    private String format(String level, String msg) {
        StringBuilder sb = new StringBuilder();

        if (LogConfig.globalPrefix != null && !LogConfig.globalPrefix.isEmpty()) {
            sb.append(LogConfig.globalPrefix).append(" ");
        }

        // Log level
        sb.append("[").append(level).append("] ");

        // Thread info
        if (LogConfig.includeThread) {
            sb.append("[Thread: ").append(Thread.currentThread().getName()).append("] ");
        }

        // Class/Logger name
        sb.append("[").append(name).append("] ");

        // Actual message
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
        return level.ordinal() >= LogConfig.globalLevel.ordinal();
    }

    @Override
    public void debug(String msg) {
        if (!allowed(LogLevel.DEBUG)) return;
        String line = format("DEBUG", msg);
        System.out.println(line);
        writeToFile(line);
    }

    @Override
    public void info(String msg) {
        if (!allowed(LogLevel.INFO)) return;
        String line = format("INFO", msg);
        System.out.println(line);
        writeToFile(line);
    }

    @Override
    public void warn(String msg) {
        if (!allowed(LogLevel.WARN)) return;
        String line = format("WARN", msg);
        System.out.println(line);
        writeToFile(line);
    }

    @Override
    public void error(String msg) {
        if (!allowed(LogLevel.ERROR)) return;
        String line = format("ERROR", msg);
        System.err.println(line);
        writeToFile(line);
    }

    public void error(String msg, Throwable t) {
        if (!allowed(LogLevel.ERROR)) return;
        String line = format("ERROR", msg + " :: " + t.getMessage());
        System.err.println(line);
        t.printStackTrace();
        writeToFile(line + "\n" + t.toString());
    }
}
