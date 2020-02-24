module ClienteCorreo {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.mail;
    requires java.sql;
    requires javafx.web;
    requires commons.email;
    requires Reloj;
    requires jasperreports;
    requires org.jsoup;
    requires jdk.jsobject;

    exports Brandy.controladores;
    exports Brandy;
    exports Brandy.models;
    exports Brandy.logica;



    opens Brandy.controladores to javafx.fxml;

}