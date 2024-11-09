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

        if(api.getRegionAdapter().checkStandingRegion(to)) {
            player.sendMessage(Component.text("You are joining this region!"));
        } else if(api.getRegionAdapter().checkStandingRegion(to)) {
            player.sendMessage(Component.text("You are leave from this region!"));
        }
    }

    @EventHandler
    private void onEntityDamage(@NotNull EntityDamageByEntityEvent event) {
        WorldGuardRegionProtect api = WorldGuardRegionProtectProvider.get();
        Player damager = (Player) event.getDamager();
        if(api.getRegionAdapter().checkStandingRegion(damager)) {
            event.setCancelled(true);
            damager.sendMessage(Component.text("You can't harm anyone!!! :<"));
        }
    }
}
