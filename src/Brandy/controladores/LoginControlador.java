package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class LoginControlador implements Initializable {

    UsuarioCorreo u ;

    @FXML
    private TextField tvCorreo;

    @FXML
    private TextField tvContrasena;

    @FXML
    private Button btInicio;

    @FXML
    private Button btSalir;
    private Stage stage;

    @FXML
    void cancelar(ActionEvent event) {

    }


    @FXML
    void iniciarSesion(ActionEvent event) throws IOException, GeneralSecurityException, MessagingException {

        String email = tvCorreo.getText();
        String contra =tvContrasena.getText();
        u =new UsuarioCorreo(email,contra);
        Logica.getInstance().anadirUsuario(u);
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tvCorreo.setText("damdijb@gmail.com");
        tvContrasena.setText("123456A@");

       /*ValidationSupport vs = new ValidationSupport();
        vs.registerValidator(tvContrasena, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tvCorreo, Validator.createEmptyValidator("el campo no puede estar vacio"));


        btInicio.disableProperty().bind(vs.invalidProperty());*/


}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}