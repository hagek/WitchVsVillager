package com.github.hage.witchvsvillager.stage.trick;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public class Chest extends Trick {

    public Chest(org.bukkit.block.Chest block) {
        super((Block) block);
    }

    @Override
    public void doAction(PlayerInteractEvent event) {
        //TODO ランダムにルートをプレイヤーに与える
    }
}
