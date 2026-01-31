package com.petarmc.petarlib;

import com.petarmc.petarlib.log.PLog;
import com.petarmc.petarlib.log.PLogger;
import net.fabricmc.api.ClientModInitializer;

public class Petarlib implements ClientModInitializer {

    private static final PLogger log = new PLog("Petarlib", "[PetarLib]");


    @Override
    public void onInitializeClient() {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("PetarLib shutting down...");
        }));

        log.info("PetarLib initialized");
    }
}
