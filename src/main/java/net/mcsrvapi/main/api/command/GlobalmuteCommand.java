package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.server.BroadcastHandler;
import net.mcsrvapi.main.api.server.ServerSetting;
import net.mcsrvapi.main.api.server.ServerSettings;
import org.bukkit.command.CommandSender;

/**
 * Command to en- or disable the chat for players without permission
 * @since 0.0.1
 */
public class GlobalmuteCommand extends APICommand {

    /**
     * Creates the command.
     * @since 0.0.1
     */
    public GlobalmuteCommand() {
        super ("globalmute", "command.globalmute", TranslationKeys.API_COMMAND_GLOBALMUTE_USAGE,
                TranslationKeys.API_COMMAND_GLOBALMUTE_DESCRIPTION, "gmute");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        changeChatState();
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {
        changeChatState();
    }

    private void changeChatState() {
        ServerSettings serverSettings = McSrvAPI.getInstance().getServerSettings();
        boolean newState = !serverSettings.isEnabled(ServerSetting.CHAT);
        serverSettings.set(ServerSetting.CHAT, newState);

        if (newState) {
            BroadcastHandler.broadcastMessage(TranslationKeys.API_MESSAGE_CHAT_ENABLED);
        } else {
            BroadcastHandler.broadcastMessage(TranslationKeys.API_MESSAGE_CHAT_DISABLED);
        }
    }

}
