package ru.redguy.miniwebserver;

import ru.redguy.miniwebserver.utils.DoubleConsumer;
import ru.redguy.miniwebserver.utils.WebRequest;
import ru.redguy.miniwebserver.utils.WebResponse;
import ru.redguy.miniwebserver.utils.arguments.QueryArgumentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebServerBuilder {
    private final List<Object> packages = new ArrayList<>();
    private final HashMap<Class<? extends QueryArgumentType>, QueryArgumentType> arguments = new HashMap<>();
    private final List<DoubleConsumer<WebRequest, WebResponse>> requestMiddlewares = new ArrayList<>();
    private final List<DoubleConsumer<WebRequest, WebResponse>> responseMiddlewares = new ArrayList<>();

    public WebServerBuilder() {

    }

    public WebServerBuilder addRouter(Object router) {
        packages.add(router);
        return this;
    }

    public WebServerBuilder addRouters(List<Object> routers) {
        packages.addAll(routers);
        return this;
    }

    public WebServerBuilder addArgumentParser(QueryArgumentType argument) {
        arguments.put(argument.getClass(),argument);
        return this;
    }

    public WebServerBuilder addRequestMiddleware(DoubleConsumer<WebRequest,WebResponse> middleware) {
        requestMiddlewares.add(middleware);
        return this;
    }

    public WebServerBuilder addResponseMiddleware(DoubleConsumer<WebRequest,WebResponse> middleware) {
        responseMiddlewares.add(middleware);
        return this;
    }

    public WebServer build() {
        return new WebServer(packages,arguments,requestMiddlewares,responseMiddlewares);
    }
}
