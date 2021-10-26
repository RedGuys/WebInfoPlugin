package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IWebPage {
    Response getPage(NanoHTTPD.IHTTPSession session, HashMap<String, ArrayList<Object>> args) throws Exception;
}
