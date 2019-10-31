module ClienteCorreo {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;

    exports Brandy.controladores;
exports Brandy;

    opens Brandy.controladores to javafx.fxml;

}