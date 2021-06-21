package ru.redguy.webinfocommon.pages.json;

import fi.iki.elonen.NanoHTTPD;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.redguy.webinfocommon.IWebPage;
import ru.redguy.webinfocommon.WebPage;
import ru.redguy.webinfocommon.structures.Plugin;
import ru.redguy.webinfocommon.utils.InfoUtils;

import java.io.IOException;
import java.util.List;

@WebPage(url="/json/plugins.json")
public class Plugins implements IWebPage {
    @Override
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        JSONObject jsonObject = new JSONObject();
        List<Plugin> plugins = InfoUtils.getInstance().getPluginsList();
        jsonObject.put("count", plugins.size());
        JSONArray array = new JSONArray();
        for (Plugin plugin : plugins) {
            JSONObject obj = new JSONObject();
            obj.put("name",plugin.getName());
            obj.put("version", plugin.getVersion());
            array.put(obj);
        }
        jsonObject.put("plugins", array);
        return NanoHTTPD.newFixedLengthResponse(jsonObject.toString());
    }
}
