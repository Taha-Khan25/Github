package com.example.login_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp_Controller implements Initializable {
    @FXML
    private Button signup_page_signup_btn;
    @FXML
    private Button signup_page_login_btn;
    @FXML
    private RadioButton techburner_id;
    @FXML
    private RadioButton trakinech_id;
    @FXML
    private TextField signup_username_feild;
    @FXML
    private TextField signup_password_feild;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup=new ToggleGroup();
        techburner_id.setToggleGroup(toggleGroup);
        trakinech_id.setToggleGroup(toggleGroup);
        techburner_id.setSelected(true);

        signup_page_signup_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName=((RadioButton)toggleGroup.getSelectedToggle()).getText();
                if (!signup_username_feild.getText().trim().isEmpty()){
                    DButils.signUpUser(event, signup_username_feild.getText(),signup_password_feild.getText(),toggleName);
                }
                else {
                    System.out.println("fill in info");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill in info");
                    alert.show();

                }
            }
        });

        signup_page_login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButils.changeScene(event,"Home.fxml","Log In",null,null);
            }
        });

    }
}
