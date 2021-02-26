package ru.redguy.webinfocommon;

import fi.iki.elonen.NanoHTTPD;
import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class WebServer extends NanoHTTPD {

    Reflections reflections;

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
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {

        if(session.getUri().startsWith("/css/")||session.getUri().startsWith("/js/")) {
            String path = "/resources/web"+session.getUri();
            try {
                //TODO: 400 код при авторизоавном доступе
                return newFixedLengthResponse(Response.Status.OK,session.getUri().startsWith("/css/") ? "text/css" : "application/javascript", IOUtils.toString(WebServer.class.getResourceAsStream(path), StandardCharsets.UTF_8));
            } catch (IOException ignored) {
            }

        } else {
            for (Class<?> mClass : reflections.getTypesAnnotatedWith(WebPage.class, true)) {
                if (session.getUri().equals(mClass.getAnnotation(WebPage.class).url()) || session.getUri().equals(mClass.getAnnotation(WebPage.class).url()+"/")) {
                    try {
                        return (Response) mClass.getMethod("getPage", IHTTPSession.class).invoke(mClass.newInstance(), session);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return newFixedLengthResponse(Response.Status.NOT_FOUND,"text/plain","Not Founded!");
    }
}