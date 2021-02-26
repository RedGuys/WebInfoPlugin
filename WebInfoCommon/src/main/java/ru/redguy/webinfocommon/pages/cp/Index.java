package ru.redguy.webinfocommon.pages.cp;

import fi.iki.elonen.NanoHTTPD;
import org.apache.commons.io.IOUtils;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.WebServer;
import ru.redguy.webinfocommon.utils.PlaceholdersUtils;
import ru.redguy.webinfocommon.utils.SessionUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebPage(url = "/cp")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        if(!SessionUtils.checkAdmin(session)) {
            NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER, NanoHTTPD.MIME_PLAINTEXT, "");
            response.addHeader("Location", "/");
            return response;
        }
        String path = "/resources/web/cp/index.html";
        String page = IOUtils.toString(WebServer.class.getResourceAsStream(path), StandardCharsets.UTF_8);
        return NanoHTTPD.newFixedLengthResponse(new PlaceholdersUtils(session).work(page));
    }
}
