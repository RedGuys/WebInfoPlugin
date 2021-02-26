package ru.redguy.webinfocommon;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public interface IWebPage {
    NanoHTTPD.Response getPage(NanoHTTPD.IHTTPSession session) throws IOException;
}
