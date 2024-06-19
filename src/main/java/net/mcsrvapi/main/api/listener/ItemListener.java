package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listener to catch all items which are dropped or pickup.
 * @since 0.0.1
 */
public class ItemListener implements Listener {

    /**
     * Event gets called when an item is dropped by a player.
     * @param event PlayerDropItemEvent - the item drop event.
     * @since 0.0.1
     */
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.ITEM_DROP))
            return;

        event.setCancelled(true);
    }

    /**
     * Event gets called when an item got picked up by an entity.
     * @param event EntityPickupItemEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.ITEM_PICKUP))
            return;

        event.setCancelled(true);
    }

}
