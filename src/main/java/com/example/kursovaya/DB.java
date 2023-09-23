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

    //Регистрация пользователя
    public static void registerUser(String username, String hashedPassword, String role) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                    "std_2281_kurs", "12345678");
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Users (username, password_hash, role) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.setString(3, role);

            statement.executeUpdate();
            statement.close();
            connection.close();
            System.out.println("Пользователь успешно зарегистрирован.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Получение данных из БД
    public ResultSet getUser(String loginText, String passText){
        ResultSet result = null;
        String select = "SELECT * FROM Users WHERE " + loginText + "=? AND " + passText + "=?";
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                    "std_2281_kurs", "12345678");
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setString(1, loginText);
            statement.setString(2, passText);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}