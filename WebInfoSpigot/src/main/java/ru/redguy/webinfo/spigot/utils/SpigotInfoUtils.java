package ru.redguy.webinfo.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.utils.IInfoUtils;

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
    public List<Mod> getModsList() {
        List<Mod> mods = new ArrayList<>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            mods.add(new Mod(plugin.getName(), plugin.getDescription().getVersion()));
        }
        return mods;
    }

    @Override
    public List<World> getWorldsList() {
        List<World> worlds = new ArrayList<>();
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            worlds.add(TransformUtils.transform(world));
        }
        return worlds;
    }

    @Override
    public boolean isClient() {
        return false;
    }
}
