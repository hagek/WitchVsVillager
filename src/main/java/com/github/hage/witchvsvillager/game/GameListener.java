package com.github.hage.witchvsvillager.game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onDeath(EntityDamageEvent ev) {
        if (ev.getEntity() instanceof Player) {
            Player victim = (Player) ev.getEntity();
            WVVPlayer wvvPlayer = WVVPlayer.getFromBukkitPlayer(victim);
            wvvPlayer.onDeath();
        }
    }
}
