package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User(req.getParameter("firstName"), req.getParameter("secondName"), req.getParameter("email"), req.getParameter("password"), User.ROLE.valueOf(req.getParameter("role")));
        userService.addUser(user);
        getServletContext().getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
    }
}