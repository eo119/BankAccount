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

        // 檢查使用者是否已經登入的邏輯
        boolean userLoggedIn = isUserLoggedIn(httpRequest);
        
        if(userLoggedIn || isPublicResource(httpRequest)) {
        	chain.doFilter(request, response);
        	return;
        }

        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        
    }

    private boolean isPublicResource(HttpServletRequest request) {
        // 在這裡檢查是否訪問的是公共資源，例如靜態資源或登入頁面
        String requestURI = request.getRequestURI();

        // 排除首頁
        if (requestURI.equals(request.getContextPath() + "/index.jsp") ||
            requestURI.equals(request.getContextPath() + "/CreateAccountServlet")||
            requestURI.equals(request.getContextPath() + "/LoginServlet")) {
            return true;
        }
        
        if (requestURI.contains("/img/")) {
            return true;
        }

        // 其他公共資源的檢查，例如登入頁面或靜態資源
        return requestURI.contains("/login.jsp") || requestURI.contains("/public/") || requestURI.contains("/createAccount.jsp");
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 如果 session 不存在，不要創建新的
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