package net.mcsrvapi.main.api;

import net.mcsrvapi.main.api.actionbar.ActionbarManager;
import net.mcsrvapi.main.api.command.*;
import net.mcsrvapi.main.api.command.handler.CommandHandler;
import net.mcsrvapi.main.api.event.AsyncAPITickEvent;
import net.mcsrvapi.main.api.inventory.InventoryHandler;
import net.mcsrvapi.main.api.inventory.InventoryListener;
import net.mcsrvapi.main.api.listener.*;
import net.mcsrvapi.main.api.messages.EnglishMessages;
import net.mcsrvapi.main.api.player.PlayerHandler;
import net.mcsrvapi.main.api.server.ServerSettings;
import net.mcsrvapi.main.api.util.RegisterUtil;
import net.mcsrvapi.misc.databasehandler.DatabaseHandler;
import net.mcsrvapi.misc.translationhandler.TranslationHandler;
import net.mcsrvapi.misc.translationhandler.translations.Language;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for our API plugin.
 * @since 0.0.1
 */
public class McSrvAPI extends JavaPlugin {

    private static McSrvAPI instance;
    private static final String PREFIX = "§6§lServer §8» ";
    //private CloudClient cloudClient;
    private DatabaseHandler dbHandler;
    private CommandHandler commandHandler;
    private PlayerHandler playerHandler;
    private ActionbarManager actionbarManager;
    private ServerSettings serverSettings;
    private InventoryHandler inventoryHandler;

    private Language apiLanguage;

    @Override
    public void onEnable() {
        instance = this;
        //cloudClient = CloudClient.getInstance();
        //cloudClient.getCloudEventManager().addCloudListener(new CloudEvents());
        dbHandler = DatabaseHandler.getInstance();
        commandHandler = new CommandHandler();
        playerHandler = new PlayerHandler();
        actionbarManager = new ActionbarManager();
        inventoryHandler = new InventoryHandler();
        this.serverSettings = new ServerSettings();

        apiLanguage = TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("en");
        new EnglishMessages();

        RegisterUtil registerUtil = new RegisterUtil(this);
        registerUtil.addCommands(new StopCommand(), new ClearChatCommand(), new GameModeCommand(),
                new GlobalmuteCommand(), new BuildCommand(), new FlyCommand());

        registerUtil.addListeners(new CommandListener(), new LoginListener(), new LeaveListener(),
                new ChatListener(), new FoodLevelListener(), new BlockListener(), new ItemListener(),
                new DamageListener(), new CreatureSpawnListener(), new WeatherChangeListener(),
                new ServerSettingChangeListener(), new VehicleDestroyListener(), new PlayerInteractEntityListener(),
                new ItemConsumeListener(), new InventoryListener());

        registerUtil.register();

        this.serverSettings.loadDefaultSettings();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this,
                () -> Bukkit.getPluginManager().callEvent(new AsyncAPITickEvent()), 0, 20L);
    }

    /**
     * Gets the instance of this plugin.
     *
     * @return Main - the instance.
     * @since 0.0.1
     */
    public static McSrvAPI getInstance() {
        return instance;
    }

    /**
     * Gets the instance of the cloud client.
     * @return CloudClient - the cloud client instance.
     * @since 0.0.1
     *
    public CloudClient getCloudClient(){
    return cloudClient;
    }
     */

    /**
     * Gets the instance of the db handler.
     *
     * @return DatabaseHandler
     * @since 0.0.1
     */
    public DatabaseHandler getDbHandler() {
        return dbHandler;
    }

    /**
     * Gets the instance of the command handler.
     *
     * @return CommandHandler
     * @since 0.0.1
     */
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    /**
     * Gets the instance of the player handler.
     *
     * @return PlayerHandler
     * @since 0.0.1
     */
    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    /**
     * Gets the language of the api.
     * Used for console outputs for instance.
     *
     * @return Language - the {@link Language} instance.
     * @since 0.0.1
     */
    public Language getAPILanguage() {
        return this.apiLanguage;
    }

    /**
     * Gets the instance of the action bar manager
     *
     * @return ActionbarManager
     * @since 0.0.1
     */
    public ActionbarManager getActionbarManager() {
        return actionbarManager;
    }

    /**
     * Gets the global API prefix.
     *
     * @return String - the prefix.
     * @since 0.0.1
     */
    public static String getPrefix() {
        return PREFIX;
    }

    /**
     * Gets the instance of server settings.
     *
     * @return ServerSettings - the settings.
     * @since 0.0.1
     */
    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    /**
     * Gets the instance of the inventory handler.
     * @return InventoryHandler - the inventory handler.
     * @since 0.0.1
     */
    public InventoryHandler getInventoryHandler() {
        return inventoryHandler;
    }
}
