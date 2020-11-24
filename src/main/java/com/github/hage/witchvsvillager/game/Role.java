package com.github.hage.witchvsvillager.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public enum Role {
    VILLAGER(Team.VILLAGER, true, victim -> victim.getRole().getTeam() == Team.VILLAGER),
    NECROMANCER(Team.VILLAGER, true, victim -> victim.getRole().getTeam() == Team.VILLAGER),
    SELF_KILLER(Team.ELSE, false, null),
    WITCH(Team.WITCH, false, null),
    CAT(Team.WITCH, true, victim -> victim.getRole() == Role.WITCH);

    private final Team team;
    private final boolean dependable;
    private final Predicate<WVVPlayer> wrongKill;
}
