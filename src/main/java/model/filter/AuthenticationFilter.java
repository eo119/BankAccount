package model.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

       
        boolean userLoggedIn = isUserLoggedIn(httpRequest);
        
        if(userLoggedIn || isPublicResource(httpRequest)) {
        	chain.doFilter(request, response);
        	return;
        }

        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        
    }

    private boolean isPublicResource(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        
        if (requestURI.equals(request.getContextPath() + "/index.jsp") ||
            requestURI.equals(request.getContextPath() + "/CreateAccountServlet")||
            requestURI.equals(request.getContextPath() + "/LoginServlet")) {
            return true;
        }
        
        if (requestURI.contains("/img/")) {
            return true;
        }

       
        return requestURI.contains("/login.jsp") || requestURI.contains("/public/") || requestURI.contains("/createAccount.jsp");
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false); 
        return session != null && session.getAttribute("isLogin") != null && (boolean) session.getAttribute("isLogin");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter 初始化
    }

    @Override
    public void destroy() {
        // Filter 銷毀
    }
}