package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.WebServer;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url = "/endpoints/")
public class endpoints implements IWebPage {
    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        return Response.OK(WebServer.getInstance().getPages().keySet());
    }
}
