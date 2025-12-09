package com.petarmc.lib.log;

public interface PLogger {
    String getName();
    void debug(String msg);
    void info(String msg);
    void warn(String msg);
    void error(String msg);
    void error(String msg, Throwable t);
}
