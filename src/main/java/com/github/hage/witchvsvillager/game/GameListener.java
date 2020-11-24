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

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameListener implements Listener {

    private Collection<? extends Player> onlinePlayers;

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            WVVPlayer wvvPlayer = WVVPlayer.getBukkitPlayer(victim);
            wvvPlayer.onDeath();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        WVVPlayer wvvPlayer = WVVPlayer.getBukkitPlayer(player);
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
        ALIVE(player -> GAME.test(player) && WVVPlayer.getBukkitPlayer(player).isAlive()),
        SPECTATOR(player -> GAME.test(player) && !WVVPlayer.getBukkitPlayer(player).isAlive()),
        SERVER(null);

        private final Predicate<Player> filter;

        public List<Player> getFiltered() {
            return Bukkit.getOnlinePlayers().stream().filter(this.filter).collect(Collectors.toList());
        }

        public boolean test(Player player) {
            return this.filter.test(player);
        }
    }
}
