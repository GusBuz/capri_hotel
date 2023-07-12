package servlet;

import java.io.*;
import java.lang.reflect.Constructor;

import action.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

//@WebServlet(urlPatterns = "/index")
public class Controller extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String paramAction = req.getParameter("action");
        String className = "action." + paramAction;

        if (paramAction == null) {
            res.sendRedirect("index.jsp");
            return;
        }

        String redirectionString;

        try {
            Class<?> genericClass = Class.forName(className);
            Constructor<?> genericConstructor = genericClass.getDeclaredConstructor();
            Action action = (Action) genericConstructor.newInstance();

            redirectionString = action.execute(req, res);
            String[] redirection = redirectionString.split(":");

            if (redirection[0].equals("forward")) {
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/" + redirection[1]);
                rd.forward(req, res);
            } else if (redirection[0].equals("redirect")) {
                res.sendRedirect(redirection[1]);
            }

        } catch (Exception e) {
            String exceptionMsg = e.toString();
            req.setAttribute("exceptionMsg", exceptionMsg);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/exception.jsp");
            rd.forward(req, res);
        }
    }
}