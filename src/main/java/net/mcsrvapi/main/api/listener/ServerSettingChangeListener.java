package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.event.ServerSettingChangeEvent;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Listener to catch all changed server settings.
 * @since 0.0.1
 */
public class ServerSettingChangeListener implements Listener {

    /**
     * Event gets called when a server setting was changed.
     * @param event {@link ServerSettingChangeEvent} - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onServerSettingChange(ServerSettingChangeEvent event) {
        if (event.getServerSetting() == ServerSetting.TIME_CHANGE) {
            Bukkit.getWorlds().forEach(world ->
                    world.setGameRuleValue("doDaylightCycle", String.valueOf(event.getNewState())));
        }
    }

}
