package Brandy.controladores;
import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPrincipalControlador implements Initializable {


    public void abrir_CargarMensajes(ActionEvent event) {
    }

    public void salir(ActionEvent event) {
    }

    public void enviarCorreo(ActionEvent event) {
    }

    public void borrarCorreo(ActionEvent event) {
    }

    public void reenviarCorreo(ActionEvent event) {
    }

    public void guardarFichero(ActionEvent event) {
    }

    public void quitarFiltro(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public class PleaseProvideControllerClassName implements Initializable {

        @FXML
        private MenuItem miInicioSesion;

        @FXML
        private MenuItem miAbrir;

        @FXML
        private MenuItem miSalir;

        @FXML
        private Button btNuevoCorreo;

        @FXML
        private Button btBorrar;

        @FXML
        private Button btReenviar;

        @FXML
        private Button btGuardar;

        @FXML
        private TextField tfBuscador;

        @FXML
        private Button btQuitarFiltro;

        @FXML
        private Font x1;

        @FXML
        private Color x2;

        @FXML
        private TreeView<?> treeView;

        @FXML
        private Font x3;

        @FXML
        private Color x4;

        @FXML
        void abrir_CargarMensajes(ActionEvent event) {

        }

        @FXML
        void borrarCorreo(ActionEvent event) {

        }

        @FXML
        void enviarCorreo(ActionEvent event) {

        }

        @FXML
        void guardarFichero(ActionEvent event) {

        }

        @FXML
        void quitarFiltro(ActionEvent event) {

        }

        @FXML
        void reenviarCorreo(ActionEvent event) {

        }

        @FXML
        void salir(ActionEvent event) {

        }
        @FXML
        private TableView<Mensaje> tableView;
        @FXML
        private TableColumn<Mensaje, String> remitente;

        @FXML
        private TableColumn<Mensaje, String> asunto;
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Logica.getInstance().cargarListaCorreos();
            remitente.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("Remitente"));
            asunto.setCellValueFactory(new PropertyValueFactory<Mensaje, String>("Asunto"));
            tableView.setItems(Logica.getInstance().getListaCorreos());
        }
    }



}
