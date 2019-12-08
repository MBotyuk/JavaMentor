package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    UserService userService = UserService.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Optional<String> email = Optional.ofNullable(req.getParameter("email"));
        String email = req.getParameter("email");
        resp.getWriter().println(email);
//        Optional<String> password = Optional.ofNullable(req.getParameter("password"));
        String password = req.getParameter("password");
        resp.getWriter().println(password);

        boolean flag = true;
        if (!email.isEmpty() & !password.isEmpty()) {
            User user = new User(email, password);
            if (userService.isExistsThisUser(user)){

            }
        }

        resp.setContentType("text/html;charset=utf-8");
        if (flag){
            resp.getWriter().println("Error! User not create");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            resp.getWriter().println("OK! User create");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println(PageGenerator.getPageGenerator().getPage("authPage.html", null));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
