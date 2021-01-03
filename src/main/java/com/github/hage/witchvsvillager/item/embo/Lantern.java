package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Lantern extends CustomItem implements Clickable, Purchasable {

    @Override
    public void onClick(WVVPlayer player) {

    }

    @Override
    protected ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.JACK_O_LANTERN, "&eランタン", "", "&760秒間周囲が明るく見やすくなる");
    }

    @Override
    public int getPrice() {
        return 3;
    }
}
