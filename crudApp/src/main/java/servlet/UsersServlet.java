package servlet;

import model.User;
import service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
//        UserService userService = new UserService();
//        userService.clearTable();
    }

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
            if (button.equals("Edit")) {
                doPut(req, resp);
                return;
            }
            if (button.equals("Delete")) {
                doDelete(req, resp);
                return;
            }
        } else {
            UserService userService = new UserService();
            userService.addUser(new User(req.getParameter("firstName"), req.getParameter("secondName"), req.getParameter("email"), req.getParameter("password")));
            doGet(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserService userService = new UserService();
        userService.delUser(userId);
        doGet(req, resp);
    }
}