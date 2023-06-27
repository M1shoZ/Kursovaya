package com.example.kursovaya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    public static void saveUser(String username, String hashedPassword) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                    "std_2281_kurs" , "12345678" );
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password_hash) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            statement.executeUpdate();
            statement.close();
            connection.close();
            System.out.println("User saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static boolean validateUser(String username, String password) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement statement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
//            statement.setString(1, username);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String hashedPassword = resultSet.getString("password");
//                return Hasher.verifyPassword(password, hashedPassword);
//            }
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
