package ru.redguy.webinfo.common.controllers;

import ru.redguy.webinfo.common.structures.ActionResult;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractWorldsController {
    public abstract boolean isWorldExist(String name);
    public abstract CompletableFuture<ActionResult> unloadWorld(String name, boolean save);
}
