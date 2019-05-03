package ru.neustupov;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.neustupov.servlet.AllRequestsServlet;

public class App {

  public static void main(String[] args) throws Exception {
    AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.addServlet(new ServletHolder(allRequestsServlet), "/*");

    Server server = new Server(8080);
    server.setHandler(context);

    server.start();
    server.join();
  }
}
