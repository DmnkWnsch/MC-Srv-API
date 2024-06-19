package net.mcsrvapi.main.api.listener;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.common.util.SpecialChars;
import net.mcsrvapi.main.api.messages.TranslationKeys;
import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listener to check all messages sent in the chat.
 * @since 0.0.1
 */
public class ChatListener implements Listener {

    /**
     * Event gets called when a player sends a message.
     * @param event AsyncPlayerChatEvent - the chat event.
     * @since 0.0.1
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        APIPlayer apiPlayer = McSrvAPI.getInstance().getPlayerHandler().getPlayer(event.getPlayer());

        if (!McSrvAPI.getInstance().getServerSettings().isEnabled(ServerSetting.CHAT)
                && !apiPlayer.hasPermission("globalmute.bypass")) {
                apiPlayer.sendMessage(TranslationKeys.API_CHAT_DISABLED);
                event.setCancelled(true);
                return;
        }

        event.setFormat(apiPlayer.getDisplayName() + "§8 " + SpecialChars.DOUBLE_ARROW_RIGHT + "§7 " + event.getMessage());
    }

}
