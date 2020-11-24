package com.github.hage.witchvsvillager.game;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class WVVPlayer {
    private static final Map<Player, WVVPlayer> PLAYERS = new HashMap<>();

    @Getter
    private Player player;
    @Getter
    private WVVPlayer disguisedAs;
    @Getter
    private Role role;
    @Getter
    private int number;
    @Getter
    private AngelType angel;
    @Getter
    private int kills;
    @Getter
    private boolean alive;

    public static WVVPlayer getFromBukkitPlayer(Player player) {
        return PLAYERS.get(player);
    }

    public void onDeath() {

    }

    public void changeSkin() {

    }

    enum AngelType {
        NORMAL,
        FALLEN,
        NONE;
    }
}
