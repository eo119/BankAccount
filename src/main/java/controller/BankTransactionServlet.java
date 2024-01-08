package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BankDAOInMysql;

@WebServlet(value="/BankTransactionServlet")
public class BankTransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String transactionType = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String currencyType = request.getParameter("currencyType");

        long userId = getUserFromSession(request);

        BankDAOInMysql bankDAO = new BankDAOInMysql();

        boolean transactionResult = false;
        if ("deposit".equals(transactionType)) {
            transactionResult = bankDAO.deposit(request, amount, currencyType, userId, transactionType);
        } else if ("withdraw".equals(transactionType)) {
            transactionResult = bankDAO.withdraw(request, amount, currencyType, userId);
        }

        if (transactionResult) {
            // 交易成功
            response.sendRedirect("success.jsp");
        } else {
            // 交易失败
            response.sendRedirect("failure.jsp");
        }
    }

    private long getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object userIdObj = session.getAttribute("userId");
            if (userIdObj instanceof Long) {
                return (Long) userIdObj;
            }
        }
        return 0; 
    }
}