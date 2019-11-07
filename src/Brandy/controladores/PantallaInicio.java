package Brandy.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaInicio implements Initializable {

    @FXML
    private TextField tvCorreo;

    @FXML
    private TextField tvContrasena;

    @FXML
    private Button btInicio;

    @FXML
    private Button btSalir;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) {



        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/MainPrincipal.fxml"));
            Parent root = fxmlLoader.load();
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 1000, 1000));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



}

}