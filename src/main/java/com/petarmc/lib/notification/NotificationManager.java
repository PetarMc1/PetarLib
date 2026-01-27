package com.petarmc.lib.notification;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/**
 * Utility class for showing in-game notifications to the player through the action bar.
 * Only works on the client side.
 *
 * This class no longer relies on a global log prefix. Callers must supply a prefix
 * when showing notifications (e.g. "[MyMod]").
 */
public class NotificationManager {

    private static void showNotification(String msg) {
        Minecraft client = Minecraft.getInstance();
        assert client.player != null;
        client.player.displayClientMessage(Component.nullToEmpty(msg), true);
    }

    /**
     * Shows an error notification in chat.
     *
     * @param message the error message to show
     * @param prefix the prefix to show (e.g. "[MyMod]") - may be empty
     */
    public static void showError(String message, String prefix) {
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotification("§e[" + safePrefix + "] §c" + message);
    }

    /**
     * Shows an info notification in chat.
     *
     * @param message the message to show
     * @param prefix the prefix to show (e.g. "[MyMod]") - can be empty
     */
    public static void showInfo(String message, String prefix) {
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotification("§e[" + safePrefix + "] §f" + message);
    }

    /**
     * Shows a custom notification in chat.
     *
     * @param message the message to show (supports color codes like "§a")
     */
    public static void showCustomNotification(String message) {
        showNotification(message);
    }
}
