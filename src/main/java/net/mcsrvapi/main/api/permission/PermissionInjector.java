package net.mcsrvapi.main.api.permission;

import net.mcsrvapi.main.api.McSrvAPI;
import net.mcsrvapi.main.api.util.Utils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissibleBase;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Injector to change spigot permission behaviour.
 * @since 0.0.1
 */
public class PermissionInjector {

    private final String LOGGER_PREFIX = "[PermissionInjector] ";

    private final String className;
    private final String fieldName;

    /**
     * Creates an instance of the permission injector.
     * @param className String - the name of the class to get.
     * @param fieldName Field - the target field.
     */
    public PermissionInjector(String className, String fieldName){
        this.className = className;
        this.fieldName = fieldName;
    }

    /**
     * Injects into the spigot permission system for a specific player.
     * @param player Player - the player.
     * @param permissible Permissible - the new Permissible instance.
     * @see Permissible
     * @since 0.0.1
     */
    public void inject(Player player, Permissible permissible){
        try{
            Field permissionField = Utils.getBukkitOrMinecraftClass(true, className).getDeclaredField(fieldName);
            permissionField.setAccessible(true);

            PermissibleBase oldPermissible = (PermissibleBase) permissionField.get(player);
            PermissibleBase newPermissible = (PermissibleBase) permissible;

            copyValues(oldPermissible, newPermissible);
            permissionField.set(player, permissible);
        } catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e){
            McSrvAPI.getInstance().getLogger().warning(LOGGER_PREFIX + e.getMessage());
        }
    }

    /**
     * Copies all relevant information from one permissible to another.
     * @param oldPermissible Permissible - the Permissible instance to copy from.
     * @param newPermissible Permissible - the Permissible instance to copy to.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @since 0.0.1
     */
    private void copyValues(PermissibleBase oldPermissible, PermissibleBase newPermissible) throws NoSuchFieldException, IllegalAccessException {
        Field attachmentField = PermissibleBase.class.getDeclaredField("attachments");
        attachmentField.setAccessible(true);

        List<Object> attachmentPerms = (List) attachmentField.get(newPermissible);
        attachmentPerms.clear();
        attachmentPerms.addAll((List) attachmentField.get(oldPermissible));
        newPermissible.recalculatePermissions();
    }
}
