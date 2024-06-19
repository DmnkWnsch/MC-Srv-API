package net.mcsrvapi.main.api.item;

import com.mojang.authlib.GameProfile;
import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.common.player.PlayerTextures;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to create items with specific properties.
 * The final ItemStack is returned by the craft() method.
 * @since 0.0.1
 */
public class ItemBuilder {

    private static final Logger LOG = McSrvAPI.getInstance().getLogger();
    private static final String COL_COULDNTSET = "Could not set";

    private final ItemStack itemStack;
    private ItemMeta itemMeta;

    /**
     * Creates an instance of the ItemBuilder.
     * @param itemStack ItemStack - the ItemStack to use.
     * @since 0.0.1
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * Creates an instance of the ItemBuilder.
     * @param material Material - the material to use.
     * @since 0.0.1
     */
    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    /**
     * Creates an instance of the ItemBuilder.
     * @param material Material - the material to use.
     * @param amount int - the item amount.
     * @since 0.0.1
     */
    public ItemBuilder(Material material, int amount) {
        this(new ItemStack(material, amount));
    }


    /**
     * Creates an instance of the ItemBuilder.
     * @param material Material - the material to use.
     * @param amount int - the item amount.
     * @param subId short - the sub id of the item.
     * @since 0.0.1
     */
    public ItemBuilder(Material material, int amount, short subId) {
        this(new ItemStack(material, amount, subId));
    }

    /**
     * Creates an instance of the ItemBuilder.
     * @param material Material - the material to use.
     * @param subId short - the sub id of the item.
     * @since 0.0.1
     */
    public ItemBuilder(Material material, short subId) {
        this(new ItemStack(material, 1, subId));
    }

    /**
     * Gets the current ItemStack.
     * @return ItemStack - the current ItemStack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Gets the current ItemMeta
     * @return ItemMeta - the current ItemMeta.
     */
    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    /**
     * Sets the display name of an item.
     * @param displayName String - the display name.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setName(String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    /**
     * Sets the lore of the item.
     * @param lore String... - the lore to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * Sets the lore of the item.
     * @param lore List<String> - the lore to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Sets the amount of the item.
     * @param amount int - the amount to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the material data of the item.
     * @param materialData MaterialData - the material data to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setMaterialData(MaterialData materialData) {
        this.itemStack.setItemMeta(itemMeta);
        this.itemStack.setData(materialData);
        this.itemMeta = this.itemStack.getItemMeta();
        return this;
    }

    /**
     * Sets the durability of the item.
     * @param durability short - the durability to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    /**
     * Sets the item flags for the item.
     * @param itemFlags ItemFlag... - the item flags to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment Enchantment - the enchantment to add.
     * @param level int - the enchantment level.
     * @param ignoreRestriction boolean - whatever the maximum level of the enchantment should be ignored.
     * @param visible boolean - whatever the enchantment should be displayed on the item.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreRestriction, boolean visible) {
        this.itemMeta.addEnchant(enchantment, level, ignoreRestriction);
        if (!visible) {
            return addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment Enchantment - the enchantment to add.
     * @param level int - the enchantment level.
     * @param ignoreRestriction boolean - whatever the maximum level of the enchantment should be ignored.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreRestriction) {
        return addEnchantment(enchantment, level, ignoreRestriction, true);
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment Enchantment - the enchantment to add.
     * @param level int - the enchantment level.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        return addEnchantment(enchantment, level, true, true);
    }

    /**
     * Clears the item flags of the item.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder clearItemFlags() {
        this.itemMeta.getItemFlags().clear();
        return this;
    }

    /**
     * Sets if the item should be unbreakable or not.
     * @param unbreakable boolean - whatever the item should be unbreakable.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the owner of the skull item.
     * Only works if the item is a player skull.
     * @param name String - the player name to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setSkullOwner(String name) {
        return setSkullOwner(Bukkit.getOfflinePlayer(name));
    }

    /**
     * Sets the owner of the skull item.
     * Only works if the item is a player skull.
     * @param offlinePlayer OfflinePlayer - the offline player to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setSkullOwner(OfflinePlayer offlinePlayer) {
        if (!(itemMeta instanceof SkullMeta)) {
            LOG.warning(COL_COULDNTSET + "skull owner: item is not a player skull.");
            return this;
        }

        SkullMeta skullMeta = (SkullMeta) itemMeta;
        if (!skullMeta.hasOwner())
            skullMeta.setOwningPlayer(offlinePlayer);
        return this;
    }

    /**
     * Sets the texture of the skull item.
     * Only works if the item is a player skull.
     * @param playerTextures PlayerTextures - The texture of a player.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    private ItemBuilder setSkullOwner(PlayerTextures playerTextures) {
        if (!(itemMeta instanceof SkullMeta)) {
            LOG.log(Level.WARNING, COL_COULDNTSET + "skull owner: item is not a player skull.");
            return this;
        }

        GameProfile profile = new GameProfile(playerTextures.getUUID(), null);
        //profile.getProperties().put("textures", new Property("textures", playerTextures.getTexture(), playerTextures.getSignature()));
        try {
            Field profileField = ((SkullMeta) itemMeta).getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            try {
                profileField.set(itemMeta, profile);
            } catch (IllegalAccessException e) {
                LOG.log(Level.WARNING, COL_COULDNTSET + "skull owner: profile field could not be overwritten.");
            }
        } catch (NoSuchFieldException e) {
            LOG.log(Level.WARNING, COL_COULDNTSET + "skull owner: Could not get profile field.");
        }
        return this;
    }

    /**
     * Sets the type of entity to spawn with the spawn egg.
     * Only works if the item is a spawn egg.
     * @param entityType EntityType - the entity type to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setSpawnEggType(EntityType entityType) {
        if (!(itemMeta instanceof SpawnEggMeta)) {
            LOG.warning(COL_COULDNTSET + "egg type: item is not an egg.");
            return this;
        }

        SpawnEggMeta spawnEggMeta = (SpawnEggMeta) itemMeta;
        spawnEggMeta.setSpawnedType(entityType);
        return this;
    }

    /**
     * Adds a potion effect to the item.
     * Only works if the item is a potion.
     * @param potionEffect PotionEffect - the potion effect to add.
     * @param overwrite boolean - whatever
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addPotionEffect(PotionEffect potionEffect, boolean overwrite) {
        if (!(itemMeta instanceof PotionMeta)) {
            LOG.warning(COL_COULDNTSET + "potion effect: item os not a potion.");
            return this;
        }

        PotionMeta potionMeta = (PotionMeta) itemMeta;
        potionMeta.addCustomEffect(potionEffect, overwrite);
        return this;
    }

    /**
     * Adds a potion effect to the item.
     * Only works if the item is a potion.
     * @param potionEffect PotionEffect - the potion effect to add.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addPotionEffect(PotionEffect potionEffect) {
        return addPotionEffect(potionEffect, true);
    }

    /**
     * Sets the potion data of the item.
     * @param potionData PotionData - the potion data to set.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setPotionData(PotionData potionData) {
        if (!(itemMeta instanceof PotionMeta)) {
            LOG.warning(COL_COULDNTSET + "potion effect: item is not a potion.");
            return this;
        }

        PotionMeta potionMeta = (PotionMeta) itemMeta;
        potionMeta.setBasePotionData(potionData);
        return this;
    }

    /**
     * Sets the leather color of the item.
     * Only works if the item is a piece of leather armor.
     * @param color Color - the color for the item.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setLeatherColor(Color color) {
        if (!(itemMeta instanceof LeatherArmorMeta)) {
            LOG.warning(COL_COULDNTSET + "leather color: item is not a leather armor piece.");
            return this;
        }

        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
        leatherArmorMeta.setColor(color);
        return this;
    }

    /**
     * Sets if the map item should scale or not.
     * Only works if the item is a map.
     * @param mapScaling boolean - whatever the map should scale or not.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setMapScaling(boolean mapScaling) {
        if (!(itemMeta instanceof MapMeta)) {
            LOG.warning(COL_COULDNTSET + "map scaling: the item is not a map.");
            return this;
        }

        MapMeta mapMeta = (MapMeta) itemMeta;
        mapMeta.setScaling(mapScaling);
        return this;
    }

    /**
     * Sets the book author of the book item.
     * Only works if the item is a book.
     * @param bookAuthor String - the name of the book author.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setBookAuthor(String bookAuthor) {
        if (!(itemMeta instanceof BookMeta)) {
            LOG.warning(COL_COULDNTSET + "book author: item is not a book.");
            return this;
        }

        BookMeta bookMeta = (BookMeta) itemMeta;
        bookMeta.setAuthor(bookAuthor);
        return this;
    }

    /**
     * Sets the book title of the item.
     * Only works if the item is a book.
     * @param bookTitle String - the title of the book.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setBookTitle(String bookTitle) {
        if (!(itemMeta instanceof BookMeta)) {
            LOG.warning(COL_COULDNTSET + "book title: item is not a book.");
            return this;
        }

        BookMeta bookMeta = (BookMeta) itemMeta;
        bookMeta.setTitle(bookTitle);
        return this;
    }

    /**
     * Adds a page to the book item.
     * Only works if the item is a book.
     * @param text String... - the text to add to the page.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addPage(String... text) {
        if (!(itemMeta instanceof BookMeta)) {
            LOG.warning("Could not add book page: item is not a book.");
            return this;
        }

        BookMeta bookMeta = (BookMeta) itemMeta;
        bookMeta.addPage(text);
        return this;
    }

    /**
     * Adds a page to the book item.
     * Only works if the item is a book.
     * @param baseComponents BaseComponent... - the base components to add to the page.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder addPage(BaseComponent... baseComponents) {
        if (!(itemMeta instanceof BookMeta)) {
            LOG.warning("Could not add book page: item is not a book.");
            return this;
        }

        BookMeta bookMeta = (BookMeta) itemMeta;
        bookMeta.spigot().addPage(baseComponents);
        return this;
    }

    /**
     * Sets a specific page in the book.
     * @param page int - the page.
     * @param text String - the text to add to the page.
     * @return ItemBuilder - the current item builder.
     * @since 0.0.1
     */
    public ItemBuilder setPage(int page, String text) {
        if (!(itemMeta instanceof BookMeta)) {
            LOG.warning(COL_COULDNTSET + "book page: item is not a book.");
            return this;
        }

        BookMeta bookMeta = (BookMeta) itemMeta;
        bookMeta.setPage(page, text);
        return this;
    }

    /**
     * Creates the item with the ItemMeta.
     * @return ItemStack - the final ItemStack.
     * @since 0.0.1
     */
    public ItemStack craft() {
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }
}
