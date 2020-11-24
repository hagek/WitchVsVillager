package com.github.hage.witchvsvillager.game;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public enum Role {
    VILLAGER(Team.VILLAGER, true, VILLAGER),
    NECROMANCER(Team.VILLAGER, true),
    SELF_KILLER(Team.ELSE, false),
    WITCH(Team.WITCH, false),
    CAT(Team.WITCH, true);

    private final Team team;
    private final boolean dependable;
    private final List<Role> badAffinity;

    Role(Team team, boolean dependable, Role... badAffinity) {
        this.team = team;
        this.dependable = dependable;
        this.badAffinity = Arrays.asList(badAffinity);
    }
}
