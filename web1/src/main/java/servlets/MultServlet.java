package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MultServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Optional<String> parameterRequest = Optional.ofNullable(request.getParameter("value"));
        boolean flag = true;
        if (parameterRequest.isPresent()) {
            try {
                response.getWriter().println(Integer.parseInt(parameterRequest.get()) * 2);
                flag = false;
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException nfe) { }
        }

        if(flag){
            response.getWriter().println(0);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


    }
}
