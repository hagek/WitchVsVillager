package com.github.hage.witchvsvillager.util.skin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.github.hage.witchvsvillager.WitchVsVillager;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import net.minecraft.server.v1_12_R1.DedicatedPlayerList;
import net.minecraft.server.v1_12_R1.OpList;
import net.minecraft.server.v1_12_R1.OpListEntry;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SkinManager {

	private static final HashMap<UUID, Skin> PLAYER_SKIN_MAP = Maps.newHashMap();
	private static final Gson GSON = new Gson();
	private static final String SKIN_URL = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";

	@SuppressWarnings("unchecked")
	public static Skin getSkin(OfflinePlayer player) {
		if (player != null) {
			if (player instanceof Player || player.isOnline()) {
				return Skin.fromGameProfile(WrappedGameProfile.fromPlayer(player.getPlayer()));
			} else {
				try (JsonReader reader = new JsonReader(new InputStreamReader(new URL(String.format(SKIN_URL, player.getUniqueId().toString().replace("-", StringUtils.EMPTY))).openStream()))) {
					LinkedTreeMap<String, ?> json = GSON.fromJson(reader, new TypeToken<LinkedTreeMap<String, ?>>() {}.getType());
					if (json != null) {
						ArrayList<?> properties = (ArrayList<?>)json.get("properties");
						LinkedTreeMap<String, String> property = (LinkedTreeMap<String, String>)properties.get(0);
						return new Skin(property.get("value"), property.get("signature"));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return Skin.EMPTY;
	}

	public static Skin getDisplayedSkin(Player p) {
		return Optional.ofNullable(PLAYER_SKIN_MAP.get(p.getUniqueId())).orElse(getSkin(p));
	}

	public static void changeSkin(Player p, @Nullable Skin skin) {
		PLAYER_SKIN_MAP.put(p.getUniqueId(), skin == null ? Skin.EMPTY : skin);
		updateGameProfile(p);
	}

	public static void resetSkin(Player p) {
		PLAYER_SKIN_MAP.remove(p.getUniqueId());
		updateGameProfile(p);
	}

	public static void updateGameProfile(Player p) {
		//hideとshowを行うだけでほかのプレイヤーからはスキンが更新されたように見える
		for (Player player: Bukkit.getOnlinePlayers()) {
			if (player != p && player.canSee(p)) {
				player.hidePlayer(WitchVsVillager.inst(), p);
				player.showPlayer(WitchVsVillager.inst(), p);
			}
		}

		//スキンの更新時に乗っていたエンティティから降りてしまう現象(クライアントサイド)の対策
		Optional.ofNullable(p.getVehicle()).ifPresent(vehicle -> {
			vehicle.removePassenger(p);
			Bukkit.getScheduler().runTask(WitchVsVillager.inst(), () -> vehicle.addPassenger(p));
		});

		PlayerInfoData playerInfoData = new PlayerInfoData(getDisplayedSkin(p).toGameProfile(p.getUniqueId(), p.getName()), 0, EnumWrappers.NativeGameMode.fromBukkit(p.getGameMode()), WrappedChatComponent.fromText(p.getPlayerListName()));

		//TabListの更新
		PacketContainer removeInfo = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
		removeInfo.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
		removeInfo.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
		PacketContainer addInfo = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
		addInfo.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
		addInfo.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));

		//クライアント側でスキンの更新
		//このパケットを送信するといろいろな情報がリセット(?)されてしまう
		PacketContainer respawn = new PacketContainer(PacketType.Play.Server.RESPAWN);
		respawn.getIntegers().write(0, p.getWorld().getEnvironment().getId());
		respawn.getDifficulties().write(0, EnumWrappers.getDifficultyConverter().getSpecific(p.getWorld().getDifficulty()));
		respawn.getGameModes().write(0, EnumWrappers.NativeGameMode.fromBukkit(p.getGameMode()));
		respawn.getWorldTypeModifier().write(0, p.getWorld().getWorldType());

		//座標情報をクライアントに認識させる
		PacketContainer position = new PacketContainer(PacketType.Play.Server.POSITION);
		position.getModifier().writeDefaults();
		position.getDoubles().write(0, p.getLocation().getX());
		position.getDoubles().write(1, p.getLocation().getY());
		position.getDoubles().write(2, p.getLocation().getZ());
		position.getFloat().write(0, p.getLocation().getYaw());
		position.getFloat().write(1, p.getLocation().getPitch());

		//OP権限のレベルをクライアントに認識させる
		PacketContainer op = new PacketContainer(PacketType.Play.Server.ENTITY_STATUS);
		op.getIntegers().write(0, p.getEntityId());
		op.getBytes().write(0, (byte)(24 + getOperatorLevel(p)));

		//付与されているエフェクト効果をクライアント認識させる
		List<PacketContainer> effects = p.getActivePotionEffects().stream().map(effect -> {
			PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EFFECT);
			packet.getIntegers().write(0, p.getEntityId());
			packet.getBytes().write(0, (byte)(effect.getType().getId() & 255));
			packet.getBytes().write(1, (byte)(effect.getAmplifier() & 255));
			packet.getIntegers().write(1, effect.getDuration());
			byte particle = 0;
			if (effect.isAmbient()) {
				particle = (byte)(particle | 1);
			}
			if (effect.hasParticles()) {
				particle = (byte)(particle | 2);
			}
			packet.getBytes().write(2, particle);
			return packet;
		}).collect(Collectors.toList());

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, removeInfo);
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, addInfo);
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, respawn);
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, position);
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, op);
			for (PacketContainer packet: effects) {
				ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
			}
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}

		//体力やインベントリ、レベルなどを認識させる
		((CraftPlayer)p).updateScaledHealth();
		p.updateInventory();
		p.getInventory().setHeldItemSlot(p.getInventory().getHeldItemSlot());
		p.setExp(p.getExp());
		p.setTotalExperience(p.getTotalExperience());
		p.setFlySpeed(p.getFlySpeed());
		p.setWalkSpeed(p.getWalkSpeed());
		p.setScoreboard(p.getScoreboard());
	}

	private static int getOperatorLevel(Player p) {
		DedicatedPlayerList playerList = ((CraftServer)Bukkit.getServer()).getHandle();
		OpList opList = playerList.getOPs();
		OpListEntry opListEntry = opList.get(((CraftPlayer)p).getProfile());
		return opListEntry == null ? 0 : opListEntry.a();
	}

}
