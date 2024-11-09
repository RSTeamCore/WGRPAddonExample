package net.ritasister;

import net.ritasister.listener.YourListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WGRPAddonExample extends JavaPlugin {

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new YourListener(), this);
    }

    public void onDisable() {

    }
}
