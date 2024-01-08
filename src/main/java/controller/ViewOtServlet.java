package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Transaction;
import model.dao.BankDAO;
import model.dao.BankDAOInMysql;

@WebServlet(value = "/ViewOtServlet")
public class ViewOtServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankDAO bankDAO = new BankDAOInMysql();

        String currencyType = req.getParameter("currencyType");

        if (currencyType.equals("USD")) {
            currencyType = "USD";
        } else if (currencyType.equals("JPY")) {
            currencyType = "JPY";
        } else if (currencyType.equals("EUR")) {
            currencyType = "EUR";
        }

        long userId = getUserFromSession(req);
        Account otAccount = bankDAO.getAccountOverview(currencyType, userId);

        req.setAttribute("otAccount", otAccount);
        req.setAttribute("selected", currencyType);

        bankDAO.close();

        req.getRequestDispatcher("/viewotAccount.jsp").forward(req, resp);
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
