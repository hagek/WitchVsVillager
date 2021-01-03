package com.github.hage.witchvsvillager.game;

import com.github.hage.witchvsvillager.util.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameListener implements Listener {

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            WVVPlayer wvvPlayer = GameManager.fromBukkitPlayer(victim);
            switch (victim.getLastDamageCause().getCause()) {
                case ENTITY_ATTACK:
                case ENTITY_SWEEP_ATTACK:
                case PROJECTILE:
                    WVVPlayer opponent = GameManager.fromBukkitPlayer(victim.getKiller());
                    if (wvvPlayer.isWrongKill(opponent)) {
                        wvvPlayer.onDeath(WVVPlayer.DeathReason.WRONG_KILL);
                    } else {
                        wvvPlayer.onDeath(WVVPlayer.DeathReason.NORMAL);
                    }
                    break;
                default:
                    wvvPlayer.onDeath(WVVPlayer.DeathReason.OTHER);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        WVVPlayer wvvPlayer = GameManager.fromBukkitPlayer(player);
        if (wvvPlayer != null) {
            if (wvvPlayer.isAlive()) {
                MessageUtil.sendMessage(Filters.ALIVE, MessageUtil.format("&e{0}&7: &f{1}", wvvPlayer.getDisguisedAs().getPlayer().getName(), event.getMessage()));
            } else {
                MessageUtil.sendMessage(Filters.SPECTATOR, MessageUtil.format("&7[SPECTATOR] {0}: {1}", player.getName(), event.getMessage()));
            }
        } else {
            MessageUtil.sendMessage(Filters.GAME, MessageUtil.format("&a{0}&7: &f{1}", player.getName(), event.getMessage()));
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Filters {
        GAME(Objects::nonNull),
        ALIVE(player -> GAME.test(player) && GameManager.fromBukkitPlayer(player).isAlive()),
        SPECTATOR(player -> GAME.test(player) && !GameManager.fromBukkitPlayer(player).isAlive()),
        SERVER(player -> true);

        private final Predicate<Player> filter;

        public List<Player> getFiltered() {
            return Bukkit.getOnlinePlayers().stream().filter(this.filter).collect(Collectors.toList());
        }

        public boolean test(Player player) {
            return this.filter.test(player);
        }
    }
}
