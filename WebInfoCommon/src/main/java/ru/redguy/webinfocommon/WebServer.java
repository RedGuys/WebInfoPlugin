package ru.redguy.webinfocommon;

import fi.iki.elonen.NanoHTTPD;
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
        for (Class<?> mClass : reflections.getTypesAnnotatedWith(WebPage.class, true)) {
            if (session.getUri().equals(mClass.getAnnotation(WebPage.class).url()) || session.getUri().equals(mClass.getAnnotation(WebPage.class).url() + "/")) {
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
        return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "Not Founded!");
    }
}