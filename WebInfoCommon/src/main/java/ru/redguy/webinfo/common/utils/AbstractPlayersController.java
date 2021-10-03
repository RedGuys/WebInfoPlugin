package ru.redguy.webinfo.common.utils;

import ru.redguy.webinfo.common.structures.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractPlayersController {
    public abstract CompletableFuture<ActionResult> ban(UUID uuid);
    public abstract CompletableFuture<ActionResult> ban(UUID uuid, String reason);

    public abstract CompletableFuture<ActionResult> banIp(String ip);
    public abstract CompletableFuture<ActionResult> banIp(String ip, String reason);

    public abstract Player getPlayerInfo(UUID uuid);
}