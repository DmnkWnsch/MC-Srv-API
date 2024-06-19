package net.mcsrvapi.main.api.server;

import java.util.List;

/**
 * Utility class to send messages to all online players
 * @since 0.0.1
 */
public class BroadcastHandler {

    private BroadcastHandler() {
        throw new UnsupportedOperationException("BroadcastHandler is a utility class.");
    }

    /**
     * Sends a message to all online players.
     * @param messageKey String - the message to send.
     * @since 0.0.1
     */
    public static void broadcastMessage(String messageKey) {
        APIServer.getOnlinePlayers().forEach(apiPlayer -> apiPlayer.sendMessage(messageKey));
    }

    /**
     * Sends a message to all online players with replaced value.
     * @param messageKey String - the message to send.
     * @param placeholder String - the variable to be replaced.
     * @param replacement String - the replacement.
     * @since 0.0.1
     */
    public static void broadcastMessage(String messageKey, String placeholder, String replacement) {
        APIServer.getOnlinePlayers().forEach(apiPlayer -> apiPlayer.sendMessage(messageKey, placeholder, replacement));
    }

    /**
     * Sends a message to all online players with replaced values.
     * @param messageKey String - the message to send.
     * @param placeholders List<String> - the variables to replace.
     * @param replacements List<String> - the replacements.
     * @since 0.0.1
     */
    public static void broadcastMessage(String messageKey, List<String> placeholders, List<String> replacements) {
        APIServer.getOnlinePlayers().forEach(apiPlayer -> apiPlayer.sendMessage(messageKey, placeholders, replacements));
    }

}
