package ru.redguy.webinfo.common;

import ru.redguy.webinfo.common.utils.Request;
import ru.redguy.webinfo.common.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;

public interface IWebPage {
    Response getPage(Request req, HashMap<String, ArrayList<Object>> args) throws Exception;
}
