package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.event.AsyncAPITickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Listeners which catches an API tick every second.
 * @since 0.0.1
 */
public class APITickListener implements Listener {

    /**
     * Event gets called every second by the api scheduler.
     * @param event {@link AsyncAPITickEvent} - the tick event.
     * @since 0.0.1
     */
    @EventHandler
    public void onAPITick(AsyncAPITickEvent event) {
        if (McSrvAPI.getInstance().getActionbarManager().runsAutomatically()) {
            McSrvAPI.getInstance().getActionbarManager().update();
        }
    }

}
