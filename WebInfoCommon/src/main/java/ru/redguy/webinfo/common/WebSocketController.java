package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.redguy.webinfo.common.controllers.Controllers;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WebSocketController {

    private static final WebSocketController INSTANCE = new WebSocketController();

    Client client;
    Reflections reflections;
    List<String> packages = new ArrayList<String>() {{add("ru.redguy.webinfo.common.pages");}};
    PagesMap pages;

    public WebSocketController() {

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
        pages = new PagesMap();
        for (Class<?> mClass : reflections.getTypesAnnotatedWith(WebPage.class, false)) {
            if (!IWebPage.class.isAssignableFrom(mClass)) continue;
            WebPage annotation = mClass.getAnnotation(WebPage.class);
            String url = annotation.url();
            try {
                pages.put(annotation.method(),url.endsWith("/") ? url : url + "/", new WebServer.Page((IWebPage) mClass.newInstance(),annotation));
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

    public void connect(String domain, String key) throws Exception {
        client = new Client(domain, key);
        client.connect();
    }

    public PagesMap getPages() {
        return pages;
    }

    public static WebSocketController getInstance() {
        return INSTANCE;
    }

    private class Client extends WebSocketClient {

        public Client(String domain, String key) throws URISyntaxException {
            super(new URI(domain+"/server/"+key));
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {

        }

        @Override
        public void onMessage(String message) {
            switch (GSON.gson.fromJson(message, WebSocketMessage.class).getType()) {
                case "request": {
                    WebSocketRequest req = GSON.gson.fromJson(message,WebSocketRequest.class);
                    String url = req.getUrl();
                    url = url.endsWith("/") ? url : url + "/";
                    if (pages.containsKey(req.getMethod(),url)) {
                        WebServer.Page page = pages.get(req.getMethod(),url);
                        IWebPage mClass = page.getPage();
                        HashMap<String, ArrayList<Object>> args = new HashMap<>();
                        for (QueryArgument arg : page.getAnnotation().args()) {
                            if(!req.getParameters().containsKey(arg.name()) && arg.required()) {
                                genResponse(req.getId(), NanoHTTPD.Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.TheVariableIsNotPassed(arg.name()));
                                break;
                            }
                            switch (arg.type()) {
                                case UUID: {
                                    ArrayList<Object> uuid = new ArrayList<>();
                                    if(req.getParameters().get(arg.name()) != null)
                                        for (String s : req.getParameters().get(arg.name())) {
                                            try {
                                                uuid.add(UUID.fromString(s));
                                            } catch (IllegalArgumentException e) {
                                                genResponse(req.getId(),NanoHTTPD.Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.VariableIncorrect(arg.name()));
                                                break;
                                            }
                                        }
                                    args.put(arg.name(),uuid);
                                    break;
                                }
                                case STRING: {
                                    List<String> ar = req.getParameters().get(arg.name());
                                    args.put(arg.name(),new ArrayList<>(ar == null ? new ArrayList<>() : ar));
                                    break;
                                }
                                case LOCATION: {
                                    ArrayList<Object> location = new ArrayList<>();
                                    for (String s : req.getParameters().get(arg.name())) {
                                        try {
                                            location.add(new Location(s));
                                        } catch (IndexOutOfBoundsException|NumberFormatException|NullPointerException e) {
                                            genResponse(req.getId(),NanoHTTPD.Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.VariableIncorrect(arg.name()));
                                            break;
                                        }
                                    }
                                    args.put(arg.name(),location);
                                    break;
                                }
                                case WORLD: {
                                    ArrayList<Object> worlds = new ArrayList<>();
                                    for (String s : req.getParameters().get(arg.name())) {
                                        if(Controllers.getWorldsController().isWorldExist(s)) {
                                            worlds.add(s);
                                        }
                                    }
                                    args.put(arg.name(),worlds);
                                    break;
                                }
                                case BOOLEAN: {
                                    ArrayList<Object> booleans = new ArrayList<>();
                                    for (String s : req.getParameters().get(arg.name())) {
                                        booleans.add(Boolean.parseBoolean(s));
                                    }
                                    args.put(arg.name(),booleans);
                                }
                            }
                        }
                        try {
                            genResponse(req.getId(),NanoHTTPD.Response.Status.OK, mClass.getPage(new Request(), args));
                            break;
                        } catch (UnsupportedOperationException e) {
                            genResponse(req.getId(),NanoHTTPD.Response.Status.BAD_REQUEST, ru.redguy.webinfo.common.utils.Response.UnsupportedOperation());
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            genResponse(req.getId(),NanoHTTPD.Response.Status.INTERNAL_ERROR, ru.redguy.webinfo.common.utils.Response.InternalError());
                            break;
                        }
                    } else {
                        genResponse(req.getId(),NanoHTTPD.Response.Status.NOT_FOUND, ru.redguy.webinfo.common.utils.Response.MethodNotFound());
                        break;
                    }
                }
            }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {

        }

        @Override
        public void onError(Exception ex) {

        }


        public void genResponse(int id,NanoHTTPD.Response.Status status, ru.redguy.webinfo.common.utils.Response response) {
            this.send(GSON.gson.toJson(new WebSocketResponse(id,status.getRequestStatus(),GSON.gson.toJson(response))));
        }
    }

    public static class Page {
        private final IWebPage page;
        private final WebPage annotation;

        public Page(IWebPage page, WebPage annotation) {
            this.page = page;
            this.annotation = annotation;
        }

        public IWebPage getPage() {
            return page;
        }

        public WebPage getAnnotation() {
            return annotation;
        }
    }
}
