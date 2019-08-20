package user.filter.login;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException{

    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        if (uri.contains("/js/") || uri.contains("/css/") || uri.contains("/fonts/") ||
                uri.contains("/login.jsp") || uri.contains("/loginServlet") ||
                uri.contains("checkcodeServlet")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Object access = request.getSession().getAttribute("access");
            if (access == null || !access.toString().equals("allow")) {
                response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy(){

    }
}
