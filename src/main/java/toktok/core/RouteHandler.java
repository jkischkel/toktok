package toktok.core;

import static toktok.http.HttpMethod.*;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteHandler extends AbstractHandler {

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
            httpResponse.getWriter().printf("<h1>%s</h1>\n", action.apply(null));

        } else {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        request.setHandled(true);
    }
}