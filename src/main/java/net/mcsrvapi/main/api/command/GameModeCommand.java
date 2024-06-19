package net.mcsrvapi.main.api.command;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Command to change the game mode of players.
 * @since 0.0.1
 */
public class GameModeCommand extends APICommand {

    private static final String PH_GAMEMODE = "${gamemode}";

    /**
     * Initializes the game mode command.
     *
     * @since 0.0.1
     */
    public GameModeCommand() {
        super("gamemode", "command.gamemode",
                TranslationKeys.API_COMMAND_GAMEMODE_USAGE, TranslationKeys.API_COMMAND_GAMEMODE_DESCRIPTION,
                "gm");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length == 0 || args.length > 2) {
            sendCommandUsage(apiPlayer);
            return;
        }

        GameMode gameMode = getGameMode(args[0]);
        if (gameMode == null) {
            apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_GAMEMODE_NOT_FOUND);
            return;
        }

        if (args.length == 1) {
            apiPlayer.getPlayer().setGameMode(gameMode);
            apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_GAMEMODE_CHANGED, PH_GAMEMODE, gameMode.name().toLowerCase());
            return;
        }

        if (!apiPlayer.hasPermission(getPermission() + ".other")) {
            sendNoPermissionMessage(apiPlayer);
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            apiPlayer.sendMessage(TranslationKeys.API_PLAYER_NOT_FOUND);
            return;
        }

        APIPlayer targetPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(target);
        targetPlayer.getPlayer().setGameMode(gameMode);
        targetPlayer.sendMessage(TranslationKeys.API_MESSAGE_GAMEMODE_CHANGED, PH_GAMEMODE, gameMode.name().toLowerCase());
        apiPlayer.sendMessage(TranslationKeys.API_MESSAGE_GAMEMODE_CHANGED_OTHERS, Arrays.asList("${player}", PH_GAMEMODE),
                Arrays.asList(targetPlayer.getDisplayName(), gameMode.name().toLowerCase()));
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] args) {
        //Currently not used
    }

    private GameMode getGameMode(String name) {
        GameMode result = null;

        Map<String, GameMode> idModes = new HashMap<>();
        idModes.put("0", GameMode.SURVIVAL);
        idModes.put("1", GameMode.CREATIVE);
        idModes.put("2", GameMode.ADVENTURE);
        idModes.put("3", GameMode.SPECTATOR);

        if (idModes.containsKey(name)) {
            result = idModes.get(name);
        } else {
            for (GameMode gameMode : GameMode.values()) {
                if (gameMode.name().equalsIgnoreCase(name)) {
                    result = gameMode;
                    break;
                }
            }
        }

        return result;
    }

}
