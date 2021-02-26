package ru.redguy.webinfocommon;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UsersConfig {

    private static JSONArray users;

    public static void loadUsers(String path) {
        try {
            users = new JSONArray(FileUtils.readFileToString(new File(path,"WebInfoUsers.json")));
        } catch (IOException e) {
            try {
                if(new File(path,"WebInfoUsers.json").createNewFile()) {
                    JSONArray array = new JSONArray();
                    array.put(new User("admin","adminlol",false).toJSONObject());
                    users = array;
                    FileUtils.writeStringToFile(new File(path,"WebInfoUsers.json"),array.toString());
                } else {
                    //TODO: I cant load
                }
            } catch (IOException ioException) {
                //TODO: I cant load
            }
        }
    }

    public static User getUser(String name) {
        for (Object user : users) {
            JSONObject object = (JSONObject)user;
            if(name.equals(object.getString("user"))) {
                return new User(object);
            }
        }
        return null;
    }
}
