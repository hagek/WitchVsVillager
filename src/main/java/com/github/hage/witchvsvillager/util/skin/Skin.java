package com.github.hage.witchvsvillager.util.skin;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
public class Skin {

	public static final Skin EMPTY = new Skin(null, null);

	private final String value;
	private final String signature;

	public WrappedGameProfile toGameProfile(UUID uuid, String name) {
		WrappedGameProfile profile = new WrappedGameProfile(uuid, name);
		if (this.value != null && this.signature != null) {
			profile.getProperties().put("textures", new WrappedSignedProperty("textures", this.value, this.signature));
		}
		return profile;
	}

	@Nullable
	public static Skin fromGameProfile(WrappedGameProfile profile) {
		for (WrappedSignedProperty property: profile.getProperties().get("textures")) {
			return new Skin(property.getValue(), property.getSignature());
		}
		return null;
	}

}
