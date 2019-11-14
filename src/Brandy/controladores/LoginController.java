package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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
    void iniciarSesion(ActionEvent event) throws IOException {

        // cbDivision.getItems().addAll(Division.values()); Esta seria la manera correcta
        String email = tvCorreo.getText();
        String contra =tvContrasena.getText();
        u =new UsuarioCorreo(email,contra);
        Logica.getInstance().anadirUsuario(u);
        Logica.getInstance().cargarListaCorreos(u);
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       /*ValidationSupport vs = new ValidationSupport();
        vs.registerValidator(tvContrasena, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tvCorreo, Validator.createEmptyValidator("el campo no puede estar vacio"));


        btInicio.disableProperty().bind(vs.invalidProperty());*/


}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}