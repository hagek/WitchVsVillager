package com.github.hage.witchvsvillager.stage;

import com.github.hage.witchvsvillager.stage.trick.Chest;
import com.google.common.collect.Lists;
import lombok.Getter;

import javax.xml.stream.Location;
import java.util.List;

@Getter
public class Stage {
    private String id;
    private String displayedName;
    private String builder;
    private final List<Chest> chests = Lists.newArrayList();
    private final List<Chest> cans = Lists.newArrayList();
    private final List<Chest> doors = Lists.newArrayList();
    private final List<Location> startPoints = Lists.newArrayList();
}
