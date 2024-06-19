package net.mcsrvapi.main.api.util;

import net.mcsrvapi.main.api.McSrvAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

/**
 * Class to send messages with or without translations to the console.
 * @since 0.0.1
 */
public class ConsoleUtil {

    /**
     * This class is a util class, so it's not needed to instantiate it.
     */
    private ConsoleUtil() {
        throw new UnsupportedOperationException("This is a util class.");
    }

    /**
     * Sends a raw message to the console.
     * @param message String - the message to send.
     * @since 0.0.1
     */
    public static void sendRawConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
    }

    /**
     * Sends a translated console message in the api language.
     * @param messageKey String - the name of the message.
     * @since 0.0.1
     */
    public static void sendConsoleMessage(String messageKey) {
        String message = McSrvAPI.getInstance().getAPILanguage().getTranslation(messageKey);
        Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
    }

    /**
     * Sends a message to the console in the api language with replacements.
     * @param messageKey String - the message name.
     * @param placeholder String - the string which should be replaced.
     * @param replacement String - the replacement.
     * @since 0.0.1
     */
    public static void sendConsoleMessage(String messageKey, String placeholder, String replacement) {
        String message = McSrvAPI.getInstance().getAPILanguage().getTranslation(messageKey, placeholder, replacement);
        Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
    }

    /**
     * Sends a message to the console in the api language with replacements.
     * @param messageKey String - the message name.
     * @param placeholders List<String> - the string which should be replaced.
     * @param replacements List<String> - the replacement.
     * @since 0.0.1
     */
    public static void sendConsoleMessage(String messageKey, List<String> placeholders, List<String> replacements) {
        String message = McSrvAPI.getInstance().getAPILanguage().getTranslation(messageKey, placeholders, replacements);
        Bukkit.getConsoleSender().sendMessage(ChatColor.stripColor(message));
    }

}
