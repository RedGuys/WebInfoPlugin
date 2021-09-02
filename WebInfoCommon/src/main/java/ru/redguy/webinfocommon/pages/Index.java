package ru.redguy.webinfocommon.pages;

import fi.iki.elonen.NanoHTTPD;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.WebServer;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebPage(url = "/")
public class Index implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MineV", InfoUtils.getInstance().getMCVersion());
        StringBuilder players = new StringBuilder();
        for (String s : InfoUtils.getInstance().getPlayersList()) {
            players.append(s).append(",");
        }
        if(players.length() > 0) {
            players.deleteCharAt(players.length() - 1);
        }
        jsonObject.put("Players",players.toString());
        jsonObject.put("isClient",false);
        return NanoHTTPD.newFixedLengthResponse(jsonObject.toString());
    }
}
