package com.example.login_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.Objects;

public class DButils {
    public static void changeScene(ActionEvent event,String fxmFile,String title,
                                   String username,String favChannel)
    {
        Parent root=null;
        if(username!= null && favChannel!=null){
            try {
                FXMLLoader loader=new FXMLLoader(DButils.class.getResource(fxmFile));
                root=loader.load();
                Loggedin_controller loggedin_controller=loader.getController();
                loggedin_controller.setUserInfo(username, favChannel);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try {
                root=FXMLLoader.load((DButils.class.getResource(fxmFile)));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();;
    }

    public static void signUpUser(ActionEvent event,String username,String password,
                                  String favChannel){
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;

        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","tahakhan");
            psCheckUserExists= connection.prepareStatement("Select * from users where username= ?");
            psCheckUserExists.setString(1,username);
            resultSet=psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User Already Exist");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username Already Used");
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("INSERT INTO users (username,password,favChannel) VALUES (?, ?, ?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,favChannel);
                psInsert.executeUpdate();

                changeScene(event,"Logged-in.fxml","Welcome",username,favChannel);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet!=null)
            {
                try {
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection!=null)
            {
                try {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logIn(ActionEvent event,String username,String password)
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "tahakhan");
            preparedStatement=connection.prepareStatement("Select password,favChannel From users where username=?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst())
            {
                System.out.println("User Not found");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("wrong input");
                alert.show();
            }
            else {
                while (resultSet.next()){
                    String rtpassword=resultSet.getString("password");
                    String rtchannel=resultSet.getString("favChannel");
                    if (rtpassword.equals(password)){
                        changeScene(event,"Logged-in.fxml","Welcome",username, rtchannel);
                    }
                    else {
                        System.out.println("Password not matched");
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("wrong password");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet!=null)
            {
                try {
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null)
            {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection!=null)
            {
                try {
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }
}
