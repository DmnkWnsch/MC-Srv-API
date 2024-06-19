package net.mcsrvapi.main.api.player;

import io.netty.buffer.Unpooled;
import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.actionbar.ActionbarMessage;
import net.mcsrvapi.main.api.common.player.DatabasePlayer;
import net.mcsrvapi.main.api.common.util.TranslationMessageHolder;
import net.mcsrvapi.main.api.hologram.APIHologram;
import net.mcsrvapi.main.api.hologram.HologramText;
import net.mcsrvapi.main.api.title.TitleSender;
import net.mcsrvapi.main.api.util.LocationUtil;
import net.mcsrvapi.misc.translationhandler.TranslationHandler;
import net.mcsrvapi.misc.translationhandler.translations.Language;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * The player object used in the api for extending the spigot player.
 * @since 0.0.1
 */
public class APIPlayer {

    private final Player player;
    private Language language;
    private final DatabasePlayer databasePlayer;
    private final TitleSender titleSender;

    private final Map<String, Object> customData;
    private final List<APIHologram> holograms;

    /**
     * Creates an instance of the api player for a given player.
     * @param player Player - the target player.
     */
    public APIPlayer(Player player) {
        this.player = player;
        this.customData = new HashMap<>();
        this.holograms = new ArrayList<>();
        this.databasePlayer = new DatabasePlayer(getUniqueId());
        this.titleSender = new TitleSender(player);
        setLanguage(TranslationHandler.getInstance().getLanguageHandler().getDefaultLanguage());
    }

    /**
     * Gets the UUID of the player.
     * @return UUID - the uuid.
     * @since 0.0.1
     */
    public UUID getUniqueId() {
        return getPlayer().getUniqueId();
    }

    /**
     * Sets and stores player dependent data.
     * @param name String - the name of the data.
     * @param data Object - the object to store.
     * @since 0.0.1
     */
    public void setCustomData(String name, Object data) {
        this.customData.put(name, data);
    }

    /**
     * Gets data that is stored in the player.
     * @param name String - the name of the data.
     * @return Object - the data itself.
     * @since 0.0.1
     */
    public Object getCustomData(String name) {
        return this.customData.get(name);
    }

    /**
     * Removes data from the player.
     * @param name String - the name of the data.
     * @since 0.0.1
     */
    public void removeCustomData(String name) {
        this.customData.remove(name);
    }

    /**
     * Gets if the player has a specific data stored.
     * @param name String - the name of the data.
     * @return boolean - whatever the data is stored.
     * @since 0.0.1
     */
    public boolean hasCustomData(String name) {
        return this.customData.containsKey(name);
    }

    /**
     * Switches a boolean which is stored in the custom data.
     * @param name String - the boolean which should be switched.
     * @return boolean - whatever the new state of the boolean is.
     * @since 0.0.1
     */
    public boolean switchCustomBooleanData(String name) {
        if (this.customData.containsKey(name)) {
            this.customData.remove(name);
            return false;
        }

        this.customData.put(name, true);
        return true;
    }

    /**
     * Sends a message to the player.
     * @param message String - the message to send.
     * @since 0.0.1
     */
    public void sendRawMessage(String message) {
        getPlayer().sendMessage(message);
    }

    /**
     * Sends a message to the player with a prefix.
     * @param message String - the message to send.
     * @since 0.0.1
     */
    public void sendPrefixedMessage(String message) {
        sendRawMessage(McSrvAPI.getPrefix() + message);
    }

    /**
     * Returns the player object used
     * @return Player - the player which is used
     * @since 0.0.1
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sends a message to the player in his language.
     * @param messageKey String - the message name.
     * @since 0.0.1
     */
    public void sendMessage(String messageKey) {
        String message = getLanguage().getTranslation(messageKey);
        sendPrefixedMessage(message);
    }

    /**
     * Sends a message to the player in his language with replacements.
     * @param messageKey String - the message name.
     * @param placeholder String - the string which should be replaced.
     * @param replacement String - the replacement.
     * @since 0.0.1
     */
    public void sendMessage(String messageKey, String placeholder, String replacement) {
        String message = getLanguage().getTranslation(messageKey, placeholder, replacement);
        sendPrefixedMessage(message);
    }

    /**
     * Sends a message to the player in his language with replacements.
     * @param messageKey String - the message name.
     * @param placeholders List<String> - the string which should be replaced.
     * @param replacements List<String> - the replacement.
     * @since 0.0.1
     */
    public void sendMessage(String messageKey, List<String> placeholders, List<String> replacements) {
        String message = getLanguage().getTranslation(messageKey, placeholders, replacements);
        sendPrefixedMessage(message);
    }

    /**
     * Sends an empty message to the player.
     * @since 0.0.1
     */
    public void sendEmptyMessage() {
        sendRawMessage(" ");
    }

    /**
     * Sends an given amount of empty messages to the player.
     * @param amount int - the amount of messages.
     * @since 0.0.1
     */
    public void sendEmptyMessage(int amount) {
        for (int i = 0; i < amount; i++) {
            sendEmptyMessage();
        }
    }

    /**
     * Gets the language of the player.
     * @return {@link Language} - the selected language.
     * @since 0.0.1
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the language of the player.
     * @param language {@link Language} - the new language.
     * @since 0.0.1
     */
    public void setLanguage(Language language) {
        this.language = language;
        this.titleSender.setLanguage(language);
    }

    /**
     * Gets if the player has a given permission.
     * @param permission String - the permission to check.
     * @return boolean - whatever the player has the permission or not.
     * @since 0.0.1
     */
    public boolean hasPermission(String permission) {
        return getPlayer().hasPermission(permission);
    }

    /**
     * Sends a base component to the player as a message.
     * @see net.md_5.bungee.api.chat.BaseComponent
     * @param baseComponent BaseComponent - the component to send.
     * @since 0.0.1
     */
    public void sendComponent(BaseComponent baseComponent) {
        getPlayer().spigot().sendMessage(baseComponent);
    }

    /**
     * Sends a list of base components to the player as a message.
     * @see net.md_5.bungee.api.chat.BaseComponent
     * @param baseComponents BaseComponent... - the components to send.
     * @since 0.0.1
     */
    public void sendComponent(BaseComponent... baseComponents) {
        getPlayer().spigot().sendMessage(baseComponents);
    }

    /**
     * Gets the display name of the player.
     * @return String - the display name.
     * @since 0.0.1
     */
    public String getDisplayName() {
        return getPlayer().getName();
    }

    /**
     * Sends an action bar to the player.
     * @param actionbarMessage {@link ActionbarMessage} - the message to send.
     * @since 0.0.1
     */
    public void sendActionbar(ActionbarMessage actionbarMessage) {
        McSrvAPI.getInstance().getActionbarManager().addActionbar(getUniqueId(), actionbarMessage);
    }

    /**
     * Sends an action bar to the player with replacement.
     * @param actionbarMessage {@link ActionbarMessage} - the message to send.
     * @param placeholder String - the placeholder to replace.
     * @param replacement String - the replacement.
     * @since 0.0.1
     */
    public void sendActionbar(ActionbarMessage actionbarMessage, String placeholder, String replacement) {
        McSrvAPI.getInstance().getActionbarManager().addActionbar(getUniqueId(), actionbarMessage, placeholder, replacement);
    }

    /**
     * Sends an action bar to the player with replacements.
     * @param actionbarMessage {@link ActionbarMessage} - the message to send.
     * @param placeholders List<String> - the placeholders to replace.
     * @param replacements Lift<String> - the replacements.
     */
    public void sendActionbar(ActionbarMessage actionbarMessage, List<String> placeholders, List<String> replacements) {
        McSrvAPI.getInstance().getActionbarManager().addActionbar(getUniqueId(), actionbarMessage, placeholders, replacements);
    }

    /**
     * Clears the action bar for the player.
     * @since 0.0.1
     */
    public void clearActionbar() {
        McSrvAPI.getInstance().getActionbarManager().clearActionbar(getUniqueId());
    }

    /**
     * Gets the database player object for the api player.
     * @return {@link DatabasePlayer} - the database player.
     * @since 0.0.1
     */
    public DatabasePlayer getDatabasePlayer() {
        return databasePlayer;
    }

    /**
     * Sends packets to the player
     * @param packets Packet<?>[] - Packets to send to the player
     */
    public void sendPacket(Packet<?>... packets) {
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        for (Packet<?> packet : packets)
            playerConnection.sendPacket(packet);
    }

    /**
     * Opens a book for the player.
     * @param bookItem ItemStack - the book item to open.
     * @since 0.0.1
     */
    public void openBook(ItemStack bookItem) {
        int slot = getPlayer().getInventory().getHeldItemSlot();
        ItemStack heldItem = getPlayer().getInventory().getItem(slot);
        getPlayer().getInventory().setItem(slot, bookItem);

        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(EnumHand.MAIN_HAND);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", packetDataSerializer);
        sendPacket(packet);

        getPlayer().getInventory().setItem(slot, heldItem);
    }

    /**
     * Plays a sound to the player.
     * @param sound Sound - the sound to play.
     * @since 0.0.1
     */
    public void playSound(Sound sound) {
        player.playSound(player.getLocation(), sound, 5, 1);
    }

    /**
     * Gets the title sender to send titles to the player.
     * @return {@link TitleSender} - the title sender.
     * @since 0.0.1
     */
    public TitleSender getTitleSender() {
        return titleSender;
    }

    /**
     * Shows a hologram to the player.
     * @param apiHologram {@link APIHologram} - the hologram to show.
     * @since 0.0.1
     */
    public void showHologram(APIHologram apiHologram) {
        holograms.add(apiHologram);

        for (EntityArmorStand entityArmorStand : apiHologram.getArmorStands()) {
            sendPacket(new PacketPlayOutSpawnEntityLiving(entityArmorStand));
        }
    }

    /**
     * Hides a hologram from a player.
     * @param apiHologram {@link APIHologram} - the hologram to hide.
     * @since 0.0.1
     */
    public void hideHologram(APIHologram apiHologram) {
        holograms.remove(apiHologram);

        for (EntityArmorStand entityArmorStand : apiHologram.getArmorStands()) {
            sendPacket(new PacketPlayOutEntityDestroy(entityArmorStand.getId()));
        }
     }

    /**
     * Hides holograms from a player on a specific location.
     * @param location Location - the location where the holograms are located.
     * @since 0.0.1
     */
     public void hideHolograms(Location location) {
        holograms.stream().filter(holo -> LocationUtil.equalsLocation(location, holo.getLocation()))
                .forEach(this::hideHologram);
     }

    /**
     * Gets all holograms which are at a specific location.
     * @param location Location - the location where the holograms are located.
     * @return List of {@link APIHologram} - the holograms.
     * @since 0.0.1
     */
     public List<APIHologram> getHolograms(Location location) {
        List<APIHologram> result = new ArrayList<>();

        holograms.stream().filter(holo -> LocationUtil.equalsLocation(location, holo.getLocation()))
                 .forEach(result::add);

        return result;
     }

    /**
     * Shows a hologram for a certain time.
     * @param apiHologram {@link APIHologram} - the hologram.
     * @param seconds int - the duration in seconds.
     * @since 0.0.1
     */
     public void showHologram(APIHologram apiHologram, int seconds) {
        showHologram(apiHologram);
        Bukkit.getScheduler().runTaskLaterAsynchronously(McSrvAPI.getInstance(), () -> hideHologram(apiHologram), 20L * seconds);
     }

    /**
     * Hides all holograms which the player can see.
     * @since 0.0.1
     */
    public void hideHolograms() {
        holograms.forEach(apiHologram -> hideHolograms());
    }

    /**
     * Gets all holograms which the player can see.
     * @return List of {@link APIHologram} - the holograms.
     * @since 0.0.1
     */
    public List<APIHologram> getShownHolograms() {
        return holograms;
    }

    /**
     * Shows a hologram created by a hologram text and a location to the player.
     * @param hologramText {@link HologramText} - the hologram text to display.
     * @param location Location - the location to show the hologram at.
     * @return {@link APIHologram} - the created hologram.
     * @since 0.0.1
     */
    public APIHologram showHologram(HologramText hologramText, Location location) {
        List<String> hologramLines = new ArrayList<>();
        for (TranslationMessageHolder holder : hologramText.getLines()) {
            if (!holder.hasReplacements()) {
                hologramLines.add(getLanguage().getTranslation(holder.getMessageKey()));
                continue;
            }

            if (holder.isSingleReplacement()) {
                hologramLines.add(getLanguage().getTranslation(holder.getMessageKey(), holder.getPlaceholder(), holder.getReplacement()));
                continue;
            }

            hologramLines.add(getLanguage().getTranslation(holder.getMessageKey(), holder.getPlaceholders(), holder.getReplacements()));
        }

        APIHologram apiHologram = new APIHologram(location, hologramLines);
        showHologram(apiHologram);
        return apiHologram;
    }

    /**
     * Shows a hologram created by a hologram text and a location to the player for a certain time.
     * @param hologramText {@link HologramText} - the hologram text to display.
     * @param location Location - the location to show the hologram at.
     * @param seconds int - the duration in seconds.
     * @return {@link APIHologram} - the created hologram.
     * @since 0.0.1
     */
    public APIHologram showHologram(HologramText hologramText, Location location, int seconds) {
        APIHologram hologram = showHologram(hologramText, location);
        Bukkit.getScheduler().runTaskLaterAsynchronously(McSrvAPI.getInstance(), () -> hideHologram(hologram), 20L * seconds);
        return hologram;
    }

    /**
     * Hides all holograms in a specific chunk for the player.
     * @param chunkId int - the id of the chunk.
     * @since 0.0.1
     */
    public void hideChunkHolograms(int chunkId) {
        holograms.stream().filter(holo -> holo.getChunkID() == chunkId)
                .forEach(this::hideHologram);
    }

}