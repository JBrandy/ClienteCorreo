package Brandy.controladores;


import Brandy.logica.Logica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import reloj.LogicaReloj;
import reloj.Tarea;

import java.time.LocalDate;

public class PantallaAnadirTareaControlador {

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

    @FXML
    void anadir(ActionEvent event) {

        String tareaS = tvTarea.getText();
        LocalDate fecha = date.getValue();
        int hora = Integer.parseInt(tvHora.getText());
        int minuto = Integer.parseInt(tvMinuto.getText());
        String realizado = null;
        Tarea tarea = new Tarea(tareaS, fecha, hora, minuto, null);
        LogicaReloj.getInstance().anadirTarea(tarea);



    }

    public void setStage(Stage stage) {
    }
}
