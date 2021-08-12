package ru.redguy.webinfocommon.pages.json;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.structures.World;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;
import java.util.List;

@WebPage(url="/json/worlds.json")
public class Worlds implements IWebPage {
    @Override
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<World> worlds = InfoUtils.getInstance().getWorldsList();
        jsonObject.put("count", worlds.size());
        JSONArray array = new JSONArray();
        for (World world : worlds) {
            array.put(world.toJSONObject());
        }
        jsonObject.put("worlds", array);
        return NanoHTTPD.newFixedLengthResponse(jsonObject.toString());
    }
}