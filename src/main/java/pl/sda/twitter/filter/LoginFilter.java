package pl.sda.twitter.filter;

import pl.sda.twitter.constance.SessionValues;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebFilter(urlPatterns = "/add-article.jsp")
public class LoginFilter implements Filter {
    private final String userValue = SessionValues.USER.getValue();
   private final String messagesValue = SessionValues.MESSAGES.getValue();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        final Object user = httpRequest.getSession().getAttribute(userValue);
        if (user == null){
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpRequest.getSession().setAttribute(messagesValue, Collections.singletonList("Zaloguj się aby kontynuować"));
             httpResponse.sendRedirect("login.jsp");
        }
        chain.doFilter(request,response);
    }
}
