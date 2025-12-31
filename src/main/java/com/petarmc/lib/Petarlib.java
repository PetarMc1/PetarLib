package com.petarmc.lib;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;
import net.fabricmc.api.ClientModInitializer;

public class Petarlib implements ClientModInitializer {

    private static final PLogger log = new PLog("Petarlib");

    @Override
    public void onInitializeClient() {
        log.info("PetarLib initializing");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("PetarLib shutdown hook running...");
        }));

        log.info("PetarLib initialized");
    }
}
