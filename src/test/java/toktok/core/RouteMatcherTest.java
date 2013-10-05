package toktok.core;

import org.junit.Before;
import org.junit.Test;
import toktok.render.Renderer;

import java.util.Arrays;

import static org.junit.Assert.*;
import static toktok.http.HttpMethod.*;

public class RouteMatcherTest {

    RouteMatcher routes;

    Action testAction = req -> Renderer.instance.text("test");

    @Before
    public void initialize() {
        routes = new RouteMatcher();
    }

    @Test
    public void itShouldSupportHandlerRegistration() {
        routes.register(GET, "/hello", testAction);
        Action match = routes.match(GET, "/hello");

        assertNotNull(match);
        assertEquals("test", match.apply(null).getContent());
    }

    @Test
    public void itShouldOnlyMatchTheCorrectMethod() {
        routes.register(PUT, "/", testAction);

        assertNull(routes.match(GET, "/"));
    }

    @Test
    public void itShouldAcceptTrailingSlash() {
        routes.register(GET, "/posts", testAction);

        assertNotNull(routes.match(GET, "/posts/"));
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

    @Test
    public void itShouldProvideDefaultMatcher() {
        RouteMatcher matcher = RouteMatcher.DefaultMatcher.instance.matcher();
        matcher.register(GET, "/", testAction);

        assertNotNull(matcher.match(GET, "/"));
    }
}
