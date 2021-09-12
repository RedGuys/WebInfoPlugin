package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;
import ru.redguy.webinfo.common.utils.IInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class SpongeInfoUtils implements IInfoUtils {

    @Override
    public String getMCVersion() {
        return Sponge.getPlatform().getMinecraftVersion().getName();
    }

    @Override
    public List<String> getPlayersList() {
        List<String> result = new ArrayList<>();
        for (Player onlinePlayer : Sponge.getServer().getOnlinePlayers()) {
            result.add(onlinePlayer.getName());
        }
        return result;
    }

    @Override
    public List<Mod> getModsList() {
        List<Mod> mods = new ArrayList<>();
        for (PluginContainer plugin : Sponge.getPluginManager().getPlugins()) {
            mods.add(new Mod(plugin.getName(),plugin.getVersion().orElse("")));
        }
        return mods;
    }

    @Override
    public List<World> getWorldsList() {
        List<World> worlds = new ArrayList<>();
        for (org.spongepowered.api.world.World world : Sponge.getServer().getWorlds()) {
            worlds.add(TransformUtils.transform(world));
        }
        return worlds;
    }

    @Override
    public boolean isClient() {
        return false;
    }
}