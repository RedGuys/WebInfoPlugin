package ru.redguy.webinfo.common.utils;

import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;

import java.util.List;

public abstract class AbstractBasicController {
    public abstract String getMCVersion();
    public abstract String getPlatform();
    public abstract List<Mod> getModsList();
    public abstract List<World> getWorldsList();
    public abstract boolean isClient();

    public abstract void shutdown();
}
