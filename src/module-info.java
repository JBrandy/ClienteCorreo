module ClienteCorreo {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.mail;
   // requires validation.api;
    requires javafx.web;
    requires commons.email;



    exports Brandy.controladores;
    exports Brandy;
    exports Brandy.models;
    exports Brandy.logica;



    opens Brandy.controladores to javafx.fxml;

}