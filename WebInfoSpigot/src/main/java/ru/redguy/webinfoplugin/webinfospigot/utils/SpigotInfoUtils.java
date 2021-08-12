package ru.redguy.webinfoplugin.webinfospigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.redguy.webinfocommon.structures.World;
import ru.redguy.webinfocommon.utils.IInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class SpigotInfoUtils implements IInfoUtils {

    @Override
    public String getMCVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public List<String> getPlayersList() {
        List<String> result = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            result.add(onlinePlayer.getName());
        }
        return result;
    }

    @Override
    public List<ru.redguy.webinfocommon.structures.Plugin> getPluginsList() {
        List<ru.redguy.webinfocommon.structures.Plugin> plugins = new ArrayList<>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            plugins.add(new ru.redguy.webinfocommon.structures.Plugin(plugin.getName(), plugin.getDescription().getVersion()));
        }
        return plugins;
    }

    @Override
    public List<World> getWorldsList() {
        List<World> worlds = new ArrayList<>();
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            worlds.add(TransformUtils.transform(world));
        }
        return worlds;
    }
}
