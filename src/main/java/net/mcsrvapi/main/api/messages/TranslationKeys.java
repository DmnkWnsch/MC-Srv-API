package net.mcsrvapi.main.api.messages;

/**
 * Just a collection of all used translation keys to minimize code duplications.
 * @since 0.0.1
 */
public class TranslationKeys {

    private TranslationKeys() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    //GENERAL
    public static final String API_DISABLED_COMMAND = "api.command.disabled";
    public static final String API_COMMAND_USAGE = "api.command.usage";
    public static final String API_NO_PERMISSION = "api.message.nopermission";
    public static final String API_CHAT_DISABLED = "api.message.chat.currentlydisabled";
    public static final String API_PLAYER_NOT_FOUND = "api.message.player.notfound";
    public static final String API_PLAYER_MONEY_GET = "api.message.player.moneyget";
    public static final String API_PLAYER_MONEY_REMOVE = "api.message.player.moneyremove";
    public static final String API_COMMAND_PREMIUM_ONLY = "api.message.command.premiumonly";

    //StopCommand
    public static final String API_COMMAND_STOP_USAGE = "api.command.stop.usage";
    public static final String API_COMMAND_STOP_DESCRIPTION = "api.command.stop.description";

    //ClearChatCommand
    public static final String API_COMMAND_CLEAR_CHAT_USAGE = "api.command.clear_chat.usage";
    public static final String API_COMMAND_CLEAR_CHAT_DESCRIPTION = "api.command.clear_chat.description";
    public static final String API_MESSAGE_CLEAR_CHAT = "api.message.clear_chat";

    //GameModeCommand
    public static final String API_COMMAND_GAMEMODE_USAGE = "api.command.gamemode.usage";
    public static final String API_COMMAND_GAMEMODE_DESCRIPTION = "api.command.gamemode.description";
    public static final String API_MESSAGE_GAMEMODE_NOT_FOUND = "api.message.gamemode.not_found";
    public static final String API_MESSAGE_GAMEMODE_CHANGED = "api.message.gamemode.changed";
    public static final String API_MESSAGE_GAMEMODE_CHANGED_OTHERS = "api.message.gamemode.changedothers";

    //GlobalMuteCommand
    public static final String API_COMMAND_GLOBALMUTE_USAGE = "api.command.globalmute.usage";
    public static final String API_COMMAND_GLOBALMUTE_DESCRIPTION = "api.command.globalmute.description";
    public static final String API_MESSAGE_CHAT_DISABLED = "api.message.chat.disabled";
    public static final String API_MESSAGE_CHAT_ENABLED = "api.message.chat.enabled";

    //BuildCommand
    public static final String API_COMMAND_BUILD_USAGE = "api.command.build.usage";
    public static final String API_COMMAND_BUILD_DESCRIPTION = "api.command.build.description";
    public static final String API_MESSAGE_BUILD_ENABLED = "api.message.build.enabled";
    public static final String API_MESSAGE_BUILD_ENABLED_OTHERS = "api.message.build.enabledothers";
    public static final String API_MESSAGE_BUILD_DISABLED = "api.message.build.disabled";
    public static final String API_MESSAGE_BUILD_DISABLED_OTHERS = "api.message.build.disabledothers";

    //FlyCommand
    public static final String API_COMMAND_FLY_USAGE = "api.command.fly.usage";
    public static final String API_COMMAND_FLY_DESCRIPTION = "api.command.fly.description";
    public static final String API_MESSAGE_FLY_ENABLED = "api.message.fly.enabled";
    public static final String API_MESSAGE_FLY_DISABLED = "api.message.fly.disabled";
    public static final String API_MESSAGE_FLY_ENABLED_OTHERS = "api.message.fly.enabledothers";
    public static final String API_MESSAGE_FLY_DISABLED_OTHERS = "api.messages.fly.disabledothers";

}
