package net.mcsrvapi.main.api.command.handler;

import net.mcsrvapi.main.api.McSrvAPI;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Handler for the APICommands
 * @since 0.0.1
 */
public class CommandHandler {

    private final Map<String, APICommand> commandMap;
    private final Map<String, APICommand> eventCommands;

    /**
     * Creates an instance of the command handler.
     * @since 0.0.1
     */
    public CommandHandler() {
        this.commandMap = new HashMap<>();
        this.eventCommands = new HashMap<>();
    }

    /**
     * Adds a list of APICommands to the CommandHandler
     * @param apiCommand {@link net.mcsrvapi.main.api.command.handler.APICommand}... - List of Commands
     * @since 0.0.1
     */
    public void addCommands(APICommand... apiCommand) {
        addCommands(Arrays.asList(apiCommand));
    }

    /**
     * Adds a list of APICommands to the CommandHandler.
     * @param commandList List of {@link net.mcsrvapi.main.api.command.handler.APICommand} - the list of commands.
     * @since 0.0.1
     */
    public void addCommands(List<APICommand> commandList) {
        for (APICommand command : commandList) {
            commandMap.put(command.getCommand(), command);
            registerCommand(command);
        }
    }

    /**
     * Returns a Map of all Commands which are registered
     * @see APICommand
     * @return Map<String, APICommand>
     * @since 0.0.1
     */
    public Map<String, APICommand> getCommandMap() {
        return commandMap;
    }

    /**
     * Returns a Map of all commands which are called as an event
     * @see APICommand
     * @return Map<String, APICommand>
     * @since 0.0.1
     */
    public Map<String, APICommand> getEventCommands() {
        return eventCommands;
    }

    /**
     * Disables a command for default usage
     * Can be bypassed with command.disabled.bypass permission
     * @param commandName String - the command to disable
     * @since 0.0.1
     */
    public void disableCommand(String commandName) {
        if (commandMap.containsKey(commandName)) {
            commandMap.get(commandName).setEnabled(false);
        } else {
            McSrvAPI.getInstance().getLogger().log(Level.WARNING, "Could not disable command {0}: command not found.", commandName);
        }
    }

    /**
     * Registers a command when added to CommandHandler
     */
    private void registerCommand(APICommand command) {
        if (command.isRunAsEvent()) {
            eventCommands.put(command.getCommand(), command);
            McSrvAPI.getInstance().getLogger().info("Registered command: " + command.getCommand() + " as event.");
            return;
        }

        CraftServer craftServer = (CraftServer) McSrvAPI.getInstance().getServer();
        SimpleCommandMap simpleCommandMap = craftServer.getCommandMap();

        try {
            simpleCommandMap.register(command.getCommand(), command);
            McSrvAPI.getInstance().getLogger().info("Registered command: " + command.getCommand());
        } catch (Exception exception) {
            McSrvAPI.getInstance().getLogger().warning("Failed to register command " + command.getCommand() + ": " + exception.getMessage());
        }
    }

}
