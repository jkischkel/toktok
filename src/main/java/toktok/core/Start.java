package toktok.core;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static toktok.http.HttpMethod.GET;

/**
 * @author Jan Kischkel
 */
public class Start {

    private static class JettyActionHandler extends AbstractHandler {

        private final RouteMatcher matcher;

        JettyActionHandler(RouteMatcher matcher) throws Exception {
            this.matcher = matcher;

            Server server = new Server(9090);
            server.setHandler(this);
            server.start();
        }

        public void handle(
                String target,
                Request request,
                HttpServletRequest httpRequest,
                HttpServletResponse httpResponse)
                throws IOException, ServletException {
            Action action = matcher.match(GET, request.getPathInfo());

            if (action != null) {
                ActionResult result = action.apply(new toktok.http.Request());

                httpResponse.setStatus(result.getStatus());
                httpResponse.setContentType("text/html;charset=utf-8");
                httpResponse.getWriter().println(result.getContent());

            } else {
                httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            request.setHandled(true);
        }
    }

    public static void jetty(RouteMatcher matcher) {
        try {
            new JettyActionHandler(matcher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Start() {}
}
