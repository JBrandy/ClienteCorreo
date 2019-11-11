package Brandy.controladores;
import Brandy.logica.Logica;
import Brandy.logica.UsuarioCorreo;
import Brandy.models.Mensaje;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;

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
    private WebView webView;
    @FXML
    private Font x3;

    @FXML
    private Color x4;




    @FXML
    private Label labelRemitente;
    @FXML
    void abrirVentana(ActionEvent event) {
        anadirUsuario();

    }
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
           anadirUsuario();





          // webView.getEngine().load("https://as.com/");
            //webView.getEngine().load();




        }

    @FXML
    void seleccion(MouseEvent event) {
        WebEngine webEngine = webView.getEngine();
        try {
            int indice = tableView.getSelectionModel().getSelectedIndex();
            webView.getEngine().loadContent(Logica.getInstance().getMessageContent(Logica.getInstance().getListaCorreos().get(indice)));
        } catch (MessagingException  e) {
            e.printStackTrace();
        }

    }

    private void anadirUsuario() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/PantallaInicio.fxml"));
            Parent root = fxmlLoader.load();
            PantallaInicio pantallaInicio = (PantallaInicio)fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 850, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(Logica.getInstance().getListaCorreos());
        //stage.close();

        // metter los botones en un toolbar
    }
}




