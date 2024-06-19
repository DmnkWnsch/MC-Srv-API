package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import net.mcsrvapi.main.api.server.ServerSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Listener to check if damage is dealed.
 * @since 0.0.1
 */
public class DamageListener implements Listener {

    /**
     * Event gets called when an entity gets damage.
     * @param event EntityDamageEvent - the damage event.
     * @since 0.0.1
     */
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        ServerSettings serverSettings = McSrvAPI.getInstance().getServerSettings();
        if (serverSettings.isEnabled(ServerSetting.ENTITY_DAMAGE))
            return;

        if (serverSettings.isEnabled(ServerSetting.FALL_DAMAGE) &&
                event.getCause() == EntityDamageEvent.DamageCause.FALL)
            return;

        event.setCancelled(true);
    }

    /**
     * Event gets called when an entity gets damage by an other entity.
     * @param event EntityDamageByEntityEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player))
            return;

        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.PVP))
            return;

        event.setCancelled(true);
    }

}
