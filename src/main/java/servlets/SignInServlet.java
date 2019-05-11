package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet {

  private final AccountService accountService;

  public SignInServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    UserProfile userProfile = accountService.getUserByLogin(login);
    if (userProfile != null) {
      if (userProfile.getPassword() != null && userProfile.getPassword().equals(password)) {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Authorized: " + login);
        resp.setStatus(HttpServletResponse.SC_OK);
      } else {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Unauthorized");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    } else {
      resp.setContentType("text/html;charset=utf-8");
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      resp.getWriter().println("Unauthorized");
    }
  }
}
