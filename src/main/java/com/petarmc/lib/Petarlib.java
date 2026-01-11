package com.petarmc.lib;

import com.petarmc.lib.log.PLog;
import com.petarmc.lib.log.PLogger;
import com.petarmc.lib.log.LogConfig;
import net.fabricmc.api.ClientModInitializer;

public class Petarlib implements ClientModInitializer {

    private static final PLogger log = new PLog("Petarlib");


    @Override
    public void onInitializeClient() {
        LogConfig.globalPrefix = "[PetarLib]";

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("PetarLib shutting down...");
        }));

        log.info("PetarLib initialized");
    }
}
