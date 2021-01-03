package com.github.hage.witchvsvillager.game;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameManager {

    private static final HashMap<Player, WVVPlayer> JOINED_PLAYERS = Maps.newHashMap();

    public static WVVPlayer fromBukkitPlayer(Player player) {
        return JOINED_PLAYERS.get(player);
    }

    public void join(Player player) {

    }

    public void leave(WVVPlayer player) {

    }

    public void swapSkin() {

    }
}
