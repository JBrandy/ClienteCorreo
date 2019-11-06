module ClienteCorreo {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.mail;


    exports Brandy.controladores;
    exports Brandy;
    exports Brandy.models;

    opens Brandy.controladores to javafx.fxml;

}