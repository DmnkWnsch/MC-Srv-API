package net.mcsrvapi.main.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An event which is triggered every second as an async task.
 * @since 0.0.1
 */
public class AsyncAPITickEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
