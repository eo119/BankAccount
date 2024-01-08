package controller;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gender;
import model.User;
import model.dao.UserDAO;
import secureity.PasswordHasher;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String newName = request.getParameter("name");
        String newEglName = request.getParameter("eglName");
        String newEmail = request.getParameter("email");
        String newPassword = request.getParameter("password");
        String newPhone = request.getParameter("phone");
        String newLocalDateStr = request.getParameter("birth");
        String newGenderStr = request.getParameter("gender");
        
        
        String hashedPassword = PasswordHasher.hashPassword(newPassword);

     
        LocalDate newLocalDate = LocalDate.parse(newLocalDateStr);
        
       
        Gender newGender = null;
        try {
            newGender = Gender.valueOf(newGenderStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); 
        }

       
        User newUser = new User();
        newUser.setName(newName);
        newUser.setEglName(newEglName);
        newUser.setEmail(newEmail);
        newUser.setPassword(hashedPassword);
        newUser.setPhone(newPhone);
        newUser.setBirth(newLocalDate);
        newUser.setGender(newGender);
        
       
        UserDAO userDAO = new UserDAO();

     
        userDAO.createUser(newUser);
        
       
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}

