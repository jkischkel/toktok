package toktok.core;

import java.io.IOException;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bootstrap {

    static class RequestHandler extends AbstractHandler {
        public void handle(
                    String target,
                    Request baseRequest,
                    HttpServletRequest request,
                    HttpServletResponse response)
                throws IOException, ServletException {

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            response.getWriter().printf(
                    "<h1>%s '%s'</h1>\n",
                    baseRequest.getMethod(),
                    baseRequest.getPathInfo());
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new RequestHandler());
        server.start();
    }
}
