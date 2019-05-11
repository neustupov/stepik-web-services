package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class App {

  public static void main(String[] args) throws Exception {

    AccountService accountService = new AccountService();
    accountService.addNewUser(new UserProfile("admin"));
    accountService.addNewUser(new UserProfile("test"));

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
    context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

    ResourceHandler resourceHandler = new ResourceHandler();
    resourceHandler.setResourceBase("public_html");

    HandlerList handlerList = new HandlerList();
    handlerList.setHandlers(new Handler[]{resourceHandler, context});

    Server server = new Server(8080);
    server.setHandler(handlerList);

    server.start();
    java.util.logging.Logger.getGlobal().info("Server started");
    server.join();

    /*DBService dbService = new DBService();
    dbService.printConnectInfo();
    try {
      long userId = dbService.addUser("tully");
      System.out.println("Added user id: " + userId);

      UsersDataSet dataSet = dbService.getUser(userId);
      System.out.println("User data set: " + dataSet);

    } catch (DBException e) {
      e.printStackTrace();
    }*/
  }
}
