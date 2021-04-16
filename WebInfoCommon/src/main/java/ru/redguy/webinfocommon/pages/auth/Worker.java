package ru.redguy.webinfocommon.pages.auth;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfocommon.*;
import ru.redguy.webinfocommon.utils.Logger;
import ru.redguy.webinfocommon.utils.LoggerType;
import ru.redguy.webinfocommon.utils.WebUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebPage(url = "/auth/worker")
public class Worker implements IWebPage {
    public NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException {
        try {
            session.parseBody(new HashMap<>());
        } catch (NanoHTTPD.ResponseException e) {
            e.printStackTrace();
        }
        Map<String, List<String>> decodedQueryParameters = WebUtils.decodeParams(session.getQueryParameterString());
        if(!decodedQueryParameters.containsKey("user")) {
            NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
            response.addHeader("Location","/auth/");
            return response;
        } else {
            if(!decodedQueryParameters.containsKey("password")) {
                NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
                response.addHeader("Location","/auth/");
                return response;
            } else {
                User user = UsersConfig.getUser(decodedQueryParameters.get("user").get(0));
                if(user == null) {
                    NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
                    response.addHeader("Location", "/auth/");
                    return response;
                } else {
                    if (!user.password.equals(decodedQueryParameters.get("password").get(0))) {
                        NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
                        response.addHeader("Location", "/auth/");
                        return response;
                    } else {
                        Date date = new Date();
                        String token = WebUtils.MD5(date.getTime()+"MagickSalt");
                        WebUtils.CookieHandler ch = new WebUtils.CookieHandler(session.getHeaders());
                        ch.set("session",token,1);
                        WebStatic.sessions.put(token,user.user);
                        NanoHTTPD.Response response = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT_SEE_OTHER,NanoHTTPD.MIME_PLAINTEXT,"");
                        response.addHeader("Location", "/");
                        ch.unloadQueue(response);
                        Logger.info(LoggerType.Web,user.user + " login from "+session.getRemoteIpAddress()+"!");
                        return response;
                    }
                }
            }
        }
    }
}
