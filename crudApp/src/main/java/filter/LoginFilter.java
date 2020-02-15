package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;


@WebFilter(filterName = "LoginFilter", urlPatterns = {"/"})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        User user;

        if (nonNull(session)) {
            if (nonNull(user = (User) session.getAttribute("user"))) {
                if (user.getRole() == User.ROLE.ADMIN) {
                    res.sendRedirect(req.getServletContext().getContextPath() + "/admin");
                } else {
                    res.sendRedirect(req.getServletContext().getContextPath() + "/user");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}