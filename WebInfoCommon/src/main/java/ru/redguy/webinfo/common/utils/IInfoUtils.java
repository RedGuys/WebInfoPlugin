package ru.redguy.webinfo.common.utils;

import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;

import java.util.List;

public interface IInfoUtils {

    String getMCVersion();
    String getPlatform();
    List<String> getPlayersList();
    List<Mod> getModsList();
    List<World> getWorldsList();
    boolean isClient();
}
