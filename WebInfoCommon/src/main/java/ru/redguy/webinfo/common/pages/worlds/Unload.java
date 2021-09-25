package ru.redguy.webinfo.common.pages.worlds;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.ParamsUtils;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url="/worlds/unload/")
public class Unload implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        if(!session.getParameters().containsKey("world"))
            return Response.TheVariableIsNotPassed("world");
        String worldName = session.getParameters().get("world").get(0);
        if(!Controllers.getWorldsController().isWorldExist(worldName))
            return Response.NotFound();

        boolean save = true;
        if(session.getParameters().containsKey("save"))
            save = ParamsUtils.parseBoolean(session.getParameters().get("save"),true);

        ActionResult result = Controllers.getWorldsController().unloadWorld(worldName,save).get();

        return Response.OK(result);
    }
}
