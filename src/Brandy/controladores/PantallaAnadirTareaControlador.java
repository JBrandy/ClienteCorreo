package Brandy.controladores;


import Brandy.logica.Logica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import reloj.MetodosTareas;
import reloj.Reloj;
import reloj.Tarea;

import java.time.LocalDate;

public class PantallaAnadirTareaControlador {

    private Stage stage  ;

    @FXML
    private DatePicker date;

    @FXML
    private TextField tvHora;

    @FXML
    private TextField tvMinuto;

    @FXML
    private Button btAnadir;

    @FXML
    private TextField tvTarea;

    private Reloj reloj;

    @FXML
    private Button btSalir;

    @FXML
    void salir(ActionEvent event) {
        stage.close();
    }
    @FXML
    void anadir(ActionEvent event) {

        String tareaS = tvTarea.getText();
        LocalDate fecha = date.getValue();
        int hora = Integer.parseInt(tvHora.getText());
        int minuto = Integer.parseInt(tvMinuto.getText());
        String realizado = null;
        Tarea tarea = new Tarea(tareaS, fecha, hora, minuto, null);
        MetodosTareas.getInstance().anadirTarea(tarea);

        //reloj.anadirTarea(tarea);
        Logica.getInstance().getListaTareas().add(tarea);
        //MetodosTareas.getInstance().cargarTareas(Logica.getInstance().getListaTareas());
        if(tarea!=null){
        Alert alert_tarea = new Alert(Alert.AlertType.INFORMATION);
        alert_tarea.setTitle("Información de Tareas");
        alert_tarea.setContentText("La Tarea "+ tareaS+ " ha sido añadida correctamente");
        alert_tarea.showAndWait();
        }


    }
    public Stage getStage() {
        return stage;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void getReloj(Reloj reloj) {
        this.reloj=reloj;
    }

}
