package net.mcsrvapi.main.api.server;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.player.APIPlayer;

import java.util.Collection;

/**
 * Utility class to interact with the players and the server.
 * @since 0.0.1
 */
public class APIServer {

    private APIServer() {
        throw new UnsupportedOperationException("APIServer is a utility class.");
    }

    /**
     * Gets all online players as APIPlayer.
     * @see APIPlayer
     * @return Collection<APIPlayer>.
     * @since 0.0.1
     */
    public static Collection<APIPlayer> getOnlinePlayers() {
        return McSrvAPI.getInstance().getPlayerHandler().getOnlinePlayers();
    }

}
