package com.github.hage.witchvsvillager.util.skin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.github.hage.witchvsvillager.WitchVsVillager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class SkinListener extends PacketAdapter {

	public SkinListener() {
		super(WitchVsVillager.inst(), PacketType.Play.Server.PLAYER_INFO);
	}

	@Override
	public void onPacketSending(PacketEvent e) {
		List<PlayerInfoData> list = e.getPacket().getPlayerInfoDataLists().read(0);
		List<PlayerInfoData> newPlayerInfo = list.stream().map(playerInfoData -> {
			WrappedGameProfile profile = playerInfoData.getProfile();
			Player p = Bukkit.getPlayer(profile.getUUID());
			if (p != null) {
				return new PlayerInfoData(SkinManager.getDisplayedSkin(p).toGameProfile(profile.getUUID(), profile.getName()), playerInfoData.getLatency(), playerInfoData.getGameMode(), playerInfoData.getDisplayName());
			}
			return playerInfoData;
		}).collect(Collectors.toList());
		e.getPacket().getPlayerInfoDataLists().write(0, newPlayerInfo);
	}

}
