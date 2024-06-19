package net.mcsrvapi.main.api.permission;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class to overwrite default PermissibleBase behavior.
 * @see org.bukkit.permissions.PermissibleBase
 * @since 0.0.1
 */
public class PermissionBase extends PermissibleBase {

    private List<String> permissions;

    /**
     * Creates an instance of the permission base for a given player.
     * @param player Player - the target player.
     */
    public PermissionBase(Player player) {
        super(player);

        permissions = new ArrayList<>();
        addEffectivePermissions();
    }

    /**
     * Checks whether a specific permission is granted or not.
     * @param permission String - the name of the permission.
     * @return True/False
     * @since 0.0.1
     */
    @Override
    public boolean hasPermission(String permission){
        if(permissions.contains("-" + permission.toLowerCase()))
            return false;
        else return permission.contains("*") || permissions.contains(permission.toLowerCase());
    }

    /**
     * Sets all permissions.
     * @param permissions ArrayList<String> - a list of permissions.
     * @since 0.0.1
     */
    public void setPermissions(ArrayList<String> permissions){
        this.permissions = (List<String>) permissions.clone();
        addEffectivePermissions();
    }

    /**
     * Adds the effective permissions to the current permission list.
     * @since 0.0.1
     */
    private void addEffectivePermissions(){
        super.getEffectivePermissions().stream().filter(PermissionAttachmentInfo::getValue).forEach(perm -> permissions.add(perm.getPermission().toLowerCase()));
    }
}
