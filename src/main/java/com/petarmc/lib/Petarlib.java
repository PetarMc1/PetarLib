package com.petarmc.lib;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;
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
