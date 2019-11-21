package Brandy.controladores;


import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaMensajeControlador implements Initializable {


    private Stage stage;
    @FXML
    private HTMLEditor cuerpoMensaje;

    @FXML
    private ComboBox<String> cbDe;

    public void enviar(ActionEvent event) {
    }

    public void cancelar(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*for (int i =0; 0<=Logica.getInstance().getListaUsuarios().size();i++){
            cbDe.getItems().add(Logica.getInstance().getListaUsuarios().get(i).getEmail());
        }*/

    }

    public class PleaseProvideControllerClassName {

        @FXML
        private TextField tfPara;

        @FXML
        private TextField tfCc;

        @FXML
        private TextField tfAsunto;

        @FXML
        private Button btEnviar;

        @FXML
        private TextField tfDe;

        @FXML
        private Button btCancelar;

        @FXML
        void cancelar(ActionEvent event) {

        }

        @FXML
        void enviar(ActionEvent event) {

        }

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
