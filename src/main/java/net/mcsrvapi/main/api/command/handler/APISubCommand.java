package net.mcsrvapi.main.api.command.handler;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to create a sub command for an APICommand
 * @since 0.0.1
 */
public abstract class APISubCommand {

    private final String name;
    private final String description;
    private final String permission;
    private final String usage;
    private final int minimumArguments;

    private final Map<String, APISubCommand> subCommandMap;

    /**
     * Creates an instance of the api sub command.
     * @param name String - the sub command name.
     * @param permission String - the sub command permission.
     * @param usage String - the usage of the sub command.
     * @param description String - the description of the subcommand.
     * @param minimumArguments int - the minimum arguments needed to perform the sub command.
     * @since 0.0.1
     */
    public APISubCommand(String name, String permission, String usage, String description, int minimumArguments) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
        this.description = description;
        this.minimumArguments = minimumArguments;
        this.subCommandMap = new HashMap<>();
    }

    /**
     * Creates an instance of the api sub command.
     * @param name String - the sub command name.
     * @param permission String - the sub command permission.
     * @param usage String - the usage of the sub command.
     * @param description String - the description of the subcommand.
     * @since 0.0.1
     */
    public APISubCommand(String name, String permission, String usage, String description) {
        this(name, permission, usage, description, 0);
    }

    /**
     * Creates an instance of the api sub command.
     * @param name String - the sub command name.
     * @param usage String - the usage of the sub command.
     * @param description String - the description of the subcommand.
     * @param minimumArguments int - the minimum arguments needed to perform the sub command.
     * @since 0.0.1
     */
    public APISubCommand(String name, String usage, String description, int minimumArguments) {
        this(name, null, usage, description, minimumArguments);
    }

    /**
     * Creates an instance of the api sub command.
     * @param name String - the sub command name.
     * @param usage String - the usage of the sub command.
     * @param description String - the description of the subcommand.
     * @since 0.0.1
     */
    public APISubCommand(String name, String usage, String description) {
        this(name, null, usage, description, 0);
    }

    /**
     * Executes a sub command called by a command sender.
     * @param commandSender CommandSender - the sender of the command.
     * @param arguments String[] - the given arguments.
     * @since 0.0.1
     */
    public void execute(CommandSender commandSender, String[] arguments) {

        if (!subCommandMap.isEmpty() && arguments.length > 0) {
            String firstArg = arguments[0];
            APISubCommand subCommand = subCommandMap.get(firstArg);
            if (subCommand != null) {
                subCommand.execute(commandSender, arguments);
                return;
            }
        }

        if (commandSender instanceof Player) {
            APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer((Player) commandSender);
            executePlayer(apiPlayer, arguments);
        }

        if (arguments.length < minimumArguments) {
            return;
        }

        onConsole(commandSender, arguments);
    }

    /**
     * Executes the sub command as a player.
     * @param apiPlayer {@link APIPlayer} - the executor.
     * @param arguments String[] the given arguments.
     * @since 0.0.1
     */
    private void executePlayer(APIPlayer apiPlayer, String[] arguments) {
        if (hasPermission() && !apiPlayer.hasPermission(permission)) {
            apiPlayer.sendMessage(TranslationKeys.API_NO_PERMISSION);
            return;
        }

        if (arguments.length < minimumArguments) {
            sendCommandUsage(apiPlayer);
            return;
        }

        onCommand(apiPlayer, arguments);
    }

    /**
     * Method that gets called if a subcommand is executed by a player.
     * @param apiPlayer {@link APIPlayer} - the executor.
     * @param arguments String[] - the command arguments.
     * @since 0.0.1
     */
    public abstract void onCommand(APIPlayer apiPlayer, String[] arguments);

    /**
     * Method that gets called if a subcommand is executed by the console.
     * @param commandSender CommandSender - the executor.
     * @param arguments String[] - the command arguments.
     * @since 0.0.1
     */
    public abstract void onConsole(CommandSender commandSender, String[] arguments);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMinimumArguments() {
        return minimumArguments;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage() {
        return usage;
    }

    /**
     * Checks whatever the sub command has a permission set or not.
     * @return boolean - whatever a permission is set.
     */
    public boolean hasPermission() {
        return permission != null;
    }

    /**
     * Adds a sub command to the sub command to chain commands.
     * @param subCommand {@link APISubCommand} - the sub command.
     * @since 0.0.1
     */
    public void addSubCommand(APISubCommand subCommand) {
        subCommandMap.put(subCommand.getName(), subCommand);
    }

    /**
     * Sends the correct usage of the command to a specified player.
     * @param player {@link APIPlayer} - the player to send the usage to.
     * @since 0.0.1
     */
    public void sendCommandUsage(APIPlayer player) {
        String commandUsage = player.getLanguage().getTranslation(getUsage());
        player.sendMessage(TranslationKeys.API_COMMAND_USAGE, "${command}", commandUsage);
    }
}
