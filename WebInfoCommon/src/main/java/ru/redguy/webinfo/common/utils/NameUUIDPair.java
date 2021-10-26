package ru.redguy.webinfo.common.utils;

import java.util.UUID;

public class NameUUIDPair {

    String name;
    UUID uuid;

    public NameUUIDPair(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }
}
