package com.github.hage.witchvsvillager.util;

import com.github.hage.witchvsvillager.game.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MessageUtil {

    public static void sendMessage(Predicate<Player> filter, String... messages) {
        Bukkit.getOnlinePlayers().stream().filter(filter).collect(Collectors.toList()).forEach(player -> player.sendMessage(messages));
    }

    public static void sendMessage(GameListener.Filters filter, String... messages) {
        sendMessage(filter.getFilter(), messages);
    }

    public static String colored(String text) {
        return text == null ? null : ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String uncolored(String text) {
        return text == null ? null : ChatColor.stripColor(colored(text));
    }

    public static String format(String format, Object... args) {
        return MessageFormat.format(format, args);
    }
}
