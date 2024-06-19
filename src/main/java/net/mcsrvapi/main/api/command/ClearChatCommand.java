package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.server.APIServer;
import net.mcsrvapi.main.api.server.BroadcastHandler;
import org.bukkit.command.CommandSender;

/**
 * Command to clear the chat for all players without the permission
 * @since 0.0.1
 */
public class ClearChatCommand extends APICommand {

    /**
     * Initializes the clear chat command
     * @since 0.0.1
     */
    public ClearChatCommand() {
        super ("clearchat", "command.clearchat",
                TranslationKeys.API_COMMAND_CLEAR_CHAT_USAGE,
                TranslationKeys.API_COMMAND_CLEAR_CHAT_DESCRIPTION,
                "cc");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        clearChat();
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {
        clearChat();
    }

    /**
     * Clears the chat for all online players without clear chat permission
     */
    private void clearChat() {
        APIServer.getOnlinePlayers().stream().filter(apiPlayer -> apiPlayer.hasPermission(getPermission()))
                .forEach(apiPlayer -> apiPlayer.sendEmptyMessage(100));
        BroadcastHandler.broadcastMessage(TranslationKeys.API_MESSAGE_CLEAR_CHAT);
    }

}
