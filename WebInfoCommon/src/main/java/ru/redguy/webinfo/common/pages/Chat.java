package ru.redguy.webinfo.common.pages;

import ru.redguy.jrweb.Context;
import ru.redguy.jrweb.annotations.Page;
import ru.redguy.jrweb.annotations.Router;
import ru.redguy.jrweb.utils.StatusCodes;
import ru.redguy.jrweb.utils.bodyparsers.JsonBody;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;
import ru.redguy.webinfo.common.utils.APIResponse;

import java.util.UUID;

@Router("/chat")
public class Chat {
    @Page(value = "/send", method = "POST")
    public void send(Context ctx) {
        if(!(ctx.request.body instanceof JsonBody)) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send("Body must be json");
            return;
        }
        JsonBody req = (JsonBody) ctx.request.body;
        if(!req.has("to")) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send(APIResponse.TheVariableIsNotPassed("to"));
            return;
        }
        if(!req.has("message")) {
            ctx.response.setStatusCode(StatusCodes.BAD_REQUEST).send(APIResponse.TheVariableIsNotPassed("message"));
            return;
        }
        UUID to = UUID.fromString(req.get("to").getAsString());
        String message = req.get("message").getAsString();
        Controllers.getChatController().sendMessage(message,to);
        ctx.response.send(new ActionResult(true));
    }
}
