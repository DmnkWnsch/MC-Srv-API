package net.mcsrvapi.main.api.item;

import net.mcsrvapi.main.api.common.player.PlayerTextures;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Class for some items which will be used often and should be the same everywhere.
 * @since 0.0.1
 */
public class UsefulItems {

    private UsefulItems() {
        throw new UnsupportedOperationException("UsefulItems is a utility class.");
    }

    /**
     * All colors of glass panes which are possible
     * The item does not have a name for usage as a background in inventories.
     */
    public static final ItemStack BACKGROUND_WHITE = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 0).setName(" ").craft();
    public static final ItemStack BACKGROUND_ORANGE = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 1).setName(" ").craft();
    public static final ItemStack BACKGROUND_MAGENTA = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 2).setName(" ").craft();
    public static final ItemStack BACKGROUND_LIGHT_BLUE = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 3).setName(" ").craft();
    public static final ItemStack BACKGROUND_YELLOW = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 4).setName(" ").craft();
    public static final ItemStack BACKGROUND_LIME = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 5).setName(" ").craft();
    public static final ItemStack BACKGROUND_PINK = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 6).setName(" ").craft();
    public static final ItemStack BACKGROUND_GRAY = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 7).setName(" ").craft();
    public static final ItemStack BACKGROUND_LIGHT_GRAY = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 8).setName(" ").craft();
    public static final ItemStack BACKGROUND_CYAN = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 9).setName(" ").craft();
    public static final ItemStack BACKGROUND_PURPLE = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 10).setName(" ").craft();
    public static final ItemStack BACKGROUND_BLUE = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 11).setName(" ").craft();
    public static final ItemStack BACKGROUND_BROWN = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 12).setName(" ").craft();
    public static final ItemStack BACKGROUND_GREEN = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 13).setName(" ").craft();
    public static final ItemStack BACKGROUND_RED = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 14).setName(" ").craft();
    public static final ItemStack BACKGROUND_BLACK = new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 15).setName(" ").craft();

    /**
     * Creates an item that show a arrow to the left as a player skull.
     */
    public static final ItemStack LEFT_ARROW = new ItemBuilder(Material.SKULL_ITEM, (short) 3).setSkullOwner("MHF_ArrowLeft").craft();

    /**
     * Creates an item that show a arrow to the right as a player skull.
     */
    public static final ItemStack RIGHT_ARROW = new ItemBuilder(Material.SKULL_ITEM, (short) 3).setSkullOwner("MHF_ArrowRight").craft();

    public static ItemBuilder getHeadByTexture(PlayerTextures textures) {
        //Will be added in the future
        return null;
    }

}
