package com.petarmc.petarlib.chat;

/**
 * Functional interface for handling exact chat message matches.
 */
@FunctionalInterface
public interface ExactMatchHandler {
    /**
     * Called when a chat message exactly matches a registered string.
     * @param message The exact message that was matched
     * @param matchId The ID of the match that triggered
     */
    void onExactMatch(String message, String matchId);
}

