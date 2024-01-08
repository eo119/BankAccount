package controller;

import model.dao.BankDAO;  // 假設你有 BankDAO 這個類別
import model.dao.BankDAOInMysql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/BuildAccountServlet")
public class BuildAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountNumber = request.getParameter("accountNumber");
        String currencyType = request.getParameter("currencyType");

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        BankDAO bankDAO = new BankDAOInMysql();
        boolean accountExists = bankDAO.checkAccountExists(accountNumber);
        

        if (accountExists) {
        //如果帳戶已存在
            response.getWriter().println("faile");
        } else {
            // 檢查幣別是否存在
            boolean currencyExists = bankDAO.checkCurrencyExists(currencyType, userId);

            if (currencyExists) {
               
                response.getWriter().println("fail");
            } else {
                
                boolean accountCreated = bankDAO.buildAccount(userId, accountNumber, currencyType);

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }
    }
}

