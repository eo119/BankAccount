package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.dao.BankDAO;
import model.dao.BankDAOInMysql;

@WebServlet(value = "/ViewTwServlet")
public class ViewTwServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        BankDAO bankDAO = new BankDAOInMysql();
        long userId = getUserFromSession(request);

        String currencyType = "TWD";
        Account twdAccount = bankDAO.getAccountOverview(currencyType, userId);

        request.setAttribute("twdAccount", twdAccount);

        bankDAO.close();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewtwAccount.jsp");
        dispatcher.forward(request, response);
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

