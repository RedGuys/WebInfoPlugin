package ru.redguy.webinfo.common.pages.chat;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.QueryArgument;
import ru.redguy.webinfo.common.QueryArgumentType;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebPage(url = "/chat/send/", method = NanoHTTPD.Method.POST, args = {
        @QueryArgument(name = "message", type = QueryArgumentType.STRING, required = true),
        @QueryArgument(name = "to", type = QueryArgumentType.UUID, required = false)
})
public class Send implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception {
        if(args.get("to").size() > 0) {
            for (Object to : args.get("to")) {
                Controllers.getChatController().sendMessage((String) args.get("message").get(0), (UUID) to);
            }
        } else {
            Controllers.getChatController().sendMessage((String) args.get("message").get(0));
        }
        return Response.OK(new ActionResult(true));
    }
}
