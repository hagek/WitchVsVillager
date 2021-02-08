package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WoodenSword extends CustomItem implements Purchasable {

    public WoodenSword() {
        super("wooden_sword");
    }

    @Override
    public int getPrice() {
        return 3;
    }

    @Override
    public ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.WOOD_SWORD, "&8木の剣", true, "", "&7ただの木の剣", "素手で戦うよりはましかもしれない");
    }
}
