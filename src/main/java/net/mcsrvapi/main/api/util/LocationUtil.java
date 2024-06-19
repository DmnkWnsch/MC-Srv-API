package net.mcsrvapi.main.api.util;

import org.bukkit.Location;

/**
 * Utility class for working with locations.
 * @since 0.0.1
 */
public class LocationUtil {

    private LocationUtil() {
        throw new UnsupportedOperationException("This is a utility class.");
    }

    public static boolean equalsLocation(Location loc1, Location loc2) {
        return (loc1.getX() == loc2.getX() && loc1.getY() == loc2.getY() && loc1.getZ() == loc2.getZ());
    }

}
