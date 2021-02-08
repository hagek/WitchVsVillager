package com.github.hage.witchvsvillager.item.embo;

import com.github.hage.witchvsvillager.game.WVVPlayer;
import com.github.hage.witchvsvillager.item.Clickable;
import com.github.hage.witchvsvillager.item.CustomItem;
import com.github.hage.witchvsvillager.item.Purchasable;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public abstract class Charm extends CustomItem implements Clickable, Purchasable {
    private static final HashMap<WVVPlayer, Class<? extends Charm>> STATUS = Maps.newHashMap();

    public Charm(String id) {
        super(id);
    }

    public static boolean check(WVVPlayer player) {
        return STATUS.containsKey(player) && STATUS.get(player) != null;
    }

}
