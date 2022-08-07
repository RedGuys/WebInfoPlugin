package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.WebServer;
import ru.redguy.miniwebserver.WebServerBuilder;
import ru.redguy.miniwebserver.utils.ArgumentIncorrect;
import ru.redguy.miniwebserver.utils.ArgumentNotPassed;
import ru.redguy.webinfo.common.pages.*;
import ru.redguy.webinfo.common.utils.ILogger;
import ru.redguy.webinfo.common.utils.LocationArgument;
import ru.redguy.webinfo.common.utils.Response;
import ru.redguy.webinfo.common.utils.WorldArgument;

public class WebInfoCommon {
    public static ILogger logger;
    public static WebServer server;
    public static WebServer buildWebServer(@NotNull WebServerBuilder builder) {
        return builder
                .addRouter(new Index())
                .addRouter(new Chat())
                .addRouter(new Entity())
                .addRouter(new Mods())
                .addRouter(new Player())
                .addRouter(new World())
                .addArgumentParser(new LocationArgument())
                .addArgumentParser(new WorldArgument())
                .addResponseMiddleware((req,res) -> {
                    if(res.getStatus().equals(NanoHTTPD.Response.Status.NOT_FOUND)) {
                        res.setResponse(Response.MethodNotFound());
                        return;
                    }
                    if(res.getStatus().equals(NanoHTTPD.Response.Status.BAD_REQUEST)) {
                        if(res.getResponse() instanceof ArgumentNotPassed) {
                            res.setResponse(Response.TheVariableIsNotPassed(((ArgumentNotPassed) res.getResponse()).getName()));
                        } else if (res.getResponse() instanceof ArgumentIncorrect) {
                            res.setResponse(Response.VariableIncorrect(((ArgumentIncorrect) res.getResponse()).getName()));
                        }
                        return;
                    }
                    res.setResponse(Response.OK(res.getResponse()));
                })
                .build();
    }
}
