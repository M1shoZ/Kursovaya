package com.example.kursovaya;
import java.sql.*;

public class main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2281_kurs",
                    "std_2281_kurs" , "12345678" );
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Owners";
            ResultSet result = statement.executeQuery(query);

            // Обработка результатов запроса
            while(result.next()){
                int owner_id = result.getInt("owner_id" );
                String name = result.getString("name" );
                int identification_number = result.getInt("identification_number");
                String address = result.getString("address" );
                String phone_number = result.getString("phone_number" );

                System.out.println("Owner: " + "id = " + owner_id + ", name = " + name +
                        ", identification_number = " + identification_number + ", address = " + address +
                        "phone_number = " + phone_number + ".");

            }
            result.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
