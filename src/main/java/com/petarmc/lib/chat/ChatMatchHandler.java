package com.petarmc.lib.chat;

/**
 * Functional interface for handling chat pattern matches.
 */
@FunctionalInterface
public interface ChatMatchHandler {
    /**
     * Called when a chat message matches a registered pattern.
     * @param match The match data containing the original message and captured groups
     */
    void onMatch(ChatMatch match);
}

