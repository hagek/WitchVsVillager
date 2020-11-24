package com.github.hage.witchvsvillager.game;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.HashMap;
import java.util.stream.Collectors;

public class GameManager {

    @Getter
    private static final HashMap<Integer, WVVPlayer> JOINED_PLAYERS = Maps.newHashMap();

    public void swapSkin() {
        JOINED_PLAYERS.values().stream().filter(WVVPlayer::isAlive).map(wvvPlayer -> (CraftPlayer) wvvPlayer.getPlayer()).collect(Collectors.toList()).forEach(skin -> {
            skin
        });
    }
}
