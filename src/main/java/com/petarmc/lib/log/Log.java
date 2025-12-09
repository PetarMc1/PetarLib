package com.petarmc.lib.log;

public class Log {

    private static PLogger injected = null;

    public static void inject(PLogger logger) {
        injected = logger;
    }

    public static PLogger get(Class<?> clazz) {
        if (injected != null)
            return injected;

        return new PLog(clazz.getSimpleName());
    }

    public static PLogger get(String name) {
        if (injected != null)
            return injected;

        return new PLog(name);
    }
}
