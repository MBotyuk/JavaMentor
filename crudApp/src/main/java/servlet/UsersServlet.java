package servlet;

import model.User;
import service.UserService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> allUsers = userService.getAllUser();

        if (allUsers != null) {
            req.setAttribute("users", allUsers);
        } else {
            req.setAttribute("users", "БД пуста!");
        }

        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");

        if (button != null) {

            if (button.equals("Delete")) {
                doDelete(req, resp);
                return;
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserService userService = new UserService();
        userService.delUser(userId);
        doGet(req, resp);
    }
}