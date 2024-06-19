package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Listener to check all block breaks and placements.
 * @since 0.0.1
 */
public class BlockListener implements Listener {

    /**
     * Event gets called whenever a block was broken.
     * @param event BlockBreakEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.BLOCK_BREAK))
            return;

        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(event.getPlayer());
        if (apiPlayer.hasCustomData("buildMode"))
            return;

        event.setCancelled(true);
    }

    /**
     * Event gets called whenever a block was placed.
     * @param event BlockPlaceEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.BLOCK_PLACE))
            return;

        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(event.getPlayer());
        if (apiPlayer.hasCustomData("buildMode"))
            return;

        event.setCancelled(true);
    }

}
