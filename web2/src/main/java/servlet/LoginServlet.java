package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    UserService userService = UserService.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean flag = true;
        if (!email.isEmpty() & !password.isEmpty()) {
            User user = new User(email, password);
            flag = !userService.authUser(user);
        }

        resp.setContentType("text/html;charset=utf-8");

        test(resp);

        if (flag){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println(PageGenerator.getPageGenerator().getPage("authPage.html", null));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void test(HttpServletResponse resp) throws IOException {
        for (User user : userService.getAllUsers()){
            resp.getWriter().print(user.getId() + "\n" + user.getEmail() + "\n" + user.getPassword() + "\n");
        }

        for (User user : userService.getAllAuth()){
            resp.getWriter().print(user.getId() + "\n" + user.getEmail() + "\n" + user.getPassword() + "\n");
        }

    }

}
