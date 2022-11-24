package com.example.login_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class Loggedin_controller implements Initializable {

    @FXML
    private Button logout_btn;
    @FXML
    private Label welcome_label;
    @FXML
    private Label fav_channel_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      logout_btn.setOnAction(new EventHandler<>() {
          @Override
          public void handle(ActionEvent event) {
              DButils.changeScene(event, "Home.fxml", "Log In", null, null);
          }
      });
    }
    public void setUserInfo(String username,String favChannel)
    {
        welcome_label.setText("Welcome " + username + " !");
        fav_channel_label.setText("Your Favourite YouTube Channel is " + favChannel + " !");
    }
}
