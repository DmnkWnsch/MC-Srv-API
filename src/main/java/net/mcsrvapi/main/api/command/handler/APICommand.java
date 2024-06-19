package net.mcsrvapi.main.api.command.handler;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.misc.translationhandler.translations.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * A command class for custom commands
 * @since 0.0.1
 */
public abstract class APICommand extends Command {

    private final String command;
    private final String permission;
    private final String desc;
    private final String usage;

    private boolean enabled;
    private boolean runAsEvent;

    private final List<String> commandAliases;
    private final Map<String, APISubCommand> subCommandMap;

    /**
     * Creates the api command object with all needed values to function.
     * @param command String - the command name.
     * @param permission String - the permission to run the command.
     * @param usage String - the correct command usage.
     * @param description String - the command description.
     * @param aliases List<String> - the aliases for the command.
     * @since 0.0.1
     */
    public APICommand(String command, String permission, String usage, String description, String... aliases) {
        super(command, description, usage, Arrays.asList(aliases));
        this.command = command;
        this.permission = permission;
        this.usage = usage;
        this.desc = description;
        this.commandAliases = Arrays.asList(aliases);
        this.enabled = true;
        this.runAsEvent = false;

        this.subCommandMap = new HashMap<>();
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {

        if (commandSender instanceof Player) {
            Player executor = (Player) commandSender;
            if (!isEnabled() && !executor.hasPermission("command.disabled.bypass")) {
                APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(executor);
                apiPlayer.sendMessage(TranslationKeys.API_DISABLED_COMMAND);
                return true;
            }
        }

        if (checkSubCommand(commandSender, args))
            return true;

        if (commandSender instanceof Player) {
            Player executor = (Player) commandSender;
            executePlayerCommand(executor, args);
            return true;
        }

        onConsole(commandSender, args);

        return true;
    }

    /**
     * Checks sub commands and executes them if one is found.
     * @param commandSender CommandSender - the sender of the command.
     * @param args String[] - the arguments.
     * @return boolean - whatever a subcommand has been executed.
     * @since 0.0.1
     */
    private boolean checkSubCommand(CommandSender commandSender, String[] args) {
        if (args.length == 0 || subCommandMap.isEmpty())
            return false;

        String subCommandName = args[0];
        APISubCommand subCommand = subCommandMap.get(subCommandName);

        if (subCommand == null)
            return false;

        subCommand.execute(commandSender, getNewArguments(args));

        return true;
    }

    /**
     * Executes a command for a player and checks the permissions.
     * @param player Player - the player to perform
     * @param args String[] - the arguments which were used.
     * @since 0.0.1
     */
    private void executePlayerCommand(Player player, String[] args) {
        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(player);

        if (permission.length() > 0 && !player.hasPermission(permission)) {
            sendNoPermissionMessage(apiPlayer);
            return;
        }

        onCommand(apiPlayer, args);
    }

    /**
     * Returns a list of all registered subcommands.
     * @return Collection ({@link APISubCommand}).
     * @since 0.0.1
     */
    public Collection<APISubCommand> getSubCommandList() {
        return subCommandMap.values();
    }

    /**
     * Gets fired when a player executed a command and has permissions to do so.
     * @param apiPlayer {@link APIPlayer} - the player who executed the command.
     * @param args String[] - the arguments which were used.
     * @since 0.0.1
     */
    public abstract void onCommand(APIPlayer apiPlayer, String[] args);

    /**
     * Gets fired when the console executes a command.
     * @param commandSender CommandSender - the command sender.
     * @param args String[] - the arguments used.
     * @since 0.0.1
     */
    public abstract void onConsole(CommandSender commandSender, String[] args);

    /**
     * Returns a new array of Strings with command arguments, cuts the start of oldArguments.
     * @param oldArguments String[] - the old arguments to cut.
     * @return String[] - the new arguments.
     * @since 0.0.1
     */
    private String[] getNewArguments(String[] oldArguments) {

        if (oldArguments.length <= 1) {
            return new String[] {};
        }

        return Arrays.copyOfRange(oldArguments, 1, oldArguments.length);
    }

    /**
     * Adds a APISubCommand to the command.
     * @param subCommand {@link APISubCommand} - the subcommand to add.
     * @since 0.0.1
     */
    public void addSubCommand(APISubCommand subCommand) {
        subCommandMap.put(subCommand.getName(), subCommand);
    }

    /**
     * Returns whatever the command is enabled for normal usage or not.
     * @return boolean
     * @since 0.0.1
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled state of the command.
     * @param enabled boolean - the state to set.
     * @since 0.0.1
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns if the command is run as an event.
     * @return boolean - whatever the command runs as event or not.
     */
    public boolean isRunAsEvent() {
        return runAsEvent;
    }

    /**
     * Sets if the command should run as event.
     * @param runAsEvent boolean
     */
    public void setRunAsEvent(boolean runAsEvent) {
        this.runAsEvent = runAsEvent;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getCommandAliases() {
        return commandAliases;
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

    /**
     * Sends the correct usage of the command to a command sender.
     * @param commandSender CommandSender - the sender to send the usage to.
     * @since 0.0.1
     */
    public void sendCommandUsage(CommandSender commandSender) {
        Language apiLanguage = McSrvAPI.getInstance().getAPILanguage();
        String commandUsage = apiLanguage.getTranslation(getUsage());
        String message = apiLanguage.getTranslation(TranslationKeys.API_COMMAND_USAGE,"${command}", commandUsage);
        commandSender.sendMessage(message);
    }

    /**
     * Sends a no permission message to the player.
     * Could be overwritten to send a custom message like different content
     * for players without permission.
     * @param apiPlayer {@link APIPlayer} - the player to sent to.
     * @since 0.0.1
     */
    public void sendNoPermissionMessage(APIPlayer apiPlayer) {
        apiPlayer.sendMessage(TranslationKeys.API_NO_PERMISSION);
    }
}
