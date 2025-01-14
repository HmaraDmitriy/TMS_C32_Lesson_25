package tom.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import tom.model.User;

public class LoginServlet extends HttpServlet {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "ADMIN");
        users.put("user", "USER");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && "qwerty".equals(password) && users.containsKey(username)) {
            String role = users.get(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", new User(username, role));
            response.getWriter().write("Login successful! Welcome, " + username + "!");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid username or password.");
        }
    }
}
