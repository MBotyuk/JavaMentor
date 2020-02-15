package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.*;
import static model.User.ROLE.UNKNOWN;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private final User USER_ADMIN = new User("admin", "admin", "admin@gmail.com", "admin", User.ROLE.ADMIN);
    User user;
    User.ROLE role;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        UserServiceImpl userService = new UserServiceImpl();

        if (userService.getNumberOfUserInTable() > 0) {
            if (userService.isUser(USER_ADMIN) == 0) {
                userService.addUser(USER_ADMIN);
            }
        } else {
            userService.addUser(USER_ADMIN);
        }

        if (nonNull(user = (User) session.getAttribute("user"))) {
            role = user.getRole();
        } else {
            user = userService.getUserByEmailPassword(request.getParameter("email"), request.getParameter("password"));
            role = user != null ? user.getRole() : UNKNOWN;
            session.setAttribute("user", user);
        }

        if (nonNull(role)) {

            if (role == User.ROLE.ADMIN) {
                response.sendRedirect(super.getServletContext().getContextPath() + "/admin");
            } else {
                response.sendRedirect(super.getServletContext().getContextPath() + "/user");
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}