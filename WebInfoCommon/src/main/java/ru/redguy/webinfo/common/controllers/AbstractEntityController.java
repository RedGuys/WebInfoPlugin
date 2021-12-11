package ru.redguy.webinfo.common.controllers;

import ru.redguy.webinfo.common.structures.Location;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractEntityController {
    public abstract CompletableFuture<UUID> spawnEntity(String type, Location location);
}
