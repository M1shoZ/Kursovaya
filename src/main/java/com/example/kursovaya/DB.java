package com.example.kursovaya;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection connection = connect();
    protected static User user;
    protected static Owner owner;
    public static Connection connect(){
        try {
            if (connection == null)
                connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                        "std_2281_kurs", "12345678");
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return connection;
    }
    //Регистрация пользователя
    public static void registration(String name, String address, String phone, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Users (name, address, phone, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, String.valueOf(password.hashCode()));
            statement.executeUpdate();
            System.out.println("Пользователь успешно зарегистрирован.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Авторизация пользователя
    public static void loginUser(String name, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE name = ? and password = ?");
            statement.setString(1, name);
            statement.setString(2, String.valueOf(password.hashCode()));
            ResultSet res = statement.executeQuery();
            if (res.next()){
                System.out.println(res.getString("id"));
                signInController.validation = true;
                System.out.println("Пользователь авторизован!");
                user = getUser(Integer.parseInt(res.getString("id")));
                owner = new Owner (getOwnerId(Integer.parseInt(res.getString("id"))),
                        Integer.parseInt(res.getString("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static User getUser(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String name = res.getString("name");
                String address = res.getString("address");
                String phone = res.getString("phone");
                String password = res.getString("password");
                String role = res.getString("role");
                return  new User(id ,name, address, phone, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void updateName(String newName, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Users SET name = ? WHERE id = ?;");
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
            user.setName(newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePassword(String newPassword, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Users SET password = ? WHERE id = ?;");
            statement.setString(1, String.valueOf(newPassword.hashCode()));
            statement.setInt(2, id);
            statement.executeUpdate();
            user.setPassword(String.valueOf(newPassword.hashCode()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAddress(String newAddress, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Users SET address = ? WHERE id = ?;");
            statement.setString(1, newAddress);
            statement.setInt(2, id);
            statement.executeUpdate();
            user.setAddress(newAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePhone(String newPhone, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Users SET phone = ? WHERE id = ?;");
            statement.setString(1, newPhone);
            statement.setInt(2, id);
            statement.executeUpdate();
            user.setPhone(newPhone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getOwnerId(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Owners WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                int id =  res.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Car> getCars(int ownerId) {
        List<Car> carList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE owner_id = ?");
            statement.setInt(1, ownerId);
            ResultSet res = statement.executeQuery();
            while (res.next()){
                String licencePlate = res.getString("license_plate");
                String brand = res.getString("brand");
                String model = res.getString("model");
                String color = res.getString("color");
                Integer mileage = res.getInt("mileage");
                Car newCar = new Car(licencePlate, brand, model, color, mileage, ownerId);
                carList.add(newCar);
            }
            return carList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addCar(String licensePlate, String brand, String model, String color, String mileage, int owner_id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Cars (license_plate, brand, model, color, mileage, owner_id) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, licensePlate);
            statement.setString(2, brand);
            statement.setString(3, model);
            statement.setString(4, color);
            statement.setString(5, mileage);
            statement.setInt(6, owner_id);
            statement.executeUpdate();
            System.out.println("Автомобиль добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void signUpCar(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Service (car, car_mileage) VALUES (?, ?)");
            statement.setString(1, licensePlate);
            statement.setInt(2, getMileageByCarLicensePlate(licensePlate));
            statement.executeUpdate();
            System.out.println("Автомобиль записан на ремонт");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getMileageByCarLicensePlate(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE license_plate = ?");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                int mileage = res.getInt("mileage");
                return mileage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    //Авторизация пользователя
//    public static void loginUser(String name, String password) {
//        if (getUserId(name, password) != ""){
//            signInController.validation = true;
//            System.out.println("Пользователь авторизован!");
//            System.out.println(getRole(Integer.parseInt(getUserId(name, password))));
//        }
//    }
//    private static String getRole(int id){
//        String role = "";
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT role FROM Users WHERE id = ?");
//            statement.setInt(1, id);
//            ResultSet res = statement.executeQuery();
//            res.getString("role");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return  role;
//    }
//    private static String getUserId(String name, String password){
//        String id = "sosi";
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT * FROM Users WHERE name = ? and password = ?");
//            statement.setString(1, name);
//            statement.setString(2, String.valueOf(password.hashCode()));
//            ResultSet res = statement.executeQuery();
//            id = res.getString("id");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
}