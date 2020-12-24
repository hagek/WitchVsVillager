package com.github.hage.witchvsvillager;

import com.comphenix.protocol.ProtocolLibrary;
import com.github.hage.witchvsvillager.util.skin.SkinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WitchVsVillager extends JavaPlugin {

    private static WitchVsVillager instance;

    @Override
    public void onEnable() {
        instance = this;
        ProtocolLibrary.getProtocolManager().addPacketListener(new SkinListener());
    }

    @Override
    public void onDisable() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(this);
    }

    public static WitchVsVillager inst() {
        return instance;
    }

}
