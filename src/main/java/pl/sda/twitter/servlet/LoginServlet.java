package pl.sda.twitter.servlet;

import pl.sda.twitter.constance.SessionValues;
import pl.sda.twitter.exceptions.IncorrectLoginOrPasswordException;
import pl.sda.twitter.persistance.entities.TbUser;
import pl.sda.twitter.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    private final String user = SessionValues.USER.getValue();
    private final String messages = SessionValues.MESSAGES.getValue();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");


        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        HttpSession session = req.getSession();
        try {
            TbUser TbUser = userService.getUser(login, password);

            session.setAttribute(user, TbUser);
            resp.sendRedirect("index.jsp");
        } catch (IncorrectLoginOrPasswordException e) {
            session.setAttribute(messages, Collections.singletonList("Nie poprawne logowanie. Sprobuj ponownie"));
            resp.sendRedirect("login.jsp");
        }
//        final PrintWriter writer = resp.getWriter();
//        final String userName = "admin";
//
//        if (login.equals(userName) && password.equals("password")) {
//            final HttpSession session = req.getSession();
//            session.setAttribute("user", userName);
//            resp.sendRedirect(req.getContextPath() + "/");
//
//        } else {
//            writer.write("Niepoprawne dane logowania");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
