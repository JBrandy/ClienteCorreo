package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import Brandy.models.UsuarioCorreo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TreeView<String> treeview;

    @FXML
    void caragarListaMensajes(MouseEvent event) {

    }

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
        try {
            treeview.setRoot(Logica.getInstance().cargaCarpetas(Logica.getInstance().getListaUsuarios().get(0)));


            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mensaje>() {
                @Override
                public void changed(ObservableValue<? extends Mensaje> observable, Mensaje oldValue, Mensaje newValue) {
                    try {
                        webView.getEngine().loadContent(newValue.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


    }

   /* @FXML
    void seleccion(MouseEvent event) {
        WebEngine webEngine = webView.getEngine();
        try {
            //int indice = tableView.getSelectionModel().getSelectedIndex();
            webView.getEngine().loadContent(tableView.getSelectionModel().getSelectedItem().getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    private void anadirUsuario() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/Login.fxml"));
            Parent root = fxmlLoader.load();
            LoginController pantallaInicio = (LoginController) fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 850, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(Logica.getInstance().getListaCorreos());

    }
}




