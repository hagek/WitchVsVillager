package com.github.hage.witchvsvillager.stage.trick;

import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.event.player.PlayerInteractEvent;

public class Can extends Trick {

    public Can(Dispenser block) {
        super((Block) block);
    }

    @Override
    protected void doAction(PlayerInteractEvent event) {
        //TODO ゴミ箱オープン
    }
}
