package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Lootable;
import com.github.hage.witchvsvillager.item.Purchasable;
import org.bukkit.inventory.ItemStack;

public class Key extends CustomItem implements Purchasable, Lootable {

    public Key(String id) {
        super("key");
    }

    @Override
    public int getWeight() {
        return 0; //TODO 仮値
    }

    @Override
    public int getPrice() {
        return 0; //TODO 仮値
    }

    @Override
    protected ItemStack getItemStack() {
        return null;
    }
}
