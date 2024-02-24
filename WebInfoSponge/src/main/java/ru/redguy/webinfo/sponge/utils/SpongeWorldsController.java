package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import ru.redguy.webinfo.common.controllers.AbstractWorldsController;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.sponge.WebInfoSponge;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class SpongeWorldsController extends AbstractWorldsController {
    @Override
    public boolean isWorldExist(String name) {
        return Sponge.server().worldManager().world(TransformUtils.transform(name)).isPresent();
    }

    @Override
    public CompletableFuture<ActionResult> unloadWorld(String name, boolean save) {
        CompletableFuture<ActionResult> res = new CompletableFuture<>();
        String[] parts = name.split(":");
        Sponge.server().worldManager().world(ResourceKey.of(parts[0], parts[1])).ifPresent(value -> Sponge.asyncScheduler()
                .submit(Task.builder()
                .execute(() -> {
                    try {
                        value.save();
                    } catch (IOException e) {
                        Logger.warn(LoggerType.Client, "Something went wrong: " + e.getMessage());
                    }
                    Sponge.server().worldManager().unloadWorld(value);
                    res.complete(new ActionResult(true));
                }).build()));
        return res;
    }
}
