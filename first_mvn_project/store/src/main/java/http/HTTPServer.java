package http;


import http.handlers.BaseAuthHandler;
import http.handlers.CategoryHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.MetricsHandler;

public class HTTPServer {
    public static void main(final String[] args) {

        RoutingHandler router = Handlers.routing();
        router.get("/admin/*", exchange -> {
            exchange.getResponseSender().send(exchange.getRelativePath());
        });

        HttpHandler handler = new MetricsHandler(router);
        handler = new CategoryHandler(handler);
        handler = new BaseAuthHandler(handler);
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handler)
                .setIoThreads(1)
                .build();
        server.start();
    }
}