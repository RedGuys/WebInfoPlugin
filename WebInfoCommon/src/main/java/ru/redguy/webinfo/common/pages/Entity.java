package ru.redguy.webinfo.common.pages;

import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.jrweb.utils.StatusCodes;
import ru.redguy.jrweb.utils.bodyparsers.JsonBody;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.APIResponse;

import java.util.concurrent.ExecutionException;

@Router("/entity")
public class Entity {
    @Page(value = "/spawn", method = "POST")
    public void spawn(Context ctx) throws ExecutionException, InterruptedException {
        if (!(ctx.request.body instanceof JsonBody)) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send("Body must be json");
            return;
        }
        JsonBody req = (JsonBody) ctx.request.body;
        if (!req.has("type")) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send(APIResponse.TheVariableIsNotPassed("type"));
            return;
        }
        if (!req.has("location")) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send(APIResponse.TheVariableIsNotPassed("location"));
            return;
        }
        ctx.response.send(Controllers.getEntityController().spawnEntity(req.get("type").getAsString(), req.get("location", Location.class)));
    }
}
