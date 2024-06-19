package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.util.ConsoleUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command to change the build mode for a player.
 * @since 0.0.1
 */
public class BuildCommand extends APICommand {

    private static final String VAR_PLAYER = "${player}";
    private static final String VAR_BUILD_MODE = "buildMode";

    /**
     * Creates the command.
     * @since 0.0.1
     */
    public BuildCommand() {
        super ("build", "command.build", TranslationKeys.API_COMMAND_BUILD_USAGE,
                TranslationKeys.API_COMMAND_BUILD_DESCRIPTION);
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length > 1) {
            sendCommandUsage(apiPlayer);
            return;
        }

        if (args.length == 1) {
            if (!apiPlayer.hasPermission(getPermission() + ".others")) {
                apiPlayer.sendMessage(TranslationKeys.API_NO_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                apiPlayer.sendMessage(TranslationKeys.API_PLAYER_NOT_FOUND);
                return;
            }

            APIPlayer targetPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(target);
            boolean newState = targetPlayer.switchCustomBooleanData(VAR_BUILD_MODE);
            if (newState) {
                targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_ENABLED);
                apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_ENABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
            } else {
                targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_DISABLED);
                apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_DISABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
            }

            return;
        }

        boolean newState = apiPlayer.switchCustomBooleanData(VAR_BUILD_MODE);
        String messageKey = TranslationKeys.API_MESSAGE_BUILD_ENABLED;
        if (!newState) {
            messageKey = TranslationKeys.API_MESSAGE_BUILD_DISABLED;
        }

        apiPlayer.sendMessage(messageKey);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {
        if (args.length != 1) {
            sendCommandUsage(commandSender);
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_PLAYER_NOT_FOUND);
            return;
        }

        APIPlayer targetPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(target);
        boolean newState = targetPlayer.switchCustomBooleanData(VAR_BUILD_MODE);
        if (newState) {
            targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_ENABLED);
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_MESSAGE_BUILD_ENABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
        } else {
            targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_BUILD_DISABLED);
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_MESSAGE_BUILD_DISABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
        }
    }

}
