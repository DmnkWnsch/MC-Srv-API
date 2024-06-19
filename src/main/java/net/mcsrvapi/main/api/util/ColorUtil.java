package net.mcsrvapi.main.api.util;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;

/**
 * Class to convert different color types.
 * @since 0.0.1
 */
public class ColorUtil {

    private ColorUtil() {
        throw new UnsupportedOperationException("This is a util class.");
    }

    /**
     * Convert the given chatColor to a dye color
     * @param chatColor ChatColor - Color to convert
     * @return DyeColor - The converted DyeColor
     */
    public static DyeColor convertChatToDyeColor(ChatColor chatColor) {
        if (chatColor == null)
            return DyeColor.WHITE;

        switch (chatColor) {
            case AQUA:
                return DyeColor.CYAN;
            case BLUE:
            case DARK_BLUE:
                return DyeColor.BLUE;
            case LIGHT_PURPLE:
                return DyeColor.MAGENTA;
            case DARK_PURPLE:
                return DyeColor.PURPLE;
            case DARK_GREEN:
                return DyeColor.GREEN;
            case GREEN:
                return DyeColor.LIME;
            case YELLOW:
                return DyeColor.YELLOW;
            case GOLD:
                return DyeColor.ORANGE;
            case GRAY:
                return DyeColor.SILVER;
            case DARK_GRAY:
                return DyeColor.GRAY;
            case RED:
            case DARK_RED:
                return DyeColor.RED;
            default:
                return DyeColor.WHITE;
        }
    }

    /**
     * Convert the given DyeColor to a ChatColor
     * @param dyeColor DyeColor - Color to convert
     * @return ChatColor - The converted ChatColor
     */
    public static ChatColor convertDyeToChatColor(DyeColor dyeColor) {
        if (dyeColor == null)
            return ChatColor.WHITE;

        switch (dyeColor) {
            case CYAN:
                return ChatColor.AQUA;
            case LIGHT_BLUE:
                return ChatColor.BLUE;
            case BLUE:
                return ChatColor.DARK_BLUE;
            case MAGENTA:
                return ChatColor.LIGHT_PURPLE;
            case PURPLE:
                return ChatColor.DARK_PURPLE;
            case GREEN:
                return ChatColor.DARK_GREEN;
            case LIME:
                return ChatColor.GREEN;
            case YELLOW:
                return ChatColor.YELLOW;
            case ORANGE:
                return ChatColor.GOLD;
            case SILVER:
                return ChatColor.GRAY;
            case GRAY:
                return ChatColor.DARK_GRAY;
            case PINK:
                return ChatColor.RED;
            case RED:
                return ChatColor.DARK_RED;
            default:
                return ChatColor.WHITE;
        }
    }

    protected static Color convertChatColorToBukkitColor(ChatColor chatColor) {
        if (chatColor == null)
            return Color.WHITE;
        switch (chatColor) {
            case AQUA:
                return Color.AQUA;
            case BLACK:
                return Color.BLACK;
            case BLUE:
            case DARK_AQUA:
            case DARK_BLUE:
                return Color.BLUE;
            case DARK_GRAY:
            case GRAY:
                return Color.GRAY;
            case DARK_GREEN:
            case GREEN:
                return Color.GREEN;
            case DARK_PURPLE:
            case LIGHT_PURPLE:
                return Color.PURPLE;
            case DARK_RED:
            case RED:
                return Color.RED;
            case GOLD:
            case YELLOW:
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }

}
