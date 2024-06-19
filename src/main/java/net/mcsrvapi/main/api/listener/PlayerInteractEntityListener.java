package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Listener that catches whenever a player interacts with an entity.
 * @since 0.0.1
 */
public class PlayerInteractEntityListener implements Listener {

    /**
     * Event gets called whenever a player interacts with an entity.
     * @param event PlayerInteractEntityEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        if (!McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.PLAYER_INTERACT_ENTITY))
            event.setCancelled(true);

    }

}
