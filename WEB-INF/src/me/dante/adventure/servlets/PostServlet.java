package me.dante.adventure.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import me.dante.adventure.models.PostDBAccess;
import me.dante.adventure.models.DBAction;
import me.dante.adventure.models.Post;

public class PostServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws IOException, ServletException {
      try {
        DBAction postDAO = new PostDBAccess();
        List<Post> lists = postDAO.getData();
        request.setAttribute("data", lists);
        request.getRequestDispatcher("/post.jsp").forward(request, response);
      } catch(Exception e) {
        PrintWriter out = response.getWriter();
        out.println(e);
        out.close();
      }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws IOException, ServletException {
      try{
        DBAction postDAO = new PostDBAccess();
        Map<String, String> params = new HashMap<String, String>();
        params.put("author", request.getParameter("author"));
        params.put("body", request.getParameter("body"));
        postDAO.updateData(params);
        response.sendRedirect("/posts");
      } catch(Exception e) {
        PrintWriter out = response.getWriter();
        out.println(e );
        out.close();
      }
  }

}
