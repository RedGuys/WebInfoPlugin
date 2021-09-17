package ru.redguy.webinfo.common.utils;

public abstract class AbstractWorldsController {
    public abstract boolean isWorldExist(String name);
    public abstract ActionResult unloadWorld(String name, boolean save);
}
