package net.mcsrvapi.main.api.inventory;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.player.APIPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Listener to watch for clicked and closed inventories.
 * @since 0.0.1
 */
public class InventoryListener implements Listener {

    private static final String INVENTORY_KEY = "openInv";

    /**
     * Event gets fired whenever an inventory was clicked.
     * @param event InventoryClickEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getSlot() < 0 || event.getRawSlot() < 0)
            return;

        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer((Player) event.getWhoClicked());
        CustomInventory customInventory = (CustomInventory) apiPlayer.getCustomData(INVENTORY_KEY);
        if (customInventory == null)
            return;

        if (!event.getInventory().equals(customInventory.getInventory()))
            return;

        int clickedSlot = event.getSlot();
        ClickAction clickAction = customInventory.getClickAction(clickedSlot);
        if (clickAction == null)
            return;

        if (event.isRightClick()) {
            if (event.isShiftClick()) {
                clickAction.onShiftRightClick(apiPlayer, event.getInventory().getItem(clickedSlot), clickedSlot);
            } else {
                clickAction.onRightClick(apiPlayer, event.getInventory().getItem(clickedSlot), clickedSlot);
            }
        } else {
            if (event.isShiftClick()) {
                clickAction.onShiftClick(apiPlayer, event.getInventory().getItem(clickedSlot), clickedSlot);
            } else {
                clickAction.onClick(apiPlayer, event.getInventory().getItem(clickedSlot), clickedSlot);
            }
        }
        Sound clickSound = clickAction.getClickSound();
        if (clickSound != null)
            apiPlayer.playSound(clickSound);

        if (clickAction.isAutoClose())
            apiPlayer.getPlayer().closeInventory();
    }

    /**
     * Event gets fired whenever an inventory was closed.
     * @param event InventoryCloseEvent - the event.
     * @since 0.0.1
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer((Player) event.getPlayer());
        CustomInventory customInventory = (CustomInventory) apiPlayer.getCustomData(INVENTORY_KEY);
        if (customInventory == null)
            return;

        apiPlayer.removeCustomData(INVENTORY_KEY);
    }

}
