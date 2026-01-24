package com.petarmc.lib.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

/**
 * Utility class for sending chat messages from the client to the server.
 * This sends messages as if the player typed them (so they are visible to the server and other players).
 *
 * Use from client-only code (GUIs, keybind handlers, etc.).
 */
public final class PlayerChatSender {

    private static final int MAX_MESSAGE_LENGTH = 256;

    private PlayerChatSender() {}

    /**
     * Send a single chat message from the player to the server.
     * Returns true if the send was attempted, false if aborted.
     */
    public static boolean send(String message) {
        if (message == null) return false;
        String msg = message.trim();
        if (msg.isEmpty()) return false;
        if (msg.length() > MAX_MESSAGE_LENGTH) msg = msg.substring(0, MAX_MESSAGE_LENGTH);

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return false;

        final ClientPlayNetworkHandler handler = client.getNetworkHandler();
        if (handler == null) return false;

        try {
            final String toSend = msg;
            client.execute(() -> {
                try {
                    handler.sendChatMessage(toSend);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            });
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}
