package ru.redguy.webinfocommon.utils;

import ru.redguy.webinfocommon.structures.Plugin;
import ru.redguy.webinfocommon.structures.World;

import java.util.List;

public interface IInfoUtils {

    String getMCVersion();
    List<String> getPlayersList();
    List<Plugin> getPluginsList();
    List<World> getWorldsList();
}
