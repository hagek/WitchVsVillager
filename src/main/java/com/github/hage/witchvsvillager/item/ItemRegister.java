package com.github.hage.witchvsvillager.item;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemRegister {
    private static final HashMap<String, CustomItem> REGISTERED_ITEMS = Maps.newHashMap();

    public static void register(CustomItem item) {
        REGISTERED_ITEMS.put(item.getId(), item);
    }

    public static Map<String, CustomItem> getRegisteredItems() {
        return Collections.unmodifiableMap(REGISTERED_ITEMS);
    }

    public static CustomItem getById(String id) {
        return REGISTERED_ITEMS.get(id);
    }
}
