package net.mcsrvapi.main.api.server;

import net.mcsrvapi.main.api.event.ServerSettingChangeEvent;
import org.bukkit.Bukkit;

import java.util.EnumMap;
import java.util.Map;

/**
 * Class to edit the server settings.
 * @since 0.0.1
 */
public class ServerSettings {

    private final Map<ServerSetting, Boolean> settingsMap;

    /**
     * Creates the instance of the server settings.
     * @since 0.0.1
     */
    public ServerSettings() {
        this.settingsMap = new EnumMap<>(ServerSetting.class);
    }

    /**
     * Loads the default values which are stored in the settings.
     * @since 0.0.1
     */
    public void loadDefaultSettings() {
        for (ServerSetting serverSetting : ServerSetting.values()) {
            set(serverSetting, serverSetting.getDefaultValue());
        }
    }

    /**
     * Enables a given server setting.
     * @param serverSetting {@link ServerSetting} - the setting.
     * @since 0.0.1
     */
    public void enable(ServerSetting serverSetting) {
        set(serverSetting, true);
    }

    /**
     * Enables given server settings.
     * @param serverSettings {@link ServerSetting}[] - the list of settings.
     * @since 0.0.1
     */
    public void enable(ServerSetting... serverSettings) {
        for (ServerSetting serverSetting : serverSettings) {
            enable(serverSetting);
        }
    }

    /**
     * Disables a given server setting.
     * @param serverSetting {@link ServerSetting} - the setting.
     * @since 0.0.1
     */
    public void disable(ServerSetting serverSetting) {
        set(serverSetting, false);
    }

    /**
     * Disables given server settings.
     * @param serverSettings {@link ServerSetting}[] - the list of settings.
     * @since 0.0.1
     */
    public void disable(ServerSetting... serverSettings) {
        for (ServerSetting serverSetting : serverSettings) {
            disable(serverSetting);
        }
    }

    /**
     * Gets if the setting is enabled or not.
     * @param serverSetting {@link ServerSetting} - the target setting.
     * @return boolean - whatever the setting is enabled or not.
     * @since 0.0.1
     */
    public boolean isEnabled(ServerSetting serverSetting) {
        return this.settingsMap.get(serverSetting);
    }

    /**
     * Sets a specific setting to a given state.
     * @param serverSetting {@link ServerSetting} - the target setting.
     * @param state boolean - the new state.
     * @since 0.0.1
     */
    public void set(ServerSetting serverSetting, boolean state) {
        this.settingsMap.put(serverSetting, state);
        Bukkit.getPluginManager().callEvent(new ServerSettingChangeEvent(serverSetting, state));
    }

}
