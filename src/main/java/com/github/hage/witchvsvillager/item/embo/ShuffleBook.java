package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShuffleBook extends CustomItem implements Clickable {

    public ShuffleBook() {
        super("shuffle_book");
    }

    @Override
    public void onClick(WVVPlayer player) {
        //TODO スキンをシャッフル
    }

    @Override
    public ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.BOOK, "&5シャッフル", "", "&7");
    }
}
