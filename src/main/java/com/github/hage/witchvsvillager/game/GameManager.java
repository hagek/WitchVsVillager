package com.github.hage.witchvsvillager.game;

import com.github.hage.witchvsvillager.WitchVsVillager;
import com.github.hage.witchvsvillager.util.PerformanceUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private static final int MINIMUM_NUMBER = 5;
    private static final List<Player> WAITING_PLAYERS = Lists.newArrayList();
    private static final HashMap<Player, WVVPlayer> GAME_PLAYERS = Maps.newHashMap();
    private static GameState gameState;
    private static WVVRunnable gameRunnable;

    public static WVVPlayer fromBukkitPlayer(Player player) {
        return GAME_PLAYERS.get(player);
    }

    public static void join(Player player) {
        if (gameState == GameState.WAITING_FOR_PLAYERS || gameState == GameState.WAITING_TO_START) {
            if (!WAITING_PLAYERS.contains(player)) {
                WAITING_PLAYERS.add(player);
            } else {
                PerformanceUtil.sendMessage(player, "&c既に参加しています");
            }
        } else {
            PerformanceUtil.sendMessage(player, "&c現在は参加できません");
        }
    }

    public static void cancelGame() {
        if (gameState == GameState.WAITING_TO_START) {
            PerformanceUtil.sendMessage(GameListener.Filters.GAME, String.format("&c必要人数の%d人を下回ったため, ゲームの開始がキャンセルされました", MINIMUM_NUMBER));
        } else {
            throw new IllegalStateException("開始待ちのときのみcancel可能");
        }
    }

    public static void leave(Player player) {
        switch (gameState) {
            case WAITING_FOR_PLAYERS:
                break;
            case WAITING_TO_START:
                if (GAME_PLAYERS.size() < MINIMUM_NUMBER) {
                    cancelGame();
                }
                break;
            default:

        }
    }

    public static void start() {

    }

    public static void stop() {
        GAME_PLAYERS.values().stream().map(WVVPlayer::getPlayer).forEach(player -> player.teleport(WitchVsVillager.lobby().getLocation()));
        gameState = GameState.WAITING_FOR_PLAYERS;
    }

    public static void gameSet(Team winner) {
        PerformanceUtil.sendTitle(GameListener.Filters.GAME, winner.getPerfix() + "の勝利", "&6&l&nGAME SET", 10, 100, 10);
        gameRunnable.cancel();
        (gameRunnable = new EndingRunnable()).runTaskTimer(WitchVsVillager.inst(), 20, 0);
    }

    public static boolean isGaming() {
        return gameState != GameState.WAITING_TO_START && gameState != GameState.WAITING_FOR_PLAYERS;
    }

    public static boolean isJoined(Player player) {
        return GAME_PLAYERS.containsKey(player);
    }

    public static void swapSkin() {

    }

    public enum GameState {
        GAMING,
        WAITING_FOR_PLAYERS,
        WAITING_TO_START,
        ENDING,
        UNKNOWN
    }

    abstract static class WVVRunnable extends BukkitRunnable {
    }

    static class StartRunnable extends WVVRunnable {
        private int time = 20;

        public StartRunnable() {
            gameState = GameState.WAITING_TO_START;
        }

        @Override
        public void run() {
            if (--this.time % 10 == 0 || this.time <= 5) {
                PerformanceUtil.playSound(GameListener.Filters.GAME, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                PerformanceUtil.sendMessage(GameListener.Filters.GAME, "&7ゲーム開始まで&e" + this.time + "&7秒");
                if (this.time == 0) {
                    this.cancel();
                    (gameRunnable = new GameRunnable()).runTaskTimer(WitchVsVillager.inst(), 20, 0);
                }
            }
        }


    }

    static class GameRunnable extends WVVRunnable {
        private int timeLeft = 500;

        public GameRunnable() {
            gameState = GameState.GAMING;
        }

        @Override
        public void run() {
            this.timeLeft--;
            if (this.timeLeft == 500) {
                PerformanceUtil.sendTitle(GameListener.Filters.GAME, "&c&l&nGAME STARTED", "&7Witch vs Villager", 0, 100, 10);
            } else if (this.timeLeft == 0) {
                //TODO 生き残ったので村人の勝利
            }
        }
    }

    static class EndingRunnable extends WVVRunnable {
        private int timeLeft = 10;

        public EndingRunnable() {
            gameState = GameState.ENDING;
        }

        @Override
        public void run() {
            this.timeLeft--;
            if (this.timeLeft == 10) {
                //TODO 結果発表
            } else if (this.timeLeft == 0) {
                GameManager.swapSkin();
            }
        }
    }
}
