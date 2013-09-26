package toktok.core;

import static toktok.http.HttpMethod.*;

import org.junit.Before;
import org.junit.Test;
import toktok.http.HttpMethod;
import toktok.render.Renderer;
import toktok.render.TextRenderer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jan Kischkel
 */
public class ControllerTest {

    TestController ctrl;

    Renderer r = Renderer.instance;

    @Before
    public void initialize() {
        ctrl = new TestController();
    }

    @Test
    public void itShouldRegisterGetRoutes() {
        ctrl.get("/get/route/:id", (req) -> r.text("getRoute"));
        assertEquals("getRoute", ctrl.lookup(GET, "/get/route/1"));
    }

    @Test
    public void itShouldRegisterPostRoutes() {
        ctrl.post("/post/route/:id", (req) -> r.text("postRoute"));
        assertEquals("postRoute", ctrl.lookup(POST, "/post/route/2"));
    }

    @Test
    public void itShouldRegisterPutRoutes() {
        ctrl.put("/put/route/:id", (req) -> r.text("putRoute"));
        assertEquals("putRoute", ctrl.lookup(PUT, "/put/route/3"));
    }

    @Test
    public void itShouldOnlyRegisterForGivenHttpMethod() {
        String route = "/exclusive";
        ctrl.post(route, (req) -> r.text(""));

        assertNull(ctrl.lookup(GET, route));
        assertNotNull(ctrl.lookup(POST, route));
    }

    private static class TestController implements Controller {
        RouteMatcher matcher = new RouteMatcher();

        @Override
        public RouteMatcher routeMatcher() {
            return matcher;
        }

        Object lookup(HttpMethod method, String route) {
            Action match = routeMatcher().match(method, route);

            return match != null ? match.apply(null).getContent() : match;
        }
    }
}
