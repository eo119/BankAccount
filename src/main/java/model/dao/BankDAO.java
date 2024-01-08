package model.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.Transaction;

public interface BankDAO {

	    // 存款操作
	    boolean deposit(HttpServletRequest request, double balance, String currencyType,long userId,String description);

	    // 提款操作
	    boolean withdraw(HttpServletRequest request, double balance, String currencyType,long userId);

	    //開戶
	    public boolean buildAccount(long userId, String accountNumber, String currencyType);
	        
	    //檢查資料庫內是否已經存在此accountNumber
	    boolean checkAccountExists(String accountNumber);
	    
	    //檢查帳戶內是否已經存在此幣別
	    boolean checkCurrencyExists(String currencyType,long userId);
	    
	    // 根據幣值獲取帳戶總覽
	    Account getAccountOverview(String currencyType,long userId);
	    
	    // 獲取交易明細列表
	    List<Transaction> getTransactionDetails(long userId);

	    // 關閉資源
	    void close();

		

		
	}



