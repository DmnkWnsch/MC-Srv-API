package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Listener to check if a item is consumed by a player.
 * @since 0.0.1
 */
public class ItemConsumeListener implements Listener {

    /**
     * Event gets called when a player consumes an item.
     * @param event PlayerItemConsumeEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {

        if (!McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.ITEM_CONSUME))
            event.setCancelled(true);

    }

}
