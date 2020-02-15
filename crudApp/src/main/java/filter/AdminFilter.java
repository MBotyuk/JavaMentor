package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        User user;

        if (nonNull(session)) {
            if (nonNull(user = (User) session.getAttribute("user"))) {
                if (user.getRole() == User.ROLE.ADMIN) {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }
            ;
        }
        res.sendRedirect(req.getServletContext().getContextPath() + "/");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}