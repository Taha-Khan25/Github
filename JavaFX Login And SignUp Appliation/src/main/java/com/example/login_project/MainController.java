package com.example.login_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Label password_label;
    @FXML
    private Button home_login_btn;
    @FXML
    private Button home_signup_btn;
    @FXML
    private TextField home_username_feild;
    @FXML
    private TextField home_password_feild;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

     home_login_btn.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
             DButils.logIn(event,home_username_feild.getText(),home_password_feild.getText());
         }
     });

     home_signup_btn.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
             DButils.changeScene(event,"Sign-up.fxml","Sign Up",null,null);
         }
     });
    }
}
