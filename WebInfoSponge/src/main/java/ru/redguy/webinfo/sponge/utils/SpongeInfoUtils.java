package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;
import ru.redguy.webinfo.common.controllers.AbstractBasicController;
import ru.redguy.webinfo.common.structures.Mod;
import ru.redguy.webinfo.common.structures.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SpongeInfoUtils extends AbstractBasicController {

    @Override
    public String getMCVersion() {
        return Sponge.platform().minecraftVersion().name();
    }

    @Override
    public String getPlatform() {
        return "sponge";
    }

    @Override
    public List<Mod> getModsList() {
        List<Mod> mods = new ArrayList<>();
        for (PluginContainer plugin : Sponge.pluginManager().plugins()) {
            mods.add(new Mod(plugin.metadata().name().get(), plugin.metadata().version().getQualifier()));
        }
        return mods;
    }

    @Override
    public CompletableFuture<List<World>> getWorldsList() {
        List<World> worlds = new ArrayList<>();
        for (org.spongepowered.api.world.World world : Sponge.server().worldManager().worlds()) {
            worlds.add(TransformUtils.transform(world));
        }
        return CompletableFuture.completedFuture(worlds);
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public double getTPS() {
        return Sponge.server().ticksPerSecond();
    }

    @Override
    public void shutdown() {
        Sponge.server().shutdown();
    }
}
