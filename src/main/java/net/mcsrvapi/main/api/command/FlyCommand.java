package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.util.ConsoleUtil;
import org.bukkit.command.CommandSender;

public class FlyCommand extends APICommand {

    private static final String VAR_FLYMODE = "flyMode";
    private static final String VAR_PLAYER = "${player}";

    public FlyCommand() {
        super ("fly", "command.fly", TranslationKeys.API_COMMAND_FLY_USAGE,
                TranslationKeys.API_COMMAND_FLY_DESCRIPTION);
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

            APIPlayer targetPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(args[0]);
            if (targetPlayer == null) {
                apiPlayer.sendMessage(TranslationKeys.API_PLAYER_NOT_FOUND);
                return;
            }

            boolean newState = !targetPlayer.getPlayer().getAllowFlight();
            targetPlayer.getPlayer().setAllowFlight(newState);
            targetPlayer.setCustomData(VAR_FLYMODE, newState);
            if (newState) {
                targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_ENABLED);
                apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_ENABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
            } else {
                targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_DISABLED);
                apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_DISABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
            }

            return;
        }

        boolean newState = !apiPlayer.getPlayer().getAllowFlight();
        apiPlayer.setCustomData(VAR_FLYMODE, newState);
        String messageKey = (newState ? TranslationKeys.API_MESSAGE_FLY_ENABLED : TranslationKeys.API_MESSAGE_FLY_DISABLED);
        apiPlayer.getPlayer().setAllowFlight(newState);
        apiPlayer.sendMessage(messageKey);

    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {

        if (args.length != 1) {
            sendCommandUsage(commandSender);
            return;
        }

        APIPlayer targetPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(args[0]);
        if (targetPlayer == null) {
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_PLAYER_NOT_FOUND);
            return;
        }

        boolean newState = !targetPlayer.getPlayer().getAllowFlight();
        targetPlayer.getPlayer().setAllowFlight(newState);
        targetPlayer.setCustomData(VAR_FLYMODE, newState);
        if (newState) {
            targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_ENABLED);
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_MESSAGE_FLY_ENABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
        } else {
            targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_FLY_DISABLED);
            ConsoleUtil.sendConsoleMessage(TranslationKeys.API_MESSAGE_FLY_DISABLED_OTHERS, VAR_PLAYER, targetPlayer.getDisplayName());
        }

    }
}
