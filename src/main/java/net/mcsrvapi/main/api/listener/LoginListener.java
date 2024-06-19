package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.event.packetreader.PlayerPacketReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Listener that gets called if a player logs into the server.
 * @since 0.0.1
 */
public class LoginListener implements Listener {

    /**
     * Method gets called when a player logs in.
     *
     * @param event PlayerLoginEvent - the login event.
     */
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        McSrvAPI.getInstance().getPlayerHandler().addPlayer(player);

        // inject the packet reader
        new PlayerPacketReader(player);
    }

}
