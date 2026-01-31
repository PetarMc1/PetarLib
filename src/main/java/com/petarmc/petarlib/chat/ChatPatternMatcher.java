package com.petarmc.petarlib.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A reusable chat pattern matching system that allows registration of regex patterns
 * and exact string matches with associated handlers.
 */
public class ChatPatternMatcher {

    private final List<PatternEntry> patterns = new ArrayList<>();
    private final Map<String, ExactMatchHandler> exactMatches = new HashMap<>();

    /**
     * Register a regex pattern with a handler.
     * @param patternId Unique identifier for this pattern
     * @param pattern The regex pattern to match
     * @param handler The handler to call when a match is found
     */
    public void registerPattern(String patternId, Pattern pattern, ChatMatchHandler handler) {
        patterns.add(new PatternEntry(patternId, pattern, handler));
    }

    /**
     * Register a regex pattern with a handler.
     * @param patternId Unique identifier for this pattern
     * @param regex The regex string to compile and match
     * @param handler The handler to call when a match is found
     */
    public void registerPattern(String patternId, String regex, ChatMatchHandler handler) {
        registerPattern(patternId, Pattern.compile(regex), handler);
    }

    /**
     * Register a regex pattern with flags.
     * @param patternId Unique identifier for this pattern
     * @param regex The regex string to compile and match
     * @param flags Pattern compilation flags (e.g., Pattern.CASE_INSENSITIVE)
     * @param handler The handler to call when a match is found
     */
    public void registerPattern(String patternId, String regex, int flags, ChatMatchHandler handler) {
        registerPattern(patternId, Pattern.compile(regex, flags), handler);
    }

    /**
     * Register an exact string match with a handler.
     * @param matchId Unique identifier for this exact match
     * @param exactMessage The exact message to match
     * @param handler The handler to call when the exact message is found
     */
    public void registerExactMatch(String matchId, String exactMessage, ExactMatchHandler handler) {
        exactMatches.put(exactMessage, handler);
    }

    /**
     * Process a chat message against all registered patterns and exact matches.
     * @param message The chat message to process
     */
    public void processMessage(String message) {
        if (message == null || message.isEmpty()) {
            return;
        }

        // Check exact matches first
        ExactMatchHandler exactHandler = exactMatches.get(message);
        if (exactHandler != null) {
            exactHandler.onExactMatch(message, message);
        }

        // Check regex patterns
        for (PatternEntry entry : patterns) {
            Matcher matcher = entry.pattern.matcher(message);
            while (matcher.find()) {
                ChatMatch match = new ChatMatch(message, matcher, entry.patternId);
                entry.handler.onMatch(match);
            }
        }
    }

    /**
     * Remove all registered patterns and exact matches.
     */
    public void clear() {
        patterns.clear();
        exactMatches.clear();
    }

    /**
     * Remove a specific pattern by ID.
     * @param patternId The ID of the pattern to remove
     * @return true if a pattern was removed
     */
    public boolean removePattern(String patternId) {
        return patterns.removeIf(entry -> entry.patternId.equals(patternId));
    }

    /**
     * Remove a specific exact match.
     * @param exactMessage The exact message to remove
     * @return true if an exact match was removed
     */
    public boolean removeExactMatch(String exactMessage) {
        return exactMatches.remove(exactMessage) != null;
    }

    private static class PatternEntry {
        final String patternId;
        final Pattern pattern;
        final ChatMatchHandler handler;

        PatternEntry(String patternId, Pattern pattern, ChatMatchHandler handler) {
            this.patternId = patternId;
            this.pattern = pattern;
            this.handler = handler;
        }
    }
}

