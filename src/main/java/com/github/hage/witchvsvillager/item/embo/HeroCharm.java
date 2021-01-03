package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HeroCharm extends Charm {

    @Override
    public void onClick(WVVPlayer player) {

    }

    @Override
    protected ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.END_CRYSTAL, "&a豪傑のお守り", "", "");
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
