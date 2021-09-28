package ru.redguy.webinfo.common.pages;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.IWebPage;
import ru.redguy.webinfo.common.WebPage;
import ru.redguy.webinfo.common.utils.ActionResult;
import ru.redguy.webinfo.common.utils.Controllers;
import ru.redguy.webinfo.common.utils.Response;

@WebPage(url = "/shutdown")
public class Shutdown implements IWebPage {

    @Override
    public Response getPage(NanoHTTPD.IHTTPSession session) throws Exception {
        Controllers.getBasicController().shutdown();
        return Response.OK(new ActionResult(true));
    }
}
