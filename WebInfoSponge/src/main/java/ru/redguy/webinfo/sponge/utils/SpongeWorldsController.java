package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import ru.redguy.webinfo.common.utils.AbstractWorldsController;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.sponge.WebInfoSponge;

import java.io.IOException;

public class SpongeWorldsController extends AbstractWorldsController {
    @Override
    public boolean isWorldExist(String name) {
        return Sponge.getServer().getWorld(name).isPresent();
    }

    @Override
    public ActionResult unloadWorld(String name, boolean save) {
        Sponge.getServer().getWorld(name).ifPresent(value -> {
            Sponge.getScheduler()
                    .createTaskBuilder()
                    .execute(() -> {
                        try {
                            value.save();
                        } catch (IOException e) {
                            Logger.warn(LoggerType.Client,"Something went wrong: "+e.getMessage());
                        }
                        Sponge.getServer().unloadWorld(value);
                    })
                    .submit(WebInfoSponge.instance);
        });
        return new ActionResult(true);
    }
}
