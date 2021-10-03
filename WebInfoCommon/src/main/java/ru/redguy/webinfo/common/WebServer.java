package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.redguy.webinfo.common.utils.GSON;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;
import ru.redguy.webinfo.common.utils.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebServer {

    private static final WebServer INSTANCE = new WebServer();

    Server server;
    Reflections reflections;
    List<String> packages = new ArrayList<String>() {{add("ru.redguy.webinfo.common.pages");}};
    Map<String, IWebPage> pages;

    public WebServer() {

    }

    /**
     * Updates reflection instance, needs after adding package
     */
    public void updateReflection() {
        Logger.info(LoggerType.Client, "Started reflection update!");
        FilterBuilder fb = new FilterBuilder();
        for (String pack : packages) {
            fb.includePackage(pack);
        }

        ConfigurationBuilder configBuilder =
                new ConfigurationBuilder()
                        .filterInputsBy(fb)
                        .setScanners(
                                new TypeAnnotationsScanner(),
                                new MethodParameterScanner(),
                                new MethodAnnotationsScanner(),
                                new FieldAnnotationsScanner(),
                                new SubTypesScanner()
                        );

        for (String aPackage : packages) {
            configBuilder.addUrls(ClasspathHelper.forPackage(aPackage));
        }

        this.reflections = new Reflections(configBuilder);
        Logger.info(LoggerType.Client, "Reflection updated with " + packages.size() + " packages!");
    }

    /**
     * Scans packages for webpages
     */
    public void pageScan() {
        Logger.info(LoggerType.Client, "Started page scan!");
        pages = new HashMap<>();
        for (Class<?> mClass : reflections.getTypesAnnotatedWith(WebPage.class, false)) {
            if (!IWebPage.class.isAssignableFrom(mClass)) continue;
            String url = mClass.getAnnotation(WebPage.class).url();
            try {
                pages.put(url.endsWith("/") ? url : url + "/", (IWebPage) mClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Logger.info(LoggerType.Client, "Indexed " + pages.keySet().size() + " pages!");
    }

    /**
     * Adds package with pages
     * @param pack package.
     */
    public void addPackage(String pack) {
        packages.add(pack);
    }

    public void startServer(int port) throws IOException {
        server = new Server(port);
        server.startServer();
    }

    public static WebServer getInstance() {
        return INSTANCE;
    }

    private class Server extends NanoHTTPD {
        public Server(int port) {
            super(port);
        }

        public void startServer() throws IOException {
            start();
        }

        @Override
        public Response serve(IHTTPSession session) {
            String url = session.getUri();
            url = url.endsWith("/") ? url : url + "/";

            if (pages.containsKey(url)) {
                IWebPage mClass = pages.get(url);
                try {
                    return genResponse(Response.Status.OK, mClass.getPage(session));
                } catch (Exception e) {
                    e.printStackTrace();
                    return genResponse(Response.Status.INTERNAL_ERROR, ru.redguy.webinfo.common.utils.Response.InternalError());
                }
            } else {
                return genResponse(Response.Status.NOT_FOUND, ru.redguy.webinfo.common.utils.Response.MethodNotFound());
            }
        }

        private Response genResponse(Response.Status status, ru.redguy.webinfo.common.utils.Response response) {
            return newFixedLengthResponse(status,"application/json", GSON.gson.toJson(response));
        }
    }
}