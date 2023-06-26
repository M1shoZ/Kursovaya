module com.example.kursovaya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.kursovaya to javafx.fxml;
    exports com.example.kursovaya;
}