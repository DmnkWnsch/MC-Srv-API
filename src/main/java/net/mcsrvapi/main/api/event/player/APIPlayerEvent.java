package net.mcsrvapi.main.api.event.player;

import net.mcsrvapi.main.api.player.APIPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Class for events that contain an api player.
 * @since 0.0.1
 */
public class APIPlayerEvent extends Event {

    private final APIPlayer apiPlayer;

    /**
     * Creates the event with a given player.
     * @param apiPlayer {@link APIPlayer} - the player.
     * @since 0.0.1
     */
    public APIPlayerEvent(APIPlayer apiPlayer) {
        this.apiPlayer = apiPlayer;
    }

    /**
     * Gets the api player which triggered the event.
     * @return {@link APIPlayer} - the player.
     * @since 0.0.1
     */
    public APIPlayer getAPIPlayer() {
        return apiPlayer;
    }

    private static final HandlerList handlerList = new HandlerList();

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
