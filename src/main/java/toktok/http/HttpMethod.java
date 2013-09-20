package toktok.http;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import toktok.core.Action;
import toktok.core.RouteMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum HttpMethod {

    GET,

    POST,

    PUT;


    public static Set<HttpMethod> all() {
        return new HashSet<>(Arrays.asList(HttpMethod.values()));
    }

    public static HttpMethod byName(String name) {
        return  all().stream()
                .filter(m -> m.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static class RouteHandler extends AbstractHandler {

        private final RouteMatcher matcher;

        public RouteHandler(RouteMatcher matcher) {
            this.matcher = matcher;
        }

        public void handle(
                String target,
                Request request,
                HttpServletRequest httpRequest,
                HttpServletResponse httpResponse)
                throws IOException, ServletException {

            Action action = matcher.match(GET, request.getPathInfo());

            if (action != null) {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                httpResponse.setContentType("text/html;charset=utf-8");
                httpResponse.getWriter().printf("<h1>%s</h1>\n", action.apply(""));

            } else {
                httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            request.setHandled(true);
        }
    }
}
