package ru.redguy.webinfo.common.controllers;

import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractBasicController {
    public abstract String getMCVersion();

    public abstract String getPlatform();

    public abstract List<Mod> getModsList();

    public abstract CompletableFuture<List<World>> getWorldsList();

    public abstract boolean isClient();

    public abstract double getTPS();

    public abstract void shutdown();
}
