package action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

public class Login implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String triedAction = req.getParameter("triedAction");

        if (username != null && password != null) {
            if (username.equals("admin") && password.equals("admin")) {
                if (triedAction == null || triedAction.isEmpty() || triedAction.equals("LoginForm")) {
                    triedAction = "Menu";
                }
                User user = new User(username, password);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                System.out.println("teste" + triedAction);
                return "redirect:index?action=" + triedAction;
            }
        }
        return "redirect:index?action=LoginForm";
    }
}
