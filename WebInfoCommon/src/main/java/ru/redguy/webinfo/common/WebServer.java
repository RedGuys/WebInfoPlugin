package ru.redguy.webinfo.common;

import fi.iki.elonen.NanoHTTPD;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.redguy.webinfo.common.utils.Logger;
import ru.redguy.webinfo.common.utils.LoggerType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebServer extends NanoHTTPD {

    Reflections reflections;
    Map<String, IWebPage> pages;

    public WebServer(int port) throws IOException {
        super(port);
        ConfigurationBuilder configBuilder =
                new ConfigurationBuilder()
                        .filterInputsBy(new FilterBuilder().includePackage("ru.redguy.webinfocommon.pages"))
                        .setUrls(ClasspathHelper.forPackage("ru.redguy.webinfocommon.pages"))
                        .setScanners(
                                new TypeAnnotationsScanner(),
                                new MethodParameterScanner(),
                                new MethodAnnotationsScanner(),
                                new FieldAnnotationsScanner()
                        );
        this.reflections = new Reflections(configBuilder);
        pageScan();
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    public void pageScan() {
        Logger.info(LoggerType.Client, "Started page scan!");
        pages = new HashMap<>();
        for (Class<?> mClass : reflections.getTypesAnnotatedWith(WebPage.class, true)) {
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

    @Override
    public Response serve(IHTTPSession session) {
        String url = session.getUri();
        url = url.endsWith("/") ? url : url + "/";
        if (pages.containsKey(url)) {
            IWebPage mClass = pages.get(url);
            try {
                return mClass.getPage(session);
            } catch (IOException e) {
                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "Internal error!");
            }
        } else {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not Founded!");
        }
    }
}