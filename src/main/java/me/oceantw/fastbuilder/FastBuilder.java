package me.oceantw.fastbuilder;

import lombok.Getter;
import me.oceantw.fastbuilder.utils.YmlMaker;
import org.bukkit.plugin.java.JavaPlugin;

public class FastBuilder extends JavaPlugin {

    @Getter
    static FastBuilder instance;

    @Getter
    private YmlMaker cfg;
    @Getter
    private YmlMaker islands;

    @Override
    public void onEnable() {
        getLogger().info("FastBuilder has been enabled!");
        getLogger().info("Loading config...");
        cfg = new YmlMaker(this, "config.yml");
        cfg.saveDefaultConfig();
        islands = new YmlMaker(this, "islands.yml");
        islands.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("FastBuilder has been disabled!");
    }
}