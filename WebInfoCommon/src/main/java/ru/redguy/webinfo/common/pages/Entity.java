package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.LocationArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Router
public class Entity{
    @WebPage(value = "/entity/spawn/", method = NanoHTTPD.Method.POST, args = {
            @QueryArgument(name = "type", type = StringArgument.class),
            @QueryArgument(name = "location", type = LocationArgument.class)
    })
    public void spawn(@NotNull WebRequest req, WebResponse res) {
        List<CompletableFuture<UUID>> futures = new ArrayList<>();
        for (Object type : req.getArguments().get("type")) {
            for (Object location : req.getArguments().get("location")) {
                futures.add(Controllers.getEntityController().spawnEntity((String) type, (Location) location));
            }
        }
        res.setResponse(futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
