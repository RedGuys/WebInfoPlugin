package ru.redguy.webinfocommon.utils;

import ru.redguy.webinfocommon.structures.Mod;
import ru.redguy.webinfocommon.structures.World;

import java.util.List;

public interface IInfoUtils {

    String getMCVersion();
    List<String> getPlayersList();
    List<Mod> getModsList();
    List<World> getWorldsList();
    boolean isClient();
}
