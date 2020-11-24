package com.github.hage.witchvsvillager.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BukkitUtil {

    public static ItemStack newItem(Material material, String name, int amount, boolean unbreakable, String... lore) {
        return new ItemBuilder().setMaterial(material)
                .setName(name)
                .setAmount(amount)
                .setLore(lore)
                .setUnbreakable(unbreakable)
                .build();
    }

    public static ItemStack newItem(Material material, String name, String... lore) {
        return newItem(material, name, 1, false, lore);
    }

    public static ItemStack newItem(Material material, String name, boolean unbreakable, String... lore) {
        return newItem(material, name, 1, unbreakable, lore);
    }
}
