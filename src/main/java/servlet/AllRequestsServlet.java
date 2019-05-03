package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import templater.PageGenerator;

public class AllRequestsServlet extends HttpServlet {

  private String login = "";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, Object> pageVariables = createPageVariablesMap(req);
    pageVariables.put("message", "");

    resp.getWriter().println(req.getParameter("key"));

    resp.setContentType("text/html;charset=utf-8");
    resp.setStatus(HttpServletResponse.SC_OK);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, Object> pageVariables = createPageVariablesMap(req);

    String message = req.getParameter("message");

    resp.setContentType("text/html;charset=utf-8");

    if (message == null || message.isEmpty()) {
      resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    } else {
      resp.setStatus(HttpServletResponse.SC_OK);
    }

    pageVariables.put("message", message == null ? "" : message);

    resp.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
  }

  private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
    Map<String, Object> pageVariables = new HashMap<>();
    pageVariables.put("method", request.getMethod());
    pageVariables.put("URL", request.getRequestURL().toString());
    pageVariables.put("pathInfo", request.getPathInfo());
    pageVariables.put("sessionId", request.getSession().getId());
    pageVariables.put("parameters", request.getParameterMap().toString());
    return pageVariables;
  }
}
