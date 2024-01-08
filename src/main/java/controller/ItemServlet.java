package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Transaction;
import model.dao.BankDAO;
import model.dao.BankDAOInMysql;

@WebServlet(value="/ItemServlet")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
        long userId = (long)session.getAttribute("userId");
        

        BankDAO bankDAO = new BankDAOInMysql(); 
        List<Transaction> transactionList = bankDAO.getTransactionDetails(userId);

        request.setAttribute("transactionList", transactionList);
       

        RequestDispatcher dispatcher = request.getRequestDispatcher("tradeItem.jsp");
        dispatcher.forward(request, response);
    }
}
