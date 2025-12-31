package com.petarmc.lib.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

/**
 * Utility class for showing in-game notifications (toasts) to the player.
 * Only works on the client side.
 */
public class NotificationManager {

    /**
     * Shows an error notification toast.
     *
     * @param title the title of the notification
     * @param message the message to display
     */
    public static void showError(String title, String message) {
        showToast(SystemToast.Type.WORLD_ACCESS_FAILURE, title, message);
    }

    /**
     * Shows an info notification toast.
     *
     * @param title the title of the notification
     * @param message the message to display
     */
    public static void showInfo(String title, String message) {
        showToast(SystemToast.Type.PACK_COPY_FAILURE, title, message);
    }

    /**
     * Shows a custom notification toast.
     *
     * @param type the type of toast
     * @param title the title of the notification
     * @param message the message to display
     */
    public static void showToast(SystemToast.Type type, String title, String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            client.getToastManager().add(new SystemToast(type, Text.of(title), Text.of(message)));
        }
    }

    /**
     * Shows a simple popup with custom data.
     * Uses a generic toast type.
     *
     * @param title the title
     * @param message the message
     */
    public static void showPopup(String title, String message) {
        showInfo(title, message); // Default to info for custom popups
    }
}
