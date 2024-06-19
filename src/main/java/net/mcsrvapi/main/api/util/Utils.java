package net.mcsrvapi.main.api.util;

import net.mcsrvapi.main.api.McSrvAPI;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Utils class to provide static methods for various mechanics.
 * @since 0.0.1
 */
public class Utils {

    private static final String LOGGER_PREFIX = "[Utils] ";

    private Utils(){
        // Needed to prevent useless instantiation of this class.
    }

    /**
     * Gets the perfect size for a minecraft inventory.
     * @param minimumSlots Integer - the minimum slots which are needed.
     * @return Integer - the size for the inventory.
     * @since 0.0.1
     */
    public static int getPerfectInventorySize(int minimumSlots) {
        int mod = minimumSlots % 9;
        if (mod == 0)
            return minimumSlots;

        return minimumSlots - mod + 9;
    }

    /**
     * Gets the PlayerConnection of a given player.
     * @see PlayerConnection
     * @param player Player - the player to get the connection from.
     * @return PlayerConnection - the connection.
     */
    public static PlayerConnection getPlayerConnection(Player player) {
        return ((CraftPlayer) player).getHandle().playerConnection;
    }

    /**
     * Gets a class from the bukkit or minecraft packages.
     * @param isBukkit boolean - Whether a bukkit or minecraft class is wanted.
     * @param className String - the class name.
     * @return Class<?> - the wanted class or null.
     * @since 0.0.1
     */
    public static Class<?> getBukkitOrMinecraftClass(boolean isBukkit, String className){
        String fullName = (isBukkit ? "org.bukkit.craftbukkit." : "net.minecraft.server.") + getBukkitVersion() + className;
        Class<?> clazz = null;
        try{
            clazz = Class.forName(fullName);
        } catch(ClassNotFoundException e){
            McSrvAPI.getInstance().getLogger().warning(LOGGER_PREFIX + e.getMessage());
        }
        return clazz;
    }

    /**
     * Gets the current bukkit/spigot version running on the server.
     * @return String - the version string.
     * @since 0.0.1
     */
    private static String getBukkitVersion(){
        String name = Bukkit.getServer().getClass().getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1) + ".";
    }
}
