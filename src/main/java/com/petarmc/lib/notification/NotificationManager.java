package com.petarmc.lib.notification;

import com.petarmc.lib.log.LogConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

/**
 * Utility class for showing in-game notifications to the player.
 * Only works on the client side.
 */
public class NotificationManager {

    /**
     * Shows an error notification in chat.
     *
     * @param message the message to display
     */
    public static void showError(String message) {
        String prefix = (LogConfig.globalPrefix != null && !LogConfig.globalPrefix.isEmpty()) ? LogConfig.globalPrefix.replaceAll("^\\[|\\]$", "") : "";
        showChatMessage("§e[" + prefix + "] §c" + message);
    }

    /**
     * Shows an info notification in chat.
     *
     * @param message the message to display
     */
    public static void showInfo(String message) {
        String prefix = (LogConfig.globalPrefix != null && !LogConfig.globalPrefix.isEmpty()) ? LogConfig.globalPrefix.replaceAll("^\\[|\\]$", "") : "";
        showChatMessage("§e[" + prefix + "] §f" + message);
    }

    /**
     * Shows a custom notification in chat.
     *
     * @param message the message to display
     */
    public static void showChatMessage(String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.inGameHud != null) {
            client.inGameHud.getChatHud().addMessage(Text.of(message));
        }
    }
}
