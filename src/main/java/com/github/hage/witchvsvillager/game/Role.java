package com.github.hage.witchvsvillager.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public enum Role {
    VILLAGER(Team.VILLAGER, true, role -> role.getTeam() == Team.VILLAGER),
    NECROMANCER(Team.VILLAGER, true, role -> role.getTeam() == Team.VILLAGER),
    SELF_KILLER(Team.ELSE, false, role -> false),
    WITCH(Team.WITCH, false, role -> false),
    CAT(Team.WITCH, true, role -> role == Role.WITCH);

    private final Team team;
    private final boolean dependable;
    private final Predicate<Role> wrongKill;
}
