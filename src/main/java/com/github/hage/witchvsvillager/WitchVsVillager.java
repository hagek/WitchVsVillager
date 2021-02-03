package com.github.hage.witchvsvillager;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class WitchVsVillager extends JavaPlugin {
    @Getter
    private static WitchVsVillager inst;

    @Override
    public void onEnable() {
        inst = this;
    }

    @Override
    public void onDisable() {

    }
}
