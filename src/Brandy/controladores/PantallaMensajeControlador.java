package Brandy.controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class PantallaMensajeControlador {


    private Stage stage;
    @FXML
    private HTMLEditor cuerpoMensaje;

    public void enviar(ActionEvent event) {
    }

    public void cancelar(ActionEvent event) {
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
