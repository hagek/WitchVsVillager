package com.github.hage.witchvsvillager;

import com.github.hage.witchvsvillager.lobby.Lobby;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class WitchVsVillager extends JavaPlugin {
    private static WitchVsVillager inst;
    private static Lobby lobby = new Lobby();

    public static JavaPlugin inst() {
        return inst;
    }

    public static Lobby lobby() {
        return lobby;
    }

    @Override
    public void onEnable() {
        inst = this;
    }

    @Override
    public void onDisable() {

    }
}
