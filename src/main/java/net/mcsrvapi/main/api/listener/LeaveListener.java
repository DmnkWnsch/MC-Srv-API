package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.event.packetreader.PlayerPacketReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener that gets called if a player quits the server
 * @since 0.0.1
 */
public class LeaveListener implements Listener {

    /**
     * Method is called when a player quits the server.
     * @param event PlayerQuitEvent - the quit event.
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        McSrvAPI.getInstance().getPlayerHandler().removePlayer(player.getUniqueId());

        // uninject packet reader
        PlayerPacketReader.uninject(player);
    }

}
