package net.mcsrvapi.main.api.permission;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a single permission group.
 * Will be loaded from the database.
 * @since 0.0.1
 */
public class PermissionGroup {

    private final String groupName;
    private final int groupId;

    private final List<String> permissions;
    private final List<String> fullPermissions;
    private final String prefix;
    private final List<PermissionGroup> parents;
    private final List<PermissionGroup> children;
    private final int priority;

    /**
     * Constructor for a single PermissionGroup.
     * @param groupName String - the name of the group.
     * @param groupId int - the id of the group.
     * @since 0.0.1
     */
    public PermissionGroup(String groupName, int groupId){
        this.groupName = groupName;
        this.groupId = groupId;

        permissions = new ArrayList<>();
        fullPermissions = new ArrayList<>();
        prefix = "";
        parents = new ArrayList<>();
        children = new ArrayList<>();
        priority = 0;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public List<String> getFullPermissions() {
        return fullPermissions;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPriority() {
        return priority;
    }

    public List<PermissionGroup> getChildren() {
        return children;
    }

    public List<PermissionGroup> getParents() {
        return parents;
    }
}
