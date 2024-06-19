package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Arrays;

/**
 * Listener that catches all commands that bukkit dont like to overwrite the conventional way.
 * @since 0.0.1
 */
public class CommandListener implements Listener {

    /**
     * Gets called when a player executes a command in the chat.
     * @param event PlayerCommandPreprocessEvent - the event.
     */
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        boolean success = processCommand(event.getPlayer(), event.getMessage().substring(1));
        event.setCancelled(success);
    }

    /**
     * Gets called when the console executes a command.
     * @param event ServerCommandEvent - the event.
     */
    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        boolean success = processCommand(event.getSender(), event.getCommand());
        event.setCancelled(success);
    }

    /**
     * Processing a command message for a given CommandSender and executing the APICommand.
     * @param sender CommandSender - the command sender.
     * @param commandMessage String - the message which contains the command.
     * @return Boolean - whatever the command was executed or not.
     */
    private boolean processCommand(CommandSender sender, String commandMessage) {
        String[] fullArguments = commandMessage.split(" ");
        String command = fullArguments[0];

        APICommand apiCommand = McSrvAPI.getInstance().getCommandHandler().getEventCommands().get(command);

        if (apiCommand == null) {
            return false;
        }

        apiCommand.execute(sender, command, Arrays.copyOfRange(fullArguments, 1, fullArguments.length));
        return true;
    }

}
