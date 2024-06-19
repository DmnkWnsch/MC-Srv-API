package net.mcsrvapi.main.api.server;

/**
 * Enum to define different server settings which can be enabled or disabled.
 * @since 0.0.1
 */
public enum ServerSetting {

    DEFAULT_CHAT (true), //TODO
    DEFAULT_TAB (true), //TODO
    CHAT (true),

    FOOD_LEVEL_CHANGE (false),
    PVP (false),
    ENTITY_DAMAGE (false),
    FALL_DAMAGE (false),
    ITEM_DROP (false),
    ITEM_PICKUP (false),
    BLOCK_BREAK (false),
    BLOCK_PLACE (false),
    VEHICLE_DESTROY (false), //TODO
    PLAYER_INTERACT_ENTITY (true), //TODO
    ITEM_CONSUME (false), //TODO

    WEATHER_CHANGE (false),
    TIME_CHANGE (false),
    CREATURE_SPAWN (false);

    private final boolean defaultValue;

    ServerSetting(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
