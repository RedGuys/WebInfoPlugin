package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;

@WebPage(url = "/endpoints/")
public class Endpoints implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception {
        return Response.OK(WebServer.getInstance().getPages().keySet().stream().map(pair -> pair.getKey().name()+" "+pair.getValue()).toArray());
    }
}
