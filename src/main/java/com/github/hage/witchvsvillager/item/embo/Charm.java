package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.google.common.collect.Maps;

import java.util.HashMap;

public abstract class Charm extends CustomItem implements Clickable, Purchasable {
    private static final HashMap<WVVPlayer, Class<? extends Charm>> STATUS = Maps.newHashMap();

    public static Class<? extends Charm> getStatus(WVVPlayer wvvPlayer) {
        return STATUS.get(wvvPlayer);
    }

    public static void setStatus(WVVPlayer wvvPlayer, Class<? extends Charm> clazz) {
        STATUS.put(wvvPlayer, clazz);
    }

    public static void clearStatus(WVVPlayer wvvPlayer) {
        STATUS.remove(wvvPlayer);
    }
}
