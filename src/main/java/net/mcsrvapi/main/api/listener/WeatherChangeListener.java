package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Listener to catch when the weather has been changed.
 * @since 0.0.1
 */
public class WeatherChangeListener implements Listener {

    /**
     * Event gets called when the weather in a world has changed.
     * @param event WeatherChangeEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.WEATHER_CHANGE))
            return;

        event.setCancelled(event.toWeatherState());
    }

}
