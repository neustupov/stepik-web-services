package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionServlet extends HttpServlet {

  private final AccountService accountService;

  public SessionServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String sessionId = req.getSession().getId();
    UserProfile profile = accountService.getUserBySessionId(sessionId);
    if (profile == null) {
      resp.setContentType("text/html;charset=utf-8");
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    } else {
      Gson gson = new Gson();
      String json = gson.toJson(profile);
      resp.setContentType("text/html;charset=utf-8");
      resp.getWriter().println(json);
      resp.setStatus(HttpServletResponse.SC_OK);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    if(login == null || password == null){
      resp.setContentType("text/html;charset=utf-8");
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    UserProfile userProfile = accountService.getUserByLogin(login);
    if(userProfile == null || userProfile.getPassword().equals(password)){
      resp.setContentType("text/html;charset=utf-8");
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    accountService.addSession(req.getSession().getId(), userProfile);
    Gson gson = new Gson();
    String json = gson.toJson(userProfile);
    resp.setContentType("text/html;charset=utf-8");
    resp.getWriter().println(json);
    resp.setStatus(HttpServletResponse.SC_OK);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String sessionId = req.getSession().getId();
    UserProfile profile = accountService.getUserBySessionId(sessionId);
    if (profile == null) {
      resp.setContentType("text/html;charset=utf-8");
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    } else {
      accountService.deleteSession(sessionId);
      resp.setContentType("text/html;charset=utf-8");
      resp.getWriter().println("Goodbye!");
      resp.setStatus(HttpServletResponse.SC_OK);
    }
  }
}
