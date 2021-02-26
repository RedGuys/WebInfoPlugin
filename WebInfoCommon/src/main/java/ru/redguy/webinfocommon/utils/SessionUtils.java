package ru.redguy.webinfocommon.utils;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.UsersConfig;
import ru.redguy.webinfocommon.WebStatic;

import java.util.Objects;

public class SessionUtils {
    public static boolean checkAdmin(NanoHTTPD.IHTTPSession session) {
        WebUtils.CookieHandler ch = new WebUtils.CookieHandler(session.getHeaders());
        if(WebStatic.sessions.containsKey(ch.read("session"))) {
            return Objects.requireNonNull(UsersConfig.getUser(WebStatic.sessions.get(ch.read("session")))).isAdmin;
        }
        return false;
    }
}
