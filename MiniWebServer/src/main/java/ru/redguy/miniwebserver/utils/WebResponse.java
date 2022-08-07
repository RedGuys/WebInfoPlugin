package ru.redguy.miniwebserver.utils;

import fi.iki.elonen.NanoHTTPD;

import java.util.HashMap;

public class WebResponse {
    private NanoHTTPD.Response.Status status = NanoHTTPD.Response.Status.OK;
    private Object response = "";
    private String mimeType = "text/plain";
    private HashMap<String, String> headers = new HashMap<>();
    public NanoHTTPD.Response.Status getStatus() {
        return status;
    }

    public void setStatus(NanoHTTPD.Response.Status status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean hasHeader(String header) {
        return headers.containsKey(header);
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public void setHeader(String header, String value) {
        headers.put(header, value);
    }

    public void removeHeader(String header) {
        headers.remove(header);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
