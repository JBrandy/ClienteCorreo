package Brandy.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguracionTemaControlador implements Initializable {

private  Stage stage;

        public Stage getStage() {
                return stage;
        }


        public  void setStage(Stage stage) {
                this.stage = stage;
        }

        @FXML
        private Button btCancelar;

        @FXML
        private Button btAplicar;

        @FXML
        private ComboBox<?> cbTema;

        @FXML
        private Button btAceptar;



        @FXML
        void aceptar(ActionEvent event) {

        }

        @FXML
        void aplicar(ActionEvent event) {

        }

        @FXML
        void cancelar(ActionEvent event) {
        stage.close();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}


