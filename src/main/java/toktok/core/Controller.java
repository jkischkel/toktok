package toktok.core;

import static toktok.http.HttpMethod.*;

/**
 * @author Jan Kischkel
 */
public interface Controller {

    default void get(String route, Action action) {
        routeMatcher().register(GET, route, action);
    }

    default void post(String route, Action action) {
        routeMatcher().register(POST, route, action);
    }

    default void put(String route, Action action) {
        routeMatcher().register(PUT, route, action);
    }

    default RouteMatcher routeMatcher() {
        return RouteMatcher.DefaultMatcher.instance.matcher();
    }
}
