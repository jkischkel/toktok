package toktok.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static toktok.http.HttpMethod.*;

public class RouteMatcherTest {

    RouteMatcher routes;

    Action testAction = req -> "test";

    @Before
    public void initialize() {
        routes = new RouteMatcher();
    }

    @Test
    public void itShouldSupportResetting() {
        routes.register(GET, "/", testAction);
        assertNotNull(routes.match(GET, "/"));

        routes.clear();
        assertNull(routes.match(GET, "/"));
    }

    @Test
    public void itShouldSupportHandlerRegistration() {
        routes.register(GET, "/hello", testAction);
        Action match = routes.match(GET, "/hello");

        assertNotNull(match);
        assertNotNull(match);
        assertEquals("test", match.apply(null));
    }

    @Test
    public void itShouldOnlyMatchTheCorrectMethod() {
        routes.register(PUT, "/", testAction);

        assertNull(routes.match(GET, "/"));
    }

    @Test
    public void itShouldMatchByRegex() {
        routes.register(GET, "/users/:id", testAction);

        Arrays.asList("/users/1", "/users/2", "/users/3").forEach(route ->
            assertNotNull(routes.match(GET, route))
        );

        Arrays.asList("/", "/users/", "/users/4/details").forEach(route ->
            assertNull(routes.match(GET, route))
        );
    }
}
