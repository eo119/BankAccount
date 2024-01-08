package model;

import java.io.Serializable;

public class Account implements Serializable{
	private String accountNumber;
    private double balance;
    private Currency currency;
    private long userId;
    private User user;
    
    
	public Account() {
		super();
	}


	public Account(String accountNumber, double balance, Currency currency, long userId, User user) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.currency = currency;
		this.userId = userId;
		this.user = user;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", currency=" + currency
		+ ", userId=" + userId + ", user=" + user + "]";
	}
	

}
