package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamenControlador implements Initializable {

    private Stage stage;

    @FXML
    private ComboBox<Mensaje> cb;

    @FXML
    private Button bt;

    @FXML
    void ver(ActionEvent event) {
        Mensaje a = cb.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Asunto");
        alert.setHeaderText(a.getAsunto());
        alert.show();


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cb.setItems(Logica.getInstance().cargarListaCorreosExamen());

    }
}