package com.example.kursovaya;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    protected static Car car;
    protected static Employee employee;
    private static Connection connection = connect();
    protected static User user;
    protected static Owner owner;
    //protected static Employee employee;
    protected static Admin admin;
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
                Car newCar = new Car(licencePlate, brand, model, ownerId, color, mileage);
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
            car = getCar(licensePlate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Car getCar(String licensePlate){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE license_plate = ?");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String brand = res.getString("brand");
                String model = res.getString("model");
                String color = res.getString("color");
                int mileage = res.getInt("mileage");
                int owner_id = res.getInt("owner_id");
                return  new Car(licensePlate ,brand, model, owner_id, color, mileage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void signUpCar(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Service (car, car_mileage, car_brand, car_model, owner, car_color) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, licensePlate);
            statement.setInt(2, getMileageByCarLicensePlate(licensePlate));
            statement.setString(3, getCarBrandByLicensePlate(licensePlate));
            statement.setString(4, getCarModelByLicensePlate(licensePlate));
            statement.setString(5, getCarOwnerByLicensePlate(licensePlate));
            statement.setString(6, getCarColorByLicensePlate(licensePlate));
            statement.executeUpdate();
            System.out.println("Автомобиль записан на ремонт");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCarColorByLicensePlate(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE license_plate = ?");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String color = res.getString("color");
                return color;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static String getCarModelByLicensePlate(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE license_plate = ?");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String model = res.getString("model");
                return model;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static String getCarBrandByLicensePlate(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Cars WHERE license_plate = ?");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String brand = res.getString("brand");
                return brand;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static int getMileageByCarLicensePlate(String licensePlate) {
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
    protected static String getCarOwnerByLicensePlate(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name FROM Users WHERE (id = (SELECT user_id FROM Owners WHERE (id = (SELECT owner_id FROM Cars WHERE license_plate = ? ))))");
            statement.setString(1, licensePlate);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return res.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getAdminId(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Admin WHERE user_id = ?");
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

    public static List<Car> getAllServiceCars() { // and owners
        List<Car> serviceCarList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Service");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                String licencePlate = res.getString("car");
                String brand = res.getString("car_brand");
                String model = res.getString("car_model");
                String owner = res.getString("owner");
                int employeeId = res.getInt("employee_id");
                Car serviceCar = new Car(licencePlate, brand, model, owner, employeeId);
                serviceCarList.add(serviceCar);
            }
            return serviceCarList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Employee> getStaff() {
        List<Employee> staff = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Users, Staff WHERE Users.id = Staff.user_id");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                int employeeId = res.getInt("employee_id");
                String name = res.getString("name");
                Employee employee = new Employee(employeeId, name);
                staff.add(employee);
            }
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addEmployee(String name, String address, String phone, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Users (name, address, phone, password, role) VALUES (?, ?, ?, ?, \"Сотрудник\")");
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, String.valueOf(password.hashCode()));
            statement.executeUpdate();
            System.out.println("Сотрудник успешно зарегистрирован.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignEmployee(int employeeId, String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Service SET employee_id = ? WHERE car = ?");
            statement.setInt(1, employeeId);
            statement.setString(2, licensePlate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void startCountdown(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Service SET start_date = CURRENT_TIMESTAMP WHERE car = ?");
            statement.setString(1, licensePlate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void stopCountdown(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Service SET end_time = CURRENT_TIMESTAMP WHERE car = ?");
            statement.setString(1, licensePlate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void calculateTime(String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Service SET worktime = (TIMEDIFF(end_time, start_date)) WHERE car = ?");
            statement.setString(1, licensePlate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getEmployeeIdByUserId(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Staff WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                int id =  res.getInt("employee_id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
//    private static Employee getEmployee(int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT * FROM Staff WHERE user_id = ?");
//            statement.setInt(1, id);
//            ResultSet res = statement.executeQuery();
//            if (res.next()){
//                int employeeId = res.getInt("id");
//                return new Employee(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static List<Car> getEmployeeServiceCars(int employeeId) {
//        List<Car> CarList = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT * FROM Service WHERE employee_id = ?");
//            statement.setInt(1, employeeId);
//            ResultSet res = statement.executeQuery();
//            while (res.next()){
//                String licencePlate = res.getString("car");
//                String brand = res.getString("car_brand");
//                String model = res.getString("car_model");
//                String owner = res.getString("owner");
//                String color = res.getString("car_color");
//                int mileage = res.getInt("car_mileage");
//                Car serviceCar = new Car(licencePlate, brand, model, owner, color, mileage);
//                CarList.add(serviceCar);
//            }
//            return CarList;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Car getEmployeeServiceCars(int employeeId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Service WHERE employee_id = ?");
            statement.setInt(1, employeeId);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                String licencePlate = res.getString("car");
                String brand = res.getString("car_brand");
                String model = res.getString("car_model");
                String owner = res.getString("owner");
                String color = res.getString("car_color");
                int mileage = res.getInt("car_mileage");
                String part = res.getString("part");
                car = new Car(licencePlate, brand, model, owner, color, mileage, part);
                return car;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Part> getMatchingParts(String cellData) {
        List<Part> parts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Parts WHERE car_model = ?");
            statement.setString(1, cellData);
            ResultSet res = statement.executeQuery();
            while (res.next()){
                int serialNumber = res.getInt("serial_number");
                String partCategory = res.getString("category");
                Part part = new Part(serialNumber, partCategory);
                parts.add(part);
            }
            return parts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Service> getAllService() {
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Service, Cars WHERE Service.car = Cars.license_plate");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                String serviceId = res.getString("id");
                String car = res.getString("car");
                String brand = res.getString("brand");
                String model = res.getString("model");
                String color = res.getString("color");
                String mileage = res.getString("mileage");
                String employeeId = res.getString("employee_id");
                String part = res.getString("part");
                String startTime = res.getString("start_date");
                String endTime = res.getString("end_time");
                String worktime = res.getString("worktime");
                Service s = new Service(serviceId, car, brand, model, color, mileage,
                        employeeId, part, startTime, endTime, worktime);
                services.add(s);
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setUpPart(String serialNumder, String licensePlate) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Service SET part = ? WHERE car = ?;");
            statement.setString(1, serialNumder);
            statement.setString(2, licensePlate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}