package ru.redguy.miniwebserver;

import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import ru.redguy.miniwebserver.utils.*;
import ru.redguy.miniwebserver.utils.arguments.BooleanArgument;
import ru.redguy.miniwebserver.utils.arguments.QueryArgumentType;
import ru.redguy.miniwebserver.utils.arguments.StringArgument;
import ru.redguy.miniwebserver.utils.arguments.UUIDArgument;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class WebServer {
    private Server server;
    private final List<Object> routers;
    private List<DoubleConsumer<WebRequest, WebResponse>> requestMiddlewares = new ArrayList<>();
    private List<DoubleConsumer<WebRequest, WebResponse>> responseMiddlewares = new ArrayList<>();
    private HashMap<Class<? extends QueryArgumentType>, QueryArgumentType> arguments = new HashMap<Class<? extends QueryArgumentType>, QueryArgumentType>();
    private PagesMap pages;

    protected WebServer(List<Object> routers, HashMap<Class<? extends QueryArgumentType>, QueryArgumentType> arguments, List<DoubleConsumer<WebRequest, WebResponse>> requestMiddlewares, List<DoubleConsumer<WebRequest, WebResponse>> responseMiddlewares) {
        this.routers = routers;
        this.arguments = arguments;
        this.requestMiddlewares = requestMiddlewares;
        this.responseMiddlewares = responseMiddlewares;
        addArgumentParser(new UUIDArgument());
        addArgumentParser(new StringArgument());
        addArgumentParser(new BooleanArgument());
        pageScan();
    }

    public void addArgumentParser(QueryArgumentType argument) {
        arguments.put(argument.getClass(), argument);
    }

    public void pageScan() {
        pages = new PagesMap();
        for (Object router : routers) {
            if (router.getClass().getAnnotation(Router.class)==null) continue;
            for (Method method : router.getClass().getDeclaredMethods()) {
                WebPage webPage = method.getAnnotation(WebPage.class);
                if (webPage == null) continue;
                String url = router.getClass().getAnnotation(Router.class).value()+webPage.value();
                url = url.replaceAll("//","/");
                pages.put(webPage.method(), url.endsWith("/") ? url : url + "/", new Page(router, method, webPage));
            }
        }
    }

    public void startServer(int port) throws IOException {
        server = new Server(port);
        server.startServer();
    }

    public PagesMap getPages() {
        return pages;
    }

    private class Server extends NanoHTTPD {
        public Server(int port) {
            super(port);
        }

        public void startServer() throws IOException {
            start();
        }

        @Override
        public @NotNull Response serve(@NotNull IHTTPSession session) {
            String url = session.getUri();
            url = url.endsWith("/") ? url : url + "/";
            WebRequest req = new WebRequest();
            WebResponse res = new WebResponse();
            for (DoubleConsumer<WebRequest, WebResponse> middleware : requestMiddlewares) {
                middleware.accept(req, res);
                if (req.isCanceled()) return genResponse(req, res);
            }
            if (!pages.containsKey(session.getMethod(), url)) {
                res.setResponse("Not Found");
                res.setStatus(Response.Status.NOT_FOUND);
                return genResponse(req, res);
            }
            Page page = pages.get(session.getMethod(), url);
            HashMap<String, ArrayList<Object>> args = new HashMap<>();
            for (QueryArgument arg : page.getAnnotation().args()) {
                if (!session.getParameters().containsKey(arg.name()) && arg.required()) {
                    res.setResponse(new ArgumentNotPassed(arg.name()));
                    res.setStatus(Response.Status.BAD_REQUEST);
                }

                QueryArgumentType type = arguments.get(arg.type());
                ArrayList<Object> parsedArgs = new ArrayList<>();
                if (session.getParameters().get(arg.name()) != null) {
                    for (String s : session.getParameters().get(arg.name())) {
                        if (type.isCorrect(s)) {
                            parsedArgs.add(type.parseArgument(s));
                        } else {
                            res.setResponse(new ArgumentIncorrect(arg.name()));
                            res.setStatus(Response.Status.BAD_REQUEST);
                        }
                    }
                }
                args.put(arg.name(), parsedArgs);
            }
            req.setArguments(args);
            try {
                page.getMethod().invoke(page.getHolder(), req, res);
                return genResponse(req, res);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                res.setResponse("Internal error");
                res.setStatus(Response.Status.INTERNAL_ERROR);
                return genResponse(req, res);
            }
        }

        /*private @NotNull Response genResponse(Response.Status status, ru.redguy.webinfo.common.utils.Response response) {
            Response res = NanoHTTPD.newFixedLengthResponse(status, "application/json", GSON.gson.toJson(response));
            res.addHeader("Access-Control-Allow-Origin", "*");
            return res;
        }*/

        private @NotNull Response genResponse(WebRequest req, @NotNull WebResponse res) {
            for (DoubleConsumer<WebRequest, WebResponse> middleware : responseMiddlewares) {
                middleware.accept(req, res);
                if (req.isCanceled()) break;
            }
            if (!(res.getResponse() instanceof String)) {
                if(res.getResponse() instanceof ArgumentIncorrect) {
                    res.setResponse("Argument "+((ArgumentIncorrect) res.getResponse()).getName()+" incorrect");
                } else if(res.getResponse() instanceof ArgumentNotPassed) {
                    res.setResponse("Argument "+((ArgumentNotPassed) res.getResponse()).getName()+" not passed");
                } else {
                    res.setResponse(GSON.gson.toJson(res.getResponse()));
                }
            }
            Response response = NanoHTTPD.newFixedLengthResponse(res.getStatus(), res.getMimeType(), (String) res.getResponse());
            for (Map.Entry<String, String> entry : res.getHeaders().entrySet()) {
                response.addHeader(entry.getKey(), entry.getValue());
            }
            return response;
        }
    }
}