package com.petarmc.petarlib.chat;

import java.util.regex.Matcher;

/**
 * Represents a successful pattern match from a chat message.
 */
public class ChatMatch {
    private final String originalMessage;
    private final Matcher matcher;
    private final String patternId;

    public ChatMatch(String originalMessage, Matcher matcher, String patternId) {
        this.originalMessage = originalMessage;
        this.matcher = matcher;
        this.patternId = patternId;
    }

    /**
     * @return The original chat message that was matched
     */
    public String getOriginalMessage() {
        return originalMessage;
    }

    /**
     * @return The regex matcher containing captured groups
     */
    public Matcher getMatcher() {
        return matcher;
    }

    /**
     * @return The ID of the pattern that matched
     */
    public String getPatternId() {
        return patternId;
    }

    /**
     * Get a captured group by index
     * @param group The group index (1-based)
     * @return The captured string, or null if group doesn't exist
     */
    public String getGroup(int group) {
        try {
            return matcher.group(group);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get the number of captured groups
     */
    public int getGroupCount() {
        return matcher.groupCount();
    }
}

