package ru.redguy.webinfocommon;

import org.json.JSONObject;
import ru.redguy.webinfocommon.utils.Jsonable;

public class User implements Jsonable {

    public String user;
    public String password;
    public boolean isAdmin;

    public User(JSONObject json) {
        user = json.optString("user","admin");
        password = json.optString("password","admin");
        isAdmin = json.optBoolean("isAdmin",false);
    }

    public User(String user, String password, boolean isAdmin) {
        this.user = user;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toJSON() {
        return toJSONObject().toString();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user",user);
        jsonObject.put("password",password);
        jsonObject.put("isAdmin",isAdmin);
        return jsonObject;
    }

}
