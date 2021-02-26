package ru.redguy.webinfocommon.utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Language {

    private static String path;
    private static JSONObject jsonObject;

    public static void initLanguage(String path) {
        new File(path,"lang").mkdirs();
        Language.path = path;
        load();
    }

    public static void load() {
        try {
            jsonObject = new JSONObject(FileUtils.readFileToString(new File(path+"/lang",Config.getString("general.lang")+".json")));
        } catch (IOException e) {
            try {
                new File(path+"/lang",Config.getString("general.lang")+".json").createNewFile();
                FileUtils.writeStringToFile(new File(path+"/lang",Config.getString("general.lang")+".json"),"{}");
            } catch (IOException ignored) {}
            jsonObject = new JSONObject();
        }

        setIfNull("link.auth","Войти");
        setIfNull("link.logout", "Выйти");
        setIfNull("link.control_panel","Панель управления");

        setIfNull("title.auth", "Авторизация");

        setIfNull("word.user","Пользователь");
        setIfNull("word.password", "Пароль");
        setIfNull("word.loading","Загрузка");
    }

    public static void save() {
        try {
            FileUtils.writeStringToFile(new File(path+"/lang",Config.getString("general.lang")+".json"),jsonObject.toString());
        } catch (IOException e) {
            Logger.error(LoggerType.Client,"Something went wrong when language save");
        }
    }

    private static JSONObject getNode(JSONObject rootNode, String path) {
        JSONObject node = rootNode;
        for (String s : path.split("\\.")) {
            try {
                node = node.getJSONObject(s);
            } catch (JSONException e) {
                JSONObject j = new JSONObject();
                node.put(s,j);
                node = j;
            }
        }
        return node;
    }

    public static String get(String path) {
        return getNode(jsonObject,path.substring(0,path.lastIndexOf("."))).getString(path.substring(path.lastIndexOf(".")+1));
    }

    public static void setIfNull(String path, Object value) {
        JSONObject node = getNode(jsonObject,path.substring(0,path.lastIndexOf(".")));
        if(node.isNull(path.substring(path.lastIndexOf(".")+1))) {
            node.put(path.substring(path.lastIndexOf(".")+1),value);
        }
    }
}
