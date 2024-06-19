package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

/**
 * Listener to catch when a vehicle has been destroyed.
 * @since 0.0.1
 */
public class VehicleDestroyListener implements Listener {

    /**
     * Event gets called whenever a vehicle gets destroyed.
     * @param event VehicleDestroyEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        if (!McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.VEHICLE_DESTROY))
            event.setCancelled(true);
    }

}
