package ru.redguy.webinfo.common.utils;

import com.google.common.collect.Lists;
import fi.iki.elonen.NanoHTTPD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSocketRequest extends WebSocketMessage {
    int id;
    String url;
    String method;
    Map<String, Object> params;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public NanoHTTPD.Method getMethod() {
        return NanoHTTPD.Method.valueOf(method);
    }

    public Map<String, List<String>> getParameters() {
        HashMap<String, List<String>> parameters = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            parameters.put(entry.getKey(), Lists.newArrayList(String.valueOf(entry.getValue())));
        }
        return parameters;
    }
}
