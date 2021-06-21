package ru.redguy.webinfocommon.pages.json;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONObject;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.utils.InfoUtils;

@WebPage(url="/json/mainData.json")
public class MainData implements IWebPage {

    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) {
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
