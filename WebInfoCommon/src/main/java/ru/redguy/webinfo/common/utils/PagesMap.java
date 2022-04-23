package ru.redguy.webinfo.common.utils;

import fi.iki.elonen.NanoHTTPD;
import ru.redguy.webinfo.common.WebServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class PagesMap {

    private HashMap<String, WebServer.Page> pages;

    public PagesMap() {
        pages = new HashMap<>();
    }

    public void put(NanoHTTPD.Method method, String path, WebServer.Page page) {
        pages.put(join(method,path),page);
    }

    public boolean containsKey(NanoHTTPD.Method method, String path) {
        return pages.containsKey(join(method,path));
    }

    public WebServer.Page get(NanoHTTPD.Method method, String path) {
        return pages.get(join(method,path));
    }

    public Set<Pair<NanoHTTPD.Method,String>> keySet() {
        return pages.keySet().stream().map(this::split).collect(Collectors.toSet());
    }

    private String join(NanoHTTPD.Method method, String path) {
        return method.name()+":"+path;
    }

    private Pair<NanoHTTPD.Method,String> split(String path) {
        String[] parts = path.split(":");
        return new Pair<>(NanoHTTPD.Method.valueOf(parts[0]),String.join(":", Arrays.copyOfRange(parts,1,parts.length)));
    }
}
