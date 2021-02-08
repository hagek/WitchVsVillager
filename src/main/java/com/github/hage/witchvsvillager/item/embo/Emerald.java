package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Emerald extends CustomItem {

    public Emerald() {
        super("emerald");
    }

    @Override
    protected ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.EMERALD, "&aエメラルド", "", "&7ゲーム内通貨", "&7これと引き換えにショップで戦利品を購入できる");
    }
}
