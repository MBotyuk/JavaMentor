package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private User userOld;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserService userService = new UserService();
        User user = userService.getUser(userId);
        if (user != null) {
            req.setAttribute("user", user);
            userOld = user;
        } else {
            userOld = new User().getDefaultUser();
            req.setAttribute("user", userOld);
        }
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = new User(Long.valueOf(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("secondName"), req.getParameter("email"), req.getParameter("password"));
        if (!userService.editUser(user)){
            user = userOld;
        }
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
    }
}
