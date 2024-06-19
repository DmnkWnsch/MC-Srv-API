package net.mcsrvapi.main.api.hologram;

import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to create holograms.
 * @since 0.0.1
 */
public class APIHologram {

    private final Location spawnLocation;
    private final int chunkID;

    private List<String> lines;
    private final List<EntityArmorStand> armorStands;

    /**
     * Create the hologram object.
     * @param location Location - the location to spawn the hologram.
     * @param lines List<String> - the text to display.
     * @since 0.0.1
     */
    public APIHologram(Location location, List<String> lines) {
        this.spawnLocation = location;
        this.chunkID = location.getChunk().getX() * 30000000 + location.getChunk().getZ();
        this.lines = lines;
        this.armorStands = new ArrayList<>();
    }

    /**
     * Gets the location where the hologram will be spawned.
     * @return Location
     * @since 0.0.1
     */
    public Location getLocation() {
        return spawnLocation;
    }

    /**
     * Gets the lines which are displayed by the hologram.
     * @return List<String> - the lines
     * @since 0.0.1
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * Gets the chunk id where the hologram is located.
     * @return int - the chunk id.
     * @since 0.0.1
     */
    public int getChunkID() {
        return chunkID;
    }

    /**
     * Sets the lines which should be displayed by the hologram.
     * @param lines List<String> - the lines to display.
     * @since 0.0.1
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
        createArmorStands();
    }

    /**
     * Sets the lines which should be displayed by the hologram.
     * @param lines String[] - the lines to display.
     * @since 0.0.1
     */
    public void setLines(String... lines) {
        setLines(Arrays.asList(lines));
    }

    /**
     * Adds a line to the lines which should be displayed.
     * @param line String - the line to add.
     * @since 0.0.1
     */
    public void addLine(String line) {
        this.lines.add(line);
        createArmorStands();
    }

    /**
     * Gets all armor stands which are stored in the hologram.
     * If a new line was added they will be re-created before.
     * @return List<EntityArmorStand> - the list of entity armor stands.
     * @since 0.0.1
     */
    public List<EntityArmorStand> getArmorStands() {
        if (armorStands.size() != lines.size())
            createArmorStands();

        return armorStands;
    }

    /**
     * Creates the armor stands for a given location with a Y difference of 0.25D for each line.
     * @since 0.0.1
     */
    private void createArmorStands() {
        this.armorStands.clear();

        Location location = new Location(spawnLocation.getWorld(), spawnLocation.getX(), spawnLocation.getY() - 1.75, spawnLocation.getZ());
        for (int i = lines.size() - 1; i >= 0; i--) {
            String currentLine = lines.get(i);
            WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
            EntityArmorStand entityArmorStand = new EntityArmorStand((World) worldServer);
            entityArmorStand.setCustomName(currentLine);
            entityArmorStand.setCustomNameVisible(true);
            entityArmorStand.setInvisible(true);
            entityArmorStand.setNoGravity(true);
            entityArmorStand.setLocation(location.getX(), location.getY(), location.getZ(), 0.0F, 0.0F);
            this.armorStands.add(entityArmorStand);
            location.add(0.0D, 0.25D, 0.0D);
        }
    }

}
