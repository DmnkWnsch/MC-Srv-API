package net.mcsrvapi.main.api.messages;

import net.mcsrvapi.main.api.common.messages.MessageList;
import net.mcsrvapi.misc.translationhandler.TranslationHandler;

/**
 * List of all default english messages
 * @since 0.0.1
 */
public class EnglishMessages extends MessageList {

    /**
     * Creates the message list and adds it the the english language
     * @since 0.0.1
     */
    public EnglishMessages() {
        super (TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("en"));

        // General
        add(TranslationKeys.API_DISABLED_COMMAND, "&cThis command is disabled.");
        add(TranslationKeys.API_COMMAND_USAGE, "&7Please use &a/${command}.");
        add(TranslationKeys.API_NO_PERMISSION, "&cYou dont have permission to do this.");
        add(TranslationKeys.API_PLAYER_NOT_FOUND, "&cThis player could not be found.");
        add(TranslationKeys.API_PLAYER_MONEY_GET, "e${amount} &7Tokens was &aadded &7to your balance!");
        add(TranslationKeys.API_PLAYER_MONEY_REMOVE, "&e${amount} &7Tokens was &cremoved &7from your balance!");
        add(TranslationKeys.API_COMMAND_PREMIUM_ONLY, "&7You need the &6premium &7rank to use this command.");

        // StopCommand
        add(TranslationKeys.API_COMMAND_STOP_USAGE, "stop");
        add(TranslationKeys.API_COMMAND_STOP_DESCRIPTION, "Stops the server");

        // ClearChatCommand
        add(TranslationKeys.API_COMMAND_CLEAR_CHAT_USAGE, "clearchat");
        add(TranslationKeys.API_COMMAND_CLEAR_CHAT_DESCRIPTION, "Clears the chat for players without permission.");
        add(TranslationKeys.API_MESSAGE_CLEAR_CHAT, "&7The chat has been cleared.");

        // GameModeCommand
        add(TranslationKeys.API_COMMAND_GAMEMODE_USAGE, "gamemode <gamemode> [player]");
        add(TranslationKeys.API_COMMAND_STOP_DESCRIPTION, "Changes the gamemode");
        add(TranslationKeys.API_MESSAGE_GAMEMODE_NOT_FOUND, "&cThis gamemode could not be found.");
        add(TranslationKeys.API_MESSAGE_GAMEMODE_CHANGED, "&7Your gamemode has been changed to &a${gamemode}.");
        add(TranslationKeys.API_MESSAGE_GAMEMODE_CHANGED_OTHERS, "&7The gamemode of ${player} &7has been changed to &a${gamemode}.");

        // GlobalmuteCommand
        add(TranslationKeys.API_COMMAND_GLOBALMUTE_USAGE, "globalmute");
        add(TranslationKeys.API_COMMAND_GLOBALMUTE_DESCRIPTION, "Changes the state of the globalmute.");
        add(TranslationKeys.API_MESSAGE_CHAT_ENABLED, "&7The chat has been &aenabled.");
        add(TranslationKeys.API_MESSAGE_CHAT_DISABLED, "&7The chat has been &cdisabled.");

        //BuildCommand
        add(TranslationKeys.API_COMMAND_BUILD_USAGE, "build [player]");
        add(TranslationKeys.API_COMMAND_BUILD_DESCRIPTION, "Changes the state of a players build mode.");
        add(TranslationKeys.API_MESSAGE_BUILD_ENABLED, "&7Your build mode has been &aenabled.");
        add(TranslationKeys.API_MESSAGE_BUILD_DISABLED, "&7Your build mode has been &cdisabled.");
        add(TranslationKeys.API_MESSAGE_BUILD_ENABLED_OTHERS, "&7The build mode of ${player} &7has been &aenabled.");
        add(TranslationKeys.API_MESSAGE_BUILD_DISABLED_OTHERS, "&7The build mode of ${player} &7has been &cdisabled.");

        //FlyCommand
        add(TranslationKeys.API_COMMAND_FLY_USAGE, "fly [player]");
        add(TranslationKeys.API_COMMAND_FLY_DESCRIPTION, "Changes the fly mode of a player.");
        add(TranslationKeys.API_MESSAGE_FLY_ENABLED, "&7You can fly now!");
        add(TranslationKeys.API_MESSAGE_FLY_DISABLED, "&7You cannot fly anymore!");
        add(TranslationKeys.API_MESSAGE_FLY_ENABLED_OTHERS, "&7The fly mode of ${player} &7has been &aenabled.");
        add(TranslationKeys.API_MESSAGE_FLY_DISABLED_OTHERS, "&7The fly mode of ${player} &7has been &cdisabled.");

        insertIntoLanguage();
    }

}
