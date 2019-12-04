package Brandy.controladores;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        private ComboBox<String> cbTema;

        @FXML
        private Button btAceptar;



        @FXML
        void aceptar(ActionEvent event) {
        stage.close();
        }

        @FXML
        void aplicar(ActionEvent event) {
            if (cbTema.getSelectionModel().getSelectedItem()!=null)
              Application.setUserAgentStylesheet(cbTema.getSelectionModel().getSelectedItem());


        }

        @FXML
        void cancelar(ActionEvent event) {
                Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        stage.close();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            cbTema.getItems().addAll(Application.STYLESHEET_CASPIAN,Application.STYLESHEET_MODENA);
            cbTema.getSelectionModel().select(Application.getUserAgentStylesheet()); //Este método nos devuelve el tema actual

        }


        /*
          scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());

          estilos.css
          .button{
    -fx-text-fill: rgb(49, 89, 23);
    -fx-border-color: rgb(49, 89, 23);
    -fx-border-radius: 5;
    -fx-background-color: red;
    -fx-padding: 3 6 6 6;
}
         */
}


