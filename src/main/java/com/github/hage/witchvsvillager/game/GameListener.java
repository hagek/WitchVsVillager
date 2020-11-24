package com.github.hage.witchvsvillager.game;

import com.github.hage.witchvsvillager.util.MessageUtil;
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
                MessageUtil.sendMessage(ChatFilters.ALIVE_PLAYER, MessageUtil.format("&e{0}&7: &f{1}", wvvPlayer.getDisguisedAs().getPlayer().getName(), event.getMessage()));
            } else {
                MessageUtil.sendMessage(ChatFilters.SPECTATOR, MessageUtil.format("&7[SPECTATOR] {0}: {1}", player.getName(), event.getMessage()));
            }
        } else {
            MessageUtil.sendMessage(ChatFilters.NOT_JOINED_PLAYER, MessageUtil.format("&a{0}&7: &f{1}", player.getName(), event.getMessage()));
        }
    }

    @Getter
    public enum ChatFilters {
        ALIVE_PLAYER(null),
        SPECTATOR(player -> !WVVPlayer.getBukkitPlayer(player).isAlive()),
        NOT_JOINED_PLAYER(Objects::isNull);

        private final Predicate<Player> filter;

        ChatFilters(Predicate<Player> filter) {
            this.filter = filter;
        }

        public List<Player> getFiltered() {
            return Bukkit.getOnlinePlayers().stream().filter(this.filter).collect(Collectors.toList());
        }
    }
}
