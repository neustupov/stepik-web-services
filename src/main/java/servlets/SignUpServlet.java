package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {

  private final AccountService accountService;

  public SignUpServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
      try {
        accountService.addNewUser(new UserProfile(login, password, null));
      } catch (SQLException | DBException e) {
        e.printStackTrace();
      }
    }
  }
}
