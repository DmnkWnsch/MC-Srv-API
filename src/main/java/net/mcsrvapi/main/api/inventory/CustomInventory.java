package net.mcsrvapi.main.api.inventory;

import net.mcsrvapi.main.api.player.APIPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to create custom inventories with click actions for items.
 * @since 0.0.1
 */
public class CustomInventory {

    private final Inventory inventory;

    private final Map<Integer, ClickAction> clickActions;

    /**
     * Creates an instance of the custom inventory.
     * @param title String - the title to show.
     * @param size int - the inventory size.
     * @since 0.0.1
     */
    public CustomInventory(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
        this.clickActions = new HashMap<>();
    }

    /**
     * Creates an instance of the custom inventory.
     * @param inventoryType InventoryType - the type of the inventory.
     * @param title String - the title to show.
     * @since 0.0.1
     */
    public CustomInventory(InventoryType inventoryType, String title) {
        this.inventory = Bukkit.createInventory(null, inventoryType, title);
        this.clickActions = new HashMap<>();
    }

    /**
     * Creates an instance of the custom inventory.
     * @param inventoryType InventoryType - the type of the inventory.
     * @since 0.0.1
     */
    public CustomInventory(InventoryType inventoryType) {
        this.inventory = Bukkit.createInventory(null, inventoryType);
        this.clickActions = new HashMap<>();
    }

    /**
     * Creates an instance of the custom inventory.
     * @param size int - the size of the inventory.
     * @since 0.0.1
     */
    public CustomInventory(int size) {
        this.inventory = Bukkit.createInventory(null, size);
        this.clickActions = new HashMap<>();
    }

    /**
     * Adds an item to the inventory.
     * @param itemStack ItemStack - the item to add.
     * @since 0.0.1
     */
    public void addItem(ItemStack itemStack) {
        this.inventory.addItem(itemStack);
    }

    /**
     * Adds an item with click action to the inventory.
     * @param itemStack ItemStack - the item to add.
     * @param clickAction {@link ClickAction} - the action on clicking the item.
     * @since 0.0.1
     */
    public void addItem(ItemStack itemStack, ClickAction clickAction) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                setItem(i, itemStack, clickAction);
                break;
            }
        }
    }

    /**
     * Sets an item with a click action to a specific slot.
     * @param slot int - the slot to set the item to.
     * @param itemStack ItemStack - the item to set.
     * @param clickAction {@link ClickAction} - the action on clicking the item.
     * @since 0.0.1
     */
    public void setItem(int slot, ItemStack itemStack, ClickAction clickAction) {
        this.inventory.setItem(slot, itemStack);

        if (clickAction != null)
            setClickAction(slot, clickAction);
    }

    /**
     * Sets an item to a specific slot.
     * @param slot int - the slot to set the item to.
     * @param itemStack ItemStack - the item to set.
     * @since 0.0.1
     */
    public void setItem(int slot, ItemStack itemStack) {
        setItem(slot, itemStack, null);
    }

    /**
     * Sets the click action for a specific slot.
     * @param slot int - the slot to set the click action for.
     * @param clickAction {@link ClickAction} - the click action itself.
     * @since 0.0.1
     */
    public void setClickAction(int slot, ClickAction clickAction) {
        this.clickActions.put(slot, clickAction);
    }

    /**
     * Fills all empty slots with a given item.
     * @param itemStack ItemStack - the item to fill empty slots.
     * @since 0.0.1
     */
    public void fill(ItemStack itemStack) {
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (inventory.getItem(slot) != null && inventory.getItem(slot).getType() != Material.AIR)
                continue;

            this.inventory.setItem(slot, itemStack);
        }
    }

    /**
     * Gets the click action for a given slot.
     * @param slot int - the slot to get the action for.
     * @return {@link ClickAction} - the click action.
     * @since 0.0.1
     */
    public ClickAction getClickAction(int slot) {
        return clickActions.get(slot);
    }

    /**
     * Gets the bukkit inventory stored in the custom inventory.
     * @return Inventory - the bukkit inventory.
     * @since 0.0.1
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Opens the inventory for a specific player.
     * @param apiPlayer {@link APIPlayer} - the player to open the inventory for.
     * @since 0.0.1
     */
    public void open(APIPlayer apiPlayer) {
        apiPlayer.getPlayer().openInventory(inventory);
        apiPlayer.setCustomData("openInv", this);
    }

}
