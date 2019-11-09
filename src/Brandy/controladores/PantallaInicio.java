package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.logica.UsuarioCorreo;
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

    UsuarioCorreo u ;

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

        // cbDivision.getItems().addAll(Division.values()); Esta seria la manera correcta
        String email = tvCorreo.getText();
        String contra =tvContrasena.getText();
        u =new UsuarioCorreo(email,contra);
        Logica.getInstance().anadirUsuario(u);
        Logica.getInstance().cargarListaCorreos(u);



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       /*ValidationSupport vs = new ValidationSupport();
        vs.registerValidator(tvContrasena, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tvCorreo, Validator.createEmptyValidator("el campo no puede estar vacio"));


        btInicio.disableProperty().bind(vs.invalidProperty());*/

}

}