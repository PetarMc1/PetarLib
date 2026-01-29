package com.petarmc.lib.notification;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.sql.Struct;

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

    private static void showNotificationInChat(String msg) {
        Minecraft client = Minecraft.getInstance();
        assert client.player != null;
        client.player.displayClientMessage(Component.nullToEmpty(msg), false);
    }

    /**
     * Shows an error notification in the action bar.
     *
     * @param message the error message to show
     * @param prefix the prefix to show (e.g. "[MyMod]") - may be empty
     */
    public static void showError(String message, String prefix) {
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotification("§e[" + safePrefix + "] §c" + message);
    }

    /**
     * Shows an info notification in the action bar.
     *
     * @param message the message to show
     * @param prefix the prefix to show (e.g. "[MyMod]") - can be empty
     */
    public static void showInfo(String message, String prefix) {
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotification("§e[" + safePrefix + "] §f" + message);
    }

    /**
     * Shows a custom notification in the action bar.
     *
     * @param message the message to show (supports color codes like "§a")
     */
    public static void showCustomNotification(String message) {
        showNotification(message);
    }

    /**
     * Shows an info notification in chat.
     *
     * @param message the message to show in chat
     * @param prefix the prefix to add before the message (can be empty)
     */
    public static void showInfoInChat(String message, String prefix) {
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotificationInChat("§e[" + safePrefix + "] §f" + message);
    }

    /**
     * Shows an error notification in chat.
     *
     * @param message the error message to show in chat
     * @param prefix the prefix to add before the message (can be empty)
     */
    public static void showErrorInChat(String message, String prefix){
        String safePrefix = (prefix == null) ? "" : prefix.replaceAll("^\\[|\\]$", "");
        showNotificationInChat("§e[" + safePrefix + "] §c" + message);
    }

    /**
     * Shows a custom notification in chat
     *
     * @param message the message to show in chat (supports color codes)
     */
    public static void showCustomChatNotif(String message){
        showNotificationInChat(message);
    }
}
