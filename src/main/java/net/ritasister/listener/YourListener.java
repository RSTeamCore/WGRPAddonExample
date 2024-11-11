package net.ritasister.listener;

import net.kyori.adventure.text.Component;
import net.ritasister.WGRPAddonExample;
import net.ritasister.wgrp.api.WorldGuardRegionProtect;
import net.ritasister.wgrp.api.WorldGuardRegionProtectProvider;
import net.ritasister.wgrp.api.manager.regions.RegionAdapterManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class YourListener implements Listener {

    private WGRPAddonExample plugin;

    public YourListener(WGRPAddonExample plugin) {
        this.plugin = plugin;
    }

    /**
     * Examples using on another listeners with WorldGuardRegionProtect API
     */
    @EventHandler
    private void onBreakBlock(@NotNull BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();
        RegionAdapterManager regionAdapterManager = plugin.getWgrpApi().getRegionAdapter();

        if(regionAdapterManager.checkStandingRegion(location)) {
            player.sendMessage(Component.text("You can't break block here"));
            event.setCancelled(true);
        }

    }

    @EventHandler
    private void onEntityDamage(@NotNull EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player damager) {
            RegionAdapterManager regionAdapterManager = plugin.getWgrpApi().getRegionAdapter();

            if(regionAdapterManager.checkStandingRegion(damager.getLocation())) {
                event.setCancelled(true);
                damager.sendMessage(Component.text("You can't harm anyone!!! :<"));
            }
        }
    }
}
