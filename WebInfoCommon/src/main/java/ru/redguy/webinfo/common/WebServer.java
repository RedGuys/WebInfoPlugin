package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.redguy.webinfo.common.structures.Location;
import ru.redguy.webinfo.common.utils.*;

import java.io.IOException;
import java.util.*;

public class WebServer {

    private static final WebServer INSTANCE = new WebServer();

    Server server;
    Reflections reflections;
    List<String> packages = new ArrayList<String>() {{add("ru.redguy.webinfo.common.pages");}};
    Map<Pair<NanoHTTPD.Method,String>, Page> pages;

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
            WebPage annotation = mClass.getAnnotation(WebPage.class);
            String url = annotation.url();
            try {
                pages.put(new Pair<>(annotation.method(),url.endsWith("/") ? url : url + "/"), new Page((IWebPage) mClass.newInstance(),annotation));
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

    public Map<Pair<NanoHTTPD.Method,String>, Page> getPages() {
        return pages;
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

            if (pages.containsKey(new Pair<>(session.getMethod(),url))) {
                Page page = pages.get(new Pair<>(session.getMethod(),url));
                IWebPage mClass = page.getPage();
                HashMap<String, ArrayList<Object>> args = new HashMap<>();
                for (QueryArgument arg : page.getAnnotation().args()) {
                    if(!session.getParameters().containsKey(arg.name()) && arg.required()) {
                        return genResponse(Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.TheVariableIsNotPassed(arg.name()));
                    }
                    switch (arg.type()) {
                        case UUID: {
                            ArrayList<Object> uuid = new ArrayList<>();
                            for (String s : session.getParameters().get(arg.name())) {
                                try {
                                    uuid.add(UUID.fromString(s));
                                } catch (IllegalArgumentException e) {
                                    return genResponse(Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.VariableIncorrect(arg.name()));
                                }
                            }
                            args.put(arg.name(),uuid);
                            break;
                        }
                        case STRING: {
                            List<String> ar = session.getParameters().get(arg.name());
                            args.put(arg.name(),new ArrayList<>(ar == null ? new ArrayList<>() : ar));
                            break;
                        }
                        case LOCATION: {
                            ArrayList<Object> location = new ArrayList<>();
                            for (String s : session.getParameters().get(arg.name())) {
                                try {
                                    location.add(new Location(s));
                                } catch (IndexOutOfBoundsException|NumberFormatException|NullPointerException e) {
                                    return genResponse(Response.Status.BAD_REQUEST,ru.redguy.webinfo.common.utils.Response.VariableIncorrect(arg.name()));
                                }
                            }
                            args.put(arg.name(),location);
                            break;
                        }
                        case WORLD: {
                            ArrayList<Object> worlds = new ArrayList<>();
                            for (String s : session.getParameters().get(arg.name())) {
                                if(Controllers.getWorldsController().isWorldExist(s)) {
                                    worlds.add(s);
                                }
                            }
                            args.put(arg.name(),worlds);
                            break;
                        }
                        case BOOLEAN: {
                            ArrayList<Object> booleans = new ArrayList<>();
                            for (String s : session.getParameters().get(arg.name())) {
                                booleans.add(Boolean.parseBoolean(s));
                            }
                            args.put(arg.name(),booleans);
                        }
                    }
                }
                try {
                    return genResponse(Response.Status.OK, mClass.getPage(session, args));
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

    static class Page {
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