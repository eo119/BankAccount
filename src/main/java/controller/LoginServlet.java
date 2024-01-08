package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;
import secureity.PasswordHasher;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        UserDAO userDAO = new UserDAO();

        User storedUser = userDAO.getUserByEmail(userEmail);

        if (storedUser != null) {
            String hashedPassword = PasswordHasher.hashPassword(userPassword);

            if (hashedPassword != null && hashedPassword.equals(storedUser.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", storedUser);
                session.setAttribute("isLogin", true);
                long userId = storedUser.getUserId(); 
                session.setAttribute("userId", userId);

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("errorMessage", "登錄失敗。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "登錄失敗。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

