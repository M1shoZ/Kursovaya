package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class signInController {
    protected static boolean validation = false;
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginRegButton;

    @FXML
    private PasswordField passField;

    @FXML
    void initialize() {
        // Авторизация пользователя
        loginButton.setOnAction(actionEvent -> {
            signInUSer();
        });
        //Открытие окна регистрации после нажатия кнопки Зарегистрироваться
        loginRegButton.setOnAction(actionEvent -> {
            start.swapScene("regWindow.fxml");
        });
    }

    private void signInUSer() {
        validation = false; // Ставим false чтобы при выходе из аккаунта, валидация проходила заново.
        String name = loginField.getText();
        String password = passField.getText();
        if (!name.equals("") && !password.equals("")){
            DB.loginUser(name, password);
            String role = DB.user.getRole();
            // Открытие окна основного приложения после нажатия кнопки Войти при верных данных
            if (validation){
                if (role.equals("Владелец авто")){
                    System.out.println("всщщщ, это юзер");
                    start.swapScene("ownerApp.fxml");
                }else if(role.equals("Сотрудник")){
                    System.out.println("This is employee");
                    start.swapScene("staffApp.fxml");
                }else{
                    System.out.println("This is boss!");
                    start.swapScene("adminApp.fxml");
                }
            }
            else
                System.out.println("Произошла ошибка!");
        }
    }

}


