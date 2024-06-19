package net.mcsrvapi.main.api.util;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.command.handler.APICommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility to register event listeners and api commands.
 * @since 0.0.1
 */
public class RegisterUtil {

    private final JavaPlugin javaPlugin;

    private final List<Listener> listeners;
    private final List<APICommand> apiCommands;

    /**
     * Creates an instance of the register util.
     * @param javaPlugin JavaPlugin - the plugin to register for.
     * @since 0.0.1
     */
    public RegisterUtil(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.listeners = new ArrayList<>();
        this.apiCommands = new ArrayList<>();
    }

    /**
     * Adds an api command to the registerer.
     * @param apiCommand {@link APICommand} - the api command.
     * @since 0.0.1
     */
    public void addCommand(APICommand apiCommand) {
        this.apiCommands.add(apiCommand);
    }

    /**
     * Adds api commands to the registerer.
     * @param apiCommands {@link APICommand}[] - the api commands.
     * @since 0.0.1
     */
    public void addCommands(APICommand... apiCommands) {
        this.apiCommands.addAll(Arrays.asList(apiCommands));
    }

    /**
     * Adds a listener to the registerer.
     * @param listener Listener - the listener.
     * @since 0.0.1
     */
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Adds a list of listeners to the registerer.
     * @param listeners Listener[] - the listeners.
     * @since 0.0.1
     */
    public void addListeners(Listener... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    /**
     * Registers all commands an listeners.
     * @since 0.0.1
     */
    public void register() {
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();
        listeners.forEach(listener -> pluginManager.registerEvents(listener, javaPlugin));

        McSrvAPI.getInstance().getCommandHandler().addCommands(apiCommands);
    }

    /**
     * Gets all listeners.
     * @return List<Listener>
     */
    public List<Listener> getListeners() {
        return listeners;
    }

    /**
     * Gets all api commands.
     * @see APICommand
     * @return List<APICommand>
     */
    public List<APICommand> getApiCommands() {
        return apiCommands;
    }

    /**
     * Gets the plugin which will be registered for.
     * @return JavaPlugin - the instance.
     */
    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
