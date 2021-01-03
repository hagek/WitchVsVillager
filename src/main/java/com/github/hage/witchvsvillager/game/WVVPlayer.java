package com.github.hage.witchvsvillager.game;

import com.github.hage.witchvsvillager.util.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
public class WVVPlayer {

    private Player player;
    private WVVPlayer disguisedAs;
    private Role role;
    private int number;
    private AngelType angel;
    private int kills;
    private boolean alive;

    public void onDeath(DeathReason reason) {
        MessageUtil.sendTitle(this.player, ChatColor.RED + reason.getDisplayedText(), ChatColor.WHITE + "", 0, 100, 0);
    }

    public void changeSkin() {

    }

    public boolean isWrongKill(WVVPlayer opponent) {
        return this.role.getWrongKill().test(opponent.getRole());
    }

    enum AngelType {
        NORMAL,
        FALLEN,
        NONE
    }

    @AllArgsConstructor
    @Getter
    enum DeathReason {
        NORMAL("誰かに殺された"),
        WRONG_KILL("誤殺してしまった"),
        OTHER("死んでしまった");

        String displayedText;
    }
}
