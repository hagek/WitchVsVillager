package com.github.hage.witchvsvillager.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack target;

    public ItemBuilder setMaterial(Material material) {
        this.target.setType(material);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.target.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        this.target.getItemMeta().setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.target.getItemMeta().setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder glow() {
        return this.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.target.getItemMeta().addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean flag) {
        this.target.getItemMeta().setUnbreakable(flag);
        return this;
    }

    public ItemStack build() {
        return this.target;
    }
}
