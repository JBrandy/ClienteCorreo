package Brandy.controladores;


import Brandy.logica.Logica;
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
import reloj.MetodosTareas;
import reloj.Reloj;
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

    private Reloj reloj;

    @FXML
    void anadir(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaAnadirTarea.fxml"));
            Parent root = fxmlLoader.load();
            PantallaAnadirTareaControlador pantallaAnadirTareaControlador = (PantallaAnadirTareaControlador) fxmlLoader.getController();
            pantallaAnadirTareaControlador.getReloj(reloj);

            pantallaAnadirTareaControlador.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 275, 350));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(FXCollections.observableArrayList(Logica.getInstance().getListaTareas()));
    }

    @FXML
    void eliminar(ActionEvent event) {
        MetodosTareas.getInstance().borrarTarea(tableView.getSelectionModel().getSelectedItem());
        Logica.getInstance().getListaTareas().remove(tableView.getSelectionModel().getSelectedItem());
        //MetodosTareas.getInstance().cargarTareas(Logica.getInstance().getListaTareas());

        tableView.setItems(FXCollections.observableArrayList(Logica.getInstance().getListaTareas()));
    }

    public void setStage(Stage stage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(FXCollections.observableArrayList(Logica.getInstance().getListaTareas()));

    }


    public void getReloj(Reloj reloj) {
        this.reloj=reloj;
    }
}