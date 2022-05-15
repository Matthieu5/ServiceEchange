module application.serviceechange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires LWSL;
    requires json;


    opens application to javafx.fxml;
    exports application;
}