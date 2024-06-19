package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Listeners to overview all creatues which are spawned.
 * @since 0.0.1
 */
public class CreatureSpawnListener implements Listener {

    /**
     * Event gets called when a creature was spawned.
     * @param event CreatureSpawnEvent - the creature spawn event.
     * @since 0.0.1
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)
            return;

        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.CREATURE_SPAWN))
            return;

        event.setCancelled(true);
    }

}
