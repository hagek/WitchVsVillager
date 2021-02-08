package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.github.hage.witchvsvillager.util.BukkitUtil;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GodBless extends CustomItem implements Clickable, Purchasable {
    private static final HashMap<WVVPlayer, Integer> COUNT = Maps.newHashMap();

    public GodBless() {
        super("god_bless");
    }

    public static int getCount(WVVPlayer wvvPlayer) {
        return COUNT.get(wvvPlayer);
    }

    public static void setCount(WVVPlayer wvvPlayer) {
        COUNT.put(wvvPlayer, 3);
    }

    public static void reduceCount(WVVPlayer wvvPlayer) {
        COUNT.put(wvvPlayer, COUNT.get(wvvPlayer) - 1);
    }

    @Override
    public void onClick(WVVPlayer player) {
        //TODO 効果付与
    }

    @Override
    protected ItemStack getItemStack() {
        return BukkitUtil.newItem(Material.GLOWSTONE_DUST, "&e神の加護", "", "&73回まで攻撃を無効化する");
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
