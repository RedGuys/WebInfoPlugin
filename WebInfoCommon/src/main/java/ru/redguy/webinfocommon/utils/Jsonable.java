package ru.redguy.webinfocommon.utils;

import org.json.JSONObject;

public interface Jsonable {
    String toJSON();

    JSONObject toJSONObject();
}
