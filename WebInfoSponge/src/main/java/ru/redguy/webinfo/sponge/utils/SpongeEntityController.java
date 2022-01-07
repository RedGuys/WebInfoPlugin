package ru.redguy.webinfo.sponge.utils;

import ru.redguy.webinfo.common.controllers.AbstractEntityController;
import ru.redguy.webinfo.common.structures.Location;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SpongeEntityController extends AbstractEntityController {
    @Override
    public CompletableFuture<UUID> spawnEntity(String type, Location location) {
        throw new UnsupportedOperationException();
    }
}
