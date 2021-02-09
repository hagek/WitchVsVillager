package com.github.hage.witchvsvillager.stage.trick;

import com.github.hage.witchvsvillager.game.GameManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public abstract class Trick implements Listener {
    protected final Block block;

    @EventHandler
    void onTriggered(PlayerInteractEvent event) {
        if (event.getClickedBlock().equals(this.block) && GameManager.isGaming() && GameManager.isJoined(event.getPlayer())) {
            this.doAction(event);
        }
    }

    protected abstract void doAction(PlayerInteractEvent event);

}
