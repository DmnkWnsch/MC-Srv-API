package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.util.ConsoleUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Command to block stopping of servers in game.
 * @since 0.0.1
 */
public class StopCommand extends APICommand {

    /**
     * Initializes the stop command.
     * @since 0.0.1
     */
    public StopCommand() {
        super ("stop", "command.stop",
                TranslationKeys.API_COMMAND_STOP_USAGE, TranslationKeys.API_COMMAND_STOP_DESCRIPTION);
        setRunAsEvent(true);
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        apiPlayer.sendMessage(TranslationKeys.API_DISABLED_COMMAND);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {
        ConsoleUtil.sendConsoleMessage(TranslationKeys.API_DISABLED_COMMAND);

        //WILL BE REMOVED LATER - JUST FOR TESTING
        Bukkit.getServer().shutdown();
    }

}
