package toktok.core;

import toktok.http.HttpMethod;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        RouteMatcher matcher = new RouteMatcher();
        matcher.register(HttpMethod.GET, "/",           req -> "hello");
        matcher.register(HttpMethod.GET, "//",          req -> "double");
        matcher.register(HttpMethod.GET, "/users",      req -> "users");
        matcher.register(HttpMethod.GET, "/posts",      req -> "posts");
        matcher.register(HttpMethod.GET, "/sounds/:id", req -> "sounds");

        Start.jetty(matcher);
    }
}
