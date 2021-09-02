package ru.redguy.webinfocommon.pages.mods;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.structures.Mod;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;
import java.util.List;

@WebPage(url="/mods/")
public class Mods implements IWebPage {
    @Override
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<Mod> mods = InfoUtils.getInstance().getPluginsList();
        jsonObject.put("count", mods.size());
        JSONArray array = new JSONArray();
        for (Mod mod : mods) {
            JSONObject obj = new JSONObject();
            obj.put("name", mod.getName());
            obj.put("version", mod.getVersion());
            array.put(obj);
        }
        jsonObject.put("mods", array);
        return NanoHTTPD.newFixedLengthResponse(jsonObject.toString());
    }
}
