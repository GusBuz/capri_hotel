package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "Authorization")
public class Authorization implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if (action == null) {
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
            return;
        }

        boolean unauthenticatedUser = session.getAttribute("user") == null;
        boolean protectedAction = !action.equals("Login");

        if (unauthenticatedUser && protectedAction) {
            req.setAttribute("triedAction", action);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/login.jsp");
            rd.forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}
