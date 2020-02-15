package servlet;

import model.User;
import service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> allUsers = userService.getAllUser();
        req.setAttribute("users", allUsers);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
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
        UserServiceImpl userService = new UserServiceImpl();
        userService.delUser(userId);
        doGet(req, resp);
    }
}