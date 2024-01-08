package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Gender;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Gender;
import model.User;

public class UserDAO {
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

    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO users (name, eglName, email, password, phone, birth, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEglName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getPhone());
                statement.setDate(6, Date.valueOf(user.getBirth()));
                statement.setString(7, user.getGender().name());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setUserId(resultSet.getLong("userId"));
                        user.setName(resultSet.getString("name"));
                        user.setEglName(resultSet.getString("eglName"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPhone(resultSet.getString("phone"));
                        user.setBirth(resultSet.getDate("birth").toLocalDate());
                        user.setGender(Gender.valueOf(resultSet.getString("gender")));
                        user.setPassword(resultSet.getString("password")); // Assuming password is stored as plain text in the database
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User login(String userEmail, String userPassword) {
        User loggedInUser = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userEmail);
                statement.setString(2, userPassword);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        loggedInUser = new User();
                        loggedInUser.setUserId(resultSet.getLong("userId"));
                        loggedInUser.setName(resultSet.getString("name"));
                        loggedInUser.setEglName(resultSet.getString("eglName"));
                        loggedInUser.setEmail(resultSet.getString("email"));
                        loggedInUser.setPhone(resultSet.getString("phone"));
                        loggedInUser.setBirth(resultSet.getDate("birth").toLocalDate());
                        loggedInUser.setGender(Gender.valueOf(resultSet.getString("gender")));
                        loggedInUser.setPassword(userPassword); // Assuming password is stored as plain text in the database
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loggedInUser;
    }
}

