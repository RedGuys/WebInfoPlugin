package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;

public interface IWebPage {
    Response getPage(NanoHTTPD.IHTTPSession session) throws Exception;
}
