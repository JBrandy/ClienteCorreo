package Brandy.controladores;
import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainPrincipalControlador implements Initializable {

    static Scanner teclado = new Scanner(System.in);
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
    private TableView<Mensaje> tableView;

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



        public void initialize(URL url, ResourceBundle resourceBundle) {


            Logica.getInstance().cargarListaCorreos();
            tableView.setItems(Logica.getInstance().getListaCorreos());

        }
    }




