package com.github.hage.witchvsvillager.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Team {
    VILLAGER("&a&lVillager陣営"),
    WITCH("&5&lWitch陣営"),
    ELSE("&c&l自殺志願者");

    private final String perfix;
}
