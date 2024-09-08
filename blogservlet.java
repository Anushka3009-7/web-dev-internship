import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

public class BlogServlet extends HttpServlet {
    private static ArrayList<String> posts = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            request.setAttribute("posts", posts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("create-post.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (title != null && content != null) {
            posts.add("Title: " + title + "\nContent: " + content);
        }
        response.sendRedirect("BlogServlet?action=view");
    }
}
