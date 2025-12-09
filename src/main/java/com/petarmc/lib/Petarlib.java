package com.petarmc.lib;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;

public class Petarlib {

    private static final PLogger log = new PLog("Petarlib");

    public static void init() {
        log.info("PetarLib v1.0.0 initializing");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("PetarLib shutdown hook running...");
        }));

        log.info("PetarLib initialized");
    }
}
