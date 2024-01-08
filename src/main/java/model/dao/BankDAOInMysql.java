package model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.Transaction;

public class BankDAOInMysql implements BankDAO {
	
	
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "lzl0857123";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading MySQL JDBC Driver");
        }
    }

    
    public boolean deposit(HttpServletRequest request, double amount, String currencyType, long userId, String description) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            connection.setAutoCommit(false);

            //  更新帳戶餘額
            String updateSql = "UPDATE accounts SET balance = balance + ? WHERE currencyType = ? AND userId = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setDouble(1, amount);
                updateStatement.setString(2, currencyType);
                updateStatement.setLong(3, userId);

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected <= 0) {
                    connection.rollback();
                    return false;
                }
            }

            //  插入交易明細
		            String insertSql = "INSERT INTO transactions (userId, amount, currencyType, description, date) " +
		                    "VALUES (?, ?, ?, '存款', NOW())";
		 try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
		     insertStatement.setLong(1, userId);
		     insertStatement.setDouble(2, amount);
		     insertStatement.setString(3, currencyType);
		     
		     int rowsAffected = insertStatement.executeUpdate();
		     if (rowsAffected <= 0) {
		         connection.rollback();
		         return false;
		     }
		 }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean withdraw(HttpServletRequest request, double amount, String currencyType, long userId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            connection.setAutoCommit(false);

            double currentBalance = getCurrentBalance(connection, currencyType, userId);

            if (currentBalance >= amount) {
                // 1. 更新帳戶餘額
                String updateSql = "UPDATE accounts SET balance = balance - ? WHERE currencyType = ? AND userId = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setDouble(1, amount);
                    updateStatement.setString(2, currencyType);
                    updateStatement.setLong(3, userId);

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected <= 0) {
                        connection.rollback();
                        return false;
                    }
                }

                // 2. 插入交易明細
                String insertSql = "INSERT INTO transactions (userId, amount, currencyType, description, date) " +
                                   "VALUES (?, ?, ?, '提款', NOW())";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setLong(1, userId);
                    insertStatement.setDouble(2, amount);
                    insertStatement.setString(3, currencyType);

                    int rowsAffected = insertStatement.executeUpdate();
                    if (rowsAffected <= 0) {
                        connection.rollback();
                        return false;
                    }
                }

                connection.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private double getCurrentBalance(Connection connection, String currencyType, long userId) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE currencyType = ? AND userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, currencyType);
            statement.setLong(2, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                } else {
                    throw new SQLException("Account not found for userId: " + userId + " and currencyType: " + currencyType);
                }
            }
        }
    }


    
    
	@Override
	public boolean buildAccount(long userId, String accountNumber, String currencyType) {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
	    	String sql = "INSERT INTO accounts (userId, accountNumber, currencyType) VALUES (?, ?, ?)";
	    	try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    	    statement.setLong(1, userId);  
	    	    statement.setString(2, accountNumber);
	    	    statement.setString(3, currencyType);

	    	    int rowsAffected = statement.executeUpdate();
	    	    return rowsAffected > 0;
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	

	@Override
	public boolean checkAccountExists(String accountNumber) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
	        String sql = "SELECT COUNT(*) FROM accounts WHERE accountNumber = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, accountNumber);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int count = resultSet.getInt(1);
	                    return count > 0;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    return false;
	}
	
	@Override
	public boolean checkCurrencyExists(String currencyType, long userId) {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
	        String sql = "SELECT COUNT(*) FROM accounts WHERE currencyType = ? AND userId = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, currencyType);
	            statement.setLong(2, userId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int count = resultSet.getInt(1);
	                    return count > 0;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return false;
	}

	
	@Override
	public Account getAccountOverview(String currencyType, long userId) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

	        String sql = "SELECT * FROM accounts WHERE currencyType = ? AND userId = ? LIMIT 1";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, currencyType);
	        statement.setLong(2, userId);

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            Account userAccount = new Account();
	            userAccount.setAccountNumber(resultSet.getString("accountNumber"));
	            userAccount.setBalance(resultSet.getDouble("balance"));

	            return userAccount;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return null;
	}
	


	@Override
	public List<Transaction> getTransactionDetails( long userId) {
	    List<Transaction> transactionList = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
	        String sql = "SELECT transactionId,amount,currencyType,description,date FROM transactions WHERE userId = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setLong(1, userId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    Transaction transaction = new Transaction();
	                    transaction.setTransactionId(resultSet.getLong("transactionId"));
	                    transaction.setAmount(resultSet.getDouble("amount"));
	                    transaction.setCurrency(resultSet.getString("currencyType"));
	                    transaction.setDescription(resultSet.getString("description"));
//	                    java.sql.Timestamp timestamp = resultSet.getTimestamp("date");
//	                    if (timestamp != null) {
//	                        transaction.setDate(new java.sql.Timestamp(timestamp.getTime()));
//	                    }
	                    transaction.setDate(resultSet.getTimestamp("date"));

	                    transactionList.add(transaction);
	                    
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return transactionList;
	}






	@Override
	public void close() {
		
	}
	
}
