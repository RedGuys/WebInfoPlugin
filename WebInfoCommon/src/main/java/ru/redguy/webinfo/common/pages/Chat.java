package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.miniwebserver.utils.arguments.UUIDArgument;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.ActionResult;

import java.util.UUID;

@Router("/chat")
public class Chat {
    @WebPage(value = "/send", method = NanoHTTPD.Method.POST, args = {
            @QueryArgument(name = "message", type = StringArgument.class),
            @QueryArgument(name = "to", type = UUIDArgument.class, required = false)
    })
    public void send(@NotNull WebRequest req, WebResponse res) {
        if (req.getArguments().get("to").size() > 0) {
            for (Object to : req.getArguments().get("to")) {
                for (Object message : req.getArguments().get("message")) {
                    Controllers.getChatController().sendMessage((String) message, (UUID) to);
                }
            }
        } else {
            for (Object message : req.getArguments().get("message")) {
                Controllers.getChatController().sendMessage((String) message);
            }
        }
        res.setResponse(new ActionResult(true));
    }
}
