package Brandy.controladores;


import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reloj.LogicaReloj;
import reloj.Tarea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaTareasControlador implements Initializable {

    @FXML
    private TableView<Tarea> tableView;

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
            stage.setScene(new Scene(root, 275, 350));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(FXCollections.observableArrayList(LogicaReloj.getInstance().getListaTareas()));
    }

    @FXML
    void eliminar(ActionEvent event) {
        LogicaReloj.getInstance().borrarTarea(tableView.getSelectionModel().getSelectedItem());
        tableView.setItems(FXCollections.observableArrayList(LogicaReloj.getInstance().getListaTareas()));
    }

    public void setStage(Stage stage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(FXCollections.observableArrayList(LogicaReloj.getInstance().getListaTareas()));

    }
}