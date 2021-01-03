package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.google.common.collect.Maps;
import lombok.Setter;

import java.util.HashMap;

public abstract class Charm extends CustomItem implements Clickable, Purchasable {
    @Setter
    private static final HashMap<WVVPlayer, Class<? extends Charm>> STATUS = Maps.newHashMap();

    public static boolean check(WVVPlayer player) {
        return STATUS.containsKey(player) && STATUS.get(player) != null;
    }

}
