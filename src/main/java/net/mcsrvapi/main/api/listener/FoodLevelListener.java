package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Listener to check if the food level of a player has changed.
 * @since 0.0.1
 */
public class FoodLevelListener implements Listener {

    /**
     * Event gets called when the food level of a player changes.
     * @param event FoodLevelChangeEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.FOOD_LEVEL_CHANGE));
    }

}
