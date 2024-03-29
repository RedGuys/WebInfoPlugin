package ru.redguy.webinfo.common.controllers;

import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.structures.Player;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractPlayersController {
    public abstract CompletableFuture<ActionResult> ban(UUID uuid);

    public abstract CompletableFuture<ActionResult> ban(UUID uuid, String reason);

    public abstract CompletableFuture<ActionResult> banIp(String ip);

    public abstract CompletableFuture<ActionResult> banIp(String ip, String reason);

    public abstract CompletableFuture<ActionResult> kick(UUID uuid);

    public abstract CompletableFuture<ActionResult> kick(UUID uuid, String reason);

    public abstract CompletableFuture<ActionResult> teleport(UUID uuid, Location location);

    public abstract Player getPlayerInfo(UUID uuid);

    public abstract List<Player> getPlayersList();
}
