package ru.redguy.webinfo.common;

import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.WebServer;
import ru.redguy.jrweb.utils.Middleware;
import ru.redguy.jrweb.utils.MiddlewarePosition;
import ru.redguy.jrweb.utils.StatusCodes;
import ru.redguy.jrweb.utils.optional.GsonUtil;
import ru.redguy.webinfo.common.pages.*;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.*;

import java.io.IOException;
import java.util.UUID;

public class WebInfoCommon {
    public static ILogger logger;
    public static WebServer server;

    public static WebServer buildWebServer() {
        GsonUtil.registerTypeAdapter(Location.class, new LocationTypeAdaptor());
        GsonUtil.registerTypeAdapter(World.class, new WorldTypeAdaptor());
        GsonUtil.registerTypeAdapter(UUID.class, new UUIDTypeAdaptor());
        WebServer builder = new WebServer();
        builder.addRouter(new Index());
        builder.addRouter(new Chat());
        builder.addRouter(new Entity());
        builder.addRouter(new Mods());
        builder.addRouter(new Player());
        builder.addRouter(new World());
        builder.addMiddleware(new Middleware() {
            @Override
            public void run(Context context) throws IOException {
                if (context.response.statusCode == StatusCodes.NOT_FOUND) {
                    context.response.send(Response.NotFound());
                }
            }

            @Override
            public MiddlewarePosition getPosition() {
                return MiddlewarePosition.AFTER;
            }
        });
        return builder;
    }
}
