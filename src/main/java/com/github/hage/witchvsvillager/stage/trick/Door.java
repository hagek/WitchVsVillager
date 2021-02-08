package com.github.hage.witchvsvillager.stage.trick;

import com.github.hage.witchvsvillager.item.ItemRegister;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public class Door extends Trick {

    public Door(org.bukkit.material.Door block) {
        super((Block) block);
    }

    @Override
    protected void doAction(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().equals(ItemRegister.getById("key"))) {
            //TODO ドアを開けて閉める
        }
    }
}
