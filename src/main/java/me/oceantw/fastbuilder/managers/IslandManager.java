package me.oceantw.fastbuilder.managers;

import me.oceantw.fastbuilder.FastBuilder;
import org.bukkit.Location;

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IslandManager {

    HashMap<String, Integer> islandList = new HashMap<>();
    List<Integer> freeIslands = new ArrayList<>();

    public void loadIslands() {
        // Load islands from file
        int i = 1;
        for (String island: FastBuilder.getInstance().getIslands().getConfig().getStringList("islands")) {
            islandList.put(island, i);
            freeIslands.add(i);
            i++;
        }
    }

    public void saveIslands() {
        // Save islands to file
        ArrayList<String> islands = new ArrayList<>(islandList.keySet());
        FastBuilder.getInstance().getIslands().getConfig().set("islands", islands);
        FastBuilder.getInstance().getIslands().saveConfig();
    }

    public int getFirstAvailableIsland() {
        // Get the first available island
        for (int i = 1; i < FastBuilder.getInstance().getIslands().getConfig().getStringList("islands").size(); i++) {
            if (!freeIslands.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public void removeFreeIsland(int island) {
        // Remove an island from the free islands list
        for (int i = 0; i < freeIslands.size(); i++) {
            if (freeIslands.get(i) == island) {
                freeIslands.remove(i);
                break;
            }
        }
    }

    public Location getLocationFromString(String island) {
        // Format: world:x:y:z:yaw:pitch
        String[] parts = island.split(":");
        return new Location(FastBuilder.getInstance().getServer().getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]));
    }

}
