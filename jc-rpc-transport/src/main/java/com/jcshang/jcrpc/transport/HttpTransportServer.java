package com.jcshang.jcrpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Http based transport server class.
 *
 * @author JcShang
 */
@Slf4j
public class HttpTransportServer implements TransportServer {
    private RequestHandler handler;
    private Server server;

    @Override
    public void initialize(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("Client connected.");

            InputStream input = req.getInputStream();
            OutputStream output = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(input, output);
            }

            output.flush();
        }
    }
}
