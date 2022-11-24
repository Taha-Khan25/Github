module com.example.login_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.login_project to javafx.fxml;
    exports com.example.login_project;
}