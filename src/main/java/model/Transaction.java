package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private long transactionId;
    private double amount;
    private String description;
    private String currency;
    private long userId;
    private Account account;
	private Timestamp date;

    public Transaction() {
    }
    
    

    public Transaction(long transactionId, double amount, String description,  String currency,long userId,
			Account account,Timestamp date) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.description = description;
		this.currency = currency;
		this.userId=userId;
		this.account = account;
		this.date=date;
	}



	public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}



	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
    
    

    public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", description=" + description
				+ ", currency=" + currency + ", userId=" + userId + ", account=" + account + ", date=" + date + "]";
	}
	
	



	

}
