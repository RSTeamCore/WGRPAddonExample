package net.ritasister;

import net.ritasister.listener.YourListener;
import net.ritasister.wgrp.api.WorldGuardRegionProtect;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class WGRPAddonExample extends JavaPlugin {

    private WorldGuardRegionProtect wgrpApi;

    @Override
    public void onEnable() {
        //You must need register plugin by using service provider.
        RegisteredServiceProvider<WorldGuardRegionProtect> provider = this.getServer().getServicesManager().getRegistration(WorldGuardRegionProtect.class);
        if (provider != null) {
            wgrpApi = provider.getProvider();
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new YourListener(this), this);

    }

    public WorldGuardRegionProtect getWgrpApi() {
        return wgrpApi;
    }

    @Override
    public void onDisable() {

    }
}
