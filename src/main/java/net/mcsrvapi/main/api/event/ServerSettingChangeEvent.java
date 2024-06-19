package net.mcsrvapi.main.api.event;

import net.mcsrvapi.main.api.server.ServerSetting;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event which gets triggered when a Server Setting has changed.
 * @see ServerSetting
 * @since 0.0.1
 */
public class ServerSettingChangeEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final ServerSetting serverSetting;
    private final boolean newState;

    /**
     * Creates an instance of the event.
     * @param serverSetting {@link ServerSetting} - the setting that has changed.
     * @param newState boolean - the new state.
     */
    public ServerSettingChangeEvent(ServerSetting serverSetting, boolean newState) {
        this.serverSetting = serverSetting;
        this.newState = newState;
    }

    public ServerSetting getServerSetting() {
        return serverSetting;
    }

    public boolean getNewState() {
        return newState;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
