package ru.redguy.webinfo.common.pages.worlds;

import com.google.gson.JsonObject;
import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ParamsUtils;
import ru.redguy.webinfo.common.utils.Response;
import ru.redguy.webinfo.common.utils.WorldsController;

import java.io.IOException;

@WebPage(url="/worlds/unload/")
public class Unload implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JsonObject object = new JsonObject();

        if(!session.getParameters().containsKey("world"))
            return Response.TheVariableIsNotPassed("world");
        String worldName = session.getParameters().get("world").get(0);
        if(!WorldsController.getInstance().isWorldExist(worldName))
            return Response.NotFound();

        boolean save = true;
        if(session.getParameters().containsKey("save"))
            save = ParamsUtils.parseBoolean(session.getParameters().get("save"),true);

        WorldsController.getInstance().unloadWorld(worldName,save);

        return Response.OK(object);
    }
}
