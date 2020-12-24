package com.github.hage.witchvsvillager.game;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import java.util.HashMap;
import java.util.stream.Collectors;

public class GameManager {

    @Getter
    private static final HashMap<Integer, WVVPlayer> JOINED_PLAYERS = Maps.newHashMap();

    public void swapSkin() {
        JOINED_PLAYERS.values().stream().filter(WVVPlayer::isAlive).map(wvvPlayer -> (CraftPlayer) wvvPlayer.getPlayer()).forEach(skin -> {
            skin
        });
    }
}
