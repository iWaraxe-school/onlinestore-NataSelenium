package http.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseAuthHandler implements HttpHandler {
    private HttpHandler next;
    Base64.Encoder encoder = Base64.getEncoder();
    String auth = "user" + ":" + "pass";
    ;
    public BaseAuthHandler(HttpHandler next) {
        this.next = next;
    }
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getRequestHeaders().put(Headers.AUTHORIZATION, "Basic " + encoder.encodeToString(auth.getBytes(StandardCharsets.UTF_8)));
        String authorization = exchange.getRequestHeaders().getFirst(Headers.AUTHORIZATION);
        if (checkAuth(authorization))
        {
            next.handleRequest(exchange);
        }
        else
        {
            exchange.getResponseHeaders().put(Headers.WWW_AUTHENTICATE, "Basic realm=\"Secure Area\"");
            exchange.setStatusCode(401);
        }
    }

    public boolean checkAuth(String authorizationInfo)
    {
        String[] split = authorizationInfo.split(" ");
        if (split.length == 2) {
            authorizationInfo = new String(Base64.getDecoder().decode(split[1]), StandardCharsets.UTF_8);
        }
        return auth.equals(authorizationInfo);

    }
}
