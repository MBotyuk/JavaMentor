package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {

    private User userOld;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getUser(userId);

        if (user != null) {
            req.setAttribute("user", user);
            userOld = user;
        } else {
            userOld = new User("","","","", User.ROLE.USER);
            req.setAttribute("user", userOld);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User(Long.valueOf(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("secondName"), req.getParameter("email"), req.getParameter("password"), User.ROLE.valueOf(req.getParameter("role").toUpperCase()));

        if (user.getEmail().equals(userOld.getEmail())){
            userService.editUser(user);
        } else {
            if (userService.isUser(user) == 0) {
                userService.editUser(user);
            } else {
                user = userOld;
            }
        }
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }
}