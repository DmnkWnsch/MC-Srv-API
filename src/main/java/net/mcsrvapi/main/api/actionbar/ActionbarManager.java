package net.mcsrvapi.main.api.actionbar;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.server.APIServer;
import net.mcsrvapi.main.api.util.Utils;
import net.mcsrvapi.misc.translationhandler.translations.Language;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Manager for action bars
 * @since 0.0.1
 */
public class ActionbarManager {

    private boolean runAutomatically;

    private final Map<UUID, ABMessageHolder> staticMessages;
    private final Map<UUID, List<ABMessageHolder>> messages;
    private final Map<UUID, List<ABMessageHolder>> importantMessages;
    private final Map<UUID, ABMessageHolder> lastMessage;

    /**
     * Creates the actionbar manager.
     * @since 0.0.1
     */
    public ActionbarManager() {
        this.staticMessages = new HashMap<>();
        this.messages = new HashMap<>();
        this.importantMessages = new HashMap<>();
        this.lastMessage = new HashMap<>();
        this.runAutomatically = false;
    }

    /**
     * Adds an action bar message to a given uuid.
     * @param uuid UUID - the uuid of the target player.
     * @param message {@link ActionbarMessage} - the message to send.
     * @since 0.0.1
     */
    public void addActionbar(UUID uuid, ActionbarMessage message) {
        Language language = McSrvAPI.getInstance().getPlayerHandler().getPlayer(uuid).getLanguage();
        String translatedMessage = language.getTranslation(message.getMessageKey());
        ABMessageHolder messageHolder = new ABMessageHolder(translatedMessage, message.getDuration());

        addActionbar(uuid, message.getType(), messageHolder);
    }

    /**
     * Adds an action bar message to a given uuid.
     * @param uuid UUID - the uuid of the target player.
     * @param message {@link ActionbarMessage} - the message to send.
     * @param placeholder String - the placeholder for translation.
     * @param replacement String - the replacement for translation.
     * @since 0.0.1
     */
    public void addActionbar(UUID uuid, ActionbarMessage message, String placeholder, String replacement) {
        Language language = McSrvAPI.getInstance().getPlayerHandler().getPlayer(uuid).getLanguage();
        String translatedMessage = language.getTranslation(message.getMessageKey(), placeholder, replacement);
        ABMessageHolder messageHolder = new ABMessageHolder(translatedMessage, message.getDuration());

        addActionbar(uuid, message.getType(), messageHolder);
    }

    /**
     * Adds an action bar message to a given uuid.
     * @param uuid UUID - the uuid of the target player.
     * @param message {@link ActionbarMessage} - the message to send.
     * @param placeholders List<String> - the placeholders for translation.
     * @param replacements List<String> - the replacements for translation.
     * @since 0.0.1
     */
    public void addActionbar(UUID uuid, ActionbarMessage message, List<String> placeholders, List<String> replacements) {
        Language language = McSrvAPI.getInstance().getPlayerHandler().getPlayer(uuid).getLanguage();
        String translatedMessage = language.getTranslation(message.getMessageKey(), placeholders, replacements);
        ABMessageHolder messageHolder = new ABMessageHolder(translatedMessage, message.getDuration());

        addActionbar(uuid, message.getType(), messageHolder);
    }

    /**
     * Internal function to add the actionbar message to the maps.
     * @param uuid UUID - the uuid of the target player.
     * @param type {@link ActionbarMessageType} - the type of the action bar message.
     * @param messageHolder {@link ABMessageHolder} - the holder of the message.
     * @since 0.0.1
     */
    private void addActionbar(UUID uuid, ActionbarMessageType type, ABMessageHolder messageHolder) {
        if (type == ActionbarMessageType.STATIC) {
            staticMessages.put(uuid, messageHolder);
            return;
        }

        if (type == ActionbarMessageType.IMPORTANT) {
            List<ABMessageHolder> messageHolders = importantMessages.get(uuid);
            if (messageHolders == null)
                messageHolders = new ArrayList<>();

            messageHolders.add(messageHolder);
            importantMessages.put(uuid, messageHolders);
            return;
        }

        if (importantMessages.containsKey(uuid) && !importantMessages.get(uuid).isEmpty()) {
            lastMessage.put(uuid, messageHolder);
            return;
        }

        List<ABMessageHolder> messageHolders = messages.get(uuid);
        if (messageHolders == null)
            messageHolders = new ArrayList<>();

        messageHolders.add(messageHolder);
        messages.put(uuid, messageHolders);
    }

    /**
     * Removes all action bars from a given uuid.
     * @param uuid UUID - the player to clear.
     * @since 0.0.1
     */
    public void clearActionbar(UUID uuid) {
        this.staticMessages.remove(uuid);
        this.messages.remove(uuid);
        this.importantMessages.remove(uuid);
    }

    /**
     * Starts the scheduler for sending the actionbar.
     * This is needed because an action bar is only sent for a second by default.
     * @since 0.0.1
     */
    public void start() {
        this.runAutomatically = true;
    }

    /**
     * Gets if the action bar manager should run automatically or not.
     * @return boolean - whatever it should run automatically or not.
     * @since 0.0.1
     */
    public boolean runsAutomatically() {
        return runAutomatically;
    }

    /**
     * Updates the action bar for all online players
     * Can be triggered manually for game countdowns or just use the start method to run the scheduler.
     * @since 0.0.1
     */
    public void update() {
        APIServer.getOnlinePlayers().forEach(onlinePlayer -> {
            UUID uuid = onlinePlayer.getUniqueId();
            String targetMessage = getTargetMessage(uuid);

            if (targetMessage != null) {
                sendActionbar(onlinePlayer.getPlayer(), targetMessage);
            }
        });
    }

    /**
     * Gets the target message which should be displayed to the uuid.
     * @param uuid UUID - the target player.
     * @return String - the target message.
     * @since 0.0.1
     */
    private String getTargetMessage(UUID uuid) {

        String importantMessage = getImportantMessage(uuid);

        if (importantMessage != null)
            return importantMessage;

        String normalMessage = getNormalMessage(uuid);

        if (normalMessage != null)
            return normalMessage;

        if (staticMessages.containsKey(uuid))
            return staticMessages.get(uuid).getMessage();

        return null;
    }

    /**
     * Gets a normal message which should be displayed to the uuid.
     * @param uuid UUID - the target player.
     * @return String - the normal message.
     * @since 0.0.1
     */
    private String getNormalMessage(UUID uuid) {
        if (!messages.containsKey(uuid) || messages.get(uuid).isEmpty())
            return null;

        return checkList(messages.get(uuid));
    }

    /**
     * Gets a important message which should be displayed to the uuid.
     * @param uuid UUID - the target player.
     * @return String - the normal message.
     * @since 0.0.1
     */
    private String getImportantMessage(UUID uuid) {
        if (!importantMessages.containsKey(uuid) || importantMessages.get(uuid).isEmpty())
            return null;

        String impMessage =  checkList(importantMessages.get(uuid));
        if (impMessage == null && lastMessage.containsKey(uuid)) {
            addActionbar(uuid, ActionbarMessageType.TIMED, lastMessage.get(uuid));
            lastMessage.remove(uuid);
        }

        return impMessage;
    }

    /**
     * Checks a message list and gets the next entry which should be displayed.
     * @param list List<{@link ABMessageHolder}> - the list of messages.
     * @return String - the target message.
     * @since 0.0.1
     */
    private String checkList(List<ABMessageHolder> list) {
        ABMessageHolder holder = list.get(0);

        if (holder.getDuration() == 1) {
            list.remove(holder);
            return holder.getMessage();
        }

        if (!holder.hasEnded()) {
            holder.increaseTimer();
            return holder.getMessage();
        } else {
            list.remove(holder);
            if (!list.isEmpty()) {
                return list.get(0).getMessage();
            }
        }

        return null;
    }

    /**
     * Sends an action bar to a given player with a given message.
     * @param player Player - the player to send to.
     * @param message String - the message to send.
     * @since 0.0.1
     */
    private void sendActionbar(Player player, String message) {
        IChatBaseComponent chatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");

        PacketPlayOutChat chatPacket =  new PacketPlayOutChat(chatBaseComponent, ChatMessageType.GAME_INFO);
        PlayerConnection connection = Utils.getPlayerConnection(player);
        connection.sendPacket(chatPacket);
    }
}
