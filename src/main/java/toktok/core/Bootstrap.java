package toktok.core;

import org.eclipse.jetty.server.Server;
import toktok.http.HttpMethod;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        RouteMatcher matcher = new RouteMatcher();
        matcher.register(HttpMethod.GET, "/users",      req -> "users");
        matcher.register(HttpMethod.GET, "/posts",      req -> "posts");
        matcher.register(HttpMethod.GET, "/sounds/:id", req -> "sounds");

        RouteHandler handler = new RouteHandler(matcher);
        Server server = new Server(8080);
        server.setHandler(handler);
        server.start();
    }
}
