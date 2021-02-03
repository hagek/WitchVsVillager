package com.github.hage.witchvsvillager.util;

import com.github.hage.witchvsvillager.game.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PerformanceUtil {

    public static void sendMessage(Predicate<Player> filter, String... messages) {
        Bukkit.getOnlinePlayers().stream().filter(filter).collect(Collectors.toList()).forEach(player -> player.sendMessage(messages));
    }

    public static void sendMessage(GameListener.Filters filter, String... messages) {
        sendMessage(filter.getFilter(), messages);
    }

    public static void sendMessage(Player player, String... messages) {
        player.sendMessage(messages);
    }

    public static void sendTitle(Predicate<Player> filter, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        Bukkit.getOnlinePlayers().stream().filter(filter).collect(Collectors.toList()).forEach(player -> player.sendTitle(title, subTitle, fadeIn, stay, fadeOut));
    }

    public static void sendTitle(GameListener.Filters filter, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(filter.getFilter(), title, subTitle, fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
    }

    public static String colored(String text) {
        return text == null ? null : ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String uncolored(String text) {
        return text == null ? null : ChatColor.stripColor(colored(text));
    }

    public static String format(String format, Object... args) {
        String text = colored(format);
        Matcher matcher = Pattern.compile("\\{\\d+}").matcher(format);
        while (matcher.find()) {
            String group = matcher.group();
            int index = Integer.parseInt(group.substring(1, group.length() - 1));
            if (args.length > index) {
                text = text.replaceFirst(Pattern.quote(group), colored(String.valueOf(args[index]).replace("$", "\\$")));
            } else {
                text = text.replaceFirst(Pattern.quote(group), "null");
            }
        }
        return text;
    }

    public static void playSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static void playSound(Predicate<Player> filter, Sound sound, float volume, float pitch) {
        Bukkit.getServer().getOnlinePlayers().stream().filter(filter).forEach(player -> playSound(player, sound, volume, pitch));
    }

    public static void playSound(GameListener.Filters filter, Sound sound, float volume, float pitch) {
        playSound(filter.getFilter(), sound, volume, pitch);
    }
}
