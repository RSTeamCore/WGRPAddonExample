package net.ritasister.listener;

import net.kyori.adventure.text.Component;
import net.ritasister.wgrp.api.WorldGuardRegionProtect;
import net.ritasister.wgrp.api.WorldGuardRegionProtectProvider;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class YourListener implements Listener {

    /**
     * Examples using on another listeners with WorldGuardRegionProtect API
     */
    @EventHandler
    private void onBreakBlock(@NotNull BlockBreakEvent event) {
        WorldGuardRegionProtect api = WorldGuardRegionProtectProvider.get();
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if(api.getRegionAdapter().checkStandingRegion(location)) {
            player.sendMessage(Component.text("You can't break block here"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onPlayerMove(@NotNull PlayerMoveEvent event) {
        WorldGuardRegionProtect api = WorldGuardRegionProtectProvider.get();
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if(api.getRegionAdapter().checkStandingRegion(to) && api.getRegionAdapter().checkStandingRegion(from)) {
            player.sendMessage(Component.text("You are joining this region!"));
        } else {
            player.sendMessage(Component.text("You are leave from this region!"));
        }
    }

    @EventHandler
    private void onEntityDamage(@NotNull EntityDamageByEntityEvent event) {
        WorldGuardRegionProtect api = WorldGuardRegionProtectProvider.get();
        if(event.getDamager() instanceof Player damager) {
            if(api.getRegionAdapter().checkStandingRegion(damager.getLocation())) {
                event.setCancelled(true);
                damager.sendMessage(Component.text("You can't harm anyone!!! :<"));
            }
        }
    }
}
