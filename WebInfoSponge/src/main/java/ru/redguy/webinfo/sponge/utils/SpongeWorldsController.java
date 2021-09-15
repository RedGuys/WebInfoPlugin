package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import ru.redguy.webinfo.common.utils.AbstractWorldsController;
import ru.redguy.webinfo.sponge.WebInfoSponge;

public class SpongeWorldsController extends AbstractWorldsController {
    @Override
    public boolean isWorldExist(String name) {
        return Sponge.getServer().getWorld(name).isPresent();
    }

    @Override
    public void unloadWorld(String name, boolean save) {
        Sponge.getServer().getWorld(name).ifPresent(value -> {
            Sponge.getScheduler()
                    .createTaskBuilder()
                    .execute(() -> Sponge.getServer().unloadWorld(value))
                    .submit(WebInfoSponge.instance);
        });
    }
}
