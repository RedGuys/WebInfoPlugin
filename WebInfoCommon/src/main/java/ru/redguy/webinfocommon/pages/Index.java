package ru.redguy.webinfocommon.pages;

import fi.iki.elonen.NanoHTTPD;
import org.apache.commons.io.IOUtils;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.WebServer;
import ru.redguy.webinfocommon.utils.PlaceholdersUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebPage(url = "/")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        String path = "/resources/web/index.html";
        String page = IOUtils.toString(WebServer.class.getResourceAsStream(path), StandardCharsets.UTF_8);
        return NanoHTTPD.newFixedLengthResponse(new PlaceholdersUtils(session).work(page));
    }
}
