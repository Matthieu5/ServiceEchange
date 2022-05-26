module application.serviceechange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires LWSL;
    requires json;
    requires com.google.gson;


    opens application to javafx.fxml;
    exports application;
}