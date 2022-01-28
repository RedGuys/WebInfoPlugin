package ru.redguy.webinfo.spigot.utils;

import me.lucko.spark.api.Spark;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.types.DoubleStatistic;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.controllers.AbstractBasicController;
import ru.redguy.webinfo.spigot.WebInfoSpigot;

import java.util.ArrayList;
import java.util.List;

public class SpigotInfoUtils extends AbstractBasicController {

    @Override
    public String getMCVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public String getPlatform() {
        return "spigot";
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

    @Override
    public double getTPS() {
        if(WebInfoSpigot.getInstance().isSparkAvailable()) {
            return SparkUtil.getTPS();
        } else {
            return Utils.getTPS();
        }
    }

    @Override
    public void shutdown() {
        Bukkit.shutdown();
    }
}
