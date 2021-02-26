package ru.redguy.webinfocommon.pages.logout;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.WebStatic;
import ru.redguy.webinfocommon.utils.Logger;
import ru.redguy.webinfocommon.utils.LoggerType;
import ru.redguy.webinfocommon.utils.WebUtils;

import java.io.IOException;

@WebPage(url = "/logout")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        WebUtils.CookieHandler ch = new WebUtils.CookieHandler(session.getHeaders());
        Logger.info(LoggerType.Web, WebStatic.sessions.get(ch.read("session"))+ " has been logout!");
        WebStatic.sessions.remove(ch.read("session"));
        ch.delete("session");
        NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
        response.addHeader("Location", "/");
        ch.unloadQueue(response);
        return response;
    }
}
