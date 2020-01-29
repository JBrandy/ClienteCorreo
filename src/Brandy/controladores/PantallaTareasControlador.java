package Brandy.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaTareasControlador {

    @FXML
    private TableColumn<?, ?> tableTareas;

    @FXML
    private Button btAnadirTareas;

    @FXML
    private Button btEliminarTareas;

    @FXML
    void anadir(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaAnadirTarea.fxml"));
            Parent root = fxmlLoader.load();
            PantallaAnadirTareaControlador pantallaAnadirTareaControlador = (PantallaAnadirTareaControlador) fxmlLoader.getController();
            pantallaAnadirTareaControlador.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    public void setStage(Stage stage) {
    }
}