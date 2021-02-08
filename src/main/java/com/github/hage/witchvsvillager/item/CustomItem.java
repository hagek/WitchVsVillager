package com.github.hage.witchvsvillager.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
@Getter
public abstract class CustomItem {
    protected final String id;

    protected abstract ItemStack getItemStack();
}
