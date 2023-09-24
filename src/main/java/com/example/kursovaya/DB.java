package com.example.kursovaya;

import java.sql.*;

public class DB {
    private static Connection connection = connect();
    public static Connection connect(){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                    "std_2281_kurs", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    //Регистрация пользователя
    public static void registration(String name, String address, String phone_number, String password, String role) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, address, phone_number, password, role) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone_number);
            statement.setString(4, String.valueOf(password.hashCode()));
            statement.setString(5, role);
            System.out.println("Пользователь успешно зарегистрирован.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Авторизация пользователя
    public static void loginUser(String name, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE name = ? and password = ?");
            statement.setString(1, name);
            statement.setString(2, String.valueOf(password.hashCode()));
            ResultSet res = statement.executeQuery();
            if (res.next()){
                System.out.println(res.getString("id"));
                signInController.validation = true;
                System.out.println("Пользователь авторизован!");
            }
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