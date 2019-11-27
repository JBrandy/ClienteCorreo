package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.logica.ServiciosEmail;
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
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.Flags;
import javax.mail.Folder;
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
    public TreeView<String> treeview;

    @FXML
    private MenuItem btCongif;

    private TreeItemMail TreeItem;

    @FXML
    void configuarCuentas(ActionEvent event) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/configurarCorreo.fxml"));
            Parent root = fxmlLoader.load();
            ConfigurarCorreoControlador pantallaConfigCorreo = (ConfigurarCorreoControlador) fxmlLoader.getController();
            pantallaConfigCorreo.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();

            }

    @FXML
    void caragarListaMensajes(MouseEvent event) {

    }
    @FXML
    void filtrarTable(ActionEvent event) {

    }
    @FXML
    void abrirVentana(ActionEvent event) {
        anadirUsuario();

    }

    @FXML
    void abrir_CargarMensajes(ActionEvent event) {

    }

    @FXML
    void borrarCorreo(ActionEvent event) throws MessagingException, GeneralSecurityException {

        Mensaje m = tableView.getSelectionModel().getSelectedItem();
        TreeItemMail email_tree = (TreeItemMail)treeview.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert_null = new Alert(Alert.AlertType.WARNING);
            alert_null.setTitle("Alerta");
            alert_null.setContentText("No hay mensajes seleccionados");
            alert_null.showAndWait();
        } else {
           Logica.getInstance().borrar_email(m,email_tree);


        }
    }



        /*Mensaje m;
        int indice = tableView.getSelectionModel().getSelectedIndex();
        m = tableView.getItems().get(indice);
        m.borrarMensaje();
        //folder.close(true);
        Folder folder = ((TreeItemMail)treeview.getSelectionModel().getSelectedItem()).getFolder();
        if (folder.isOpen()){
            folder.close();
        }*/

        // el metodo anterior es desues de hacer
        //copumessage(m,folder.trush)

        /*

logica..getstore.getfolder(ruta)

        if (m esta en trush)
         Mensaje m;
        int indice = tableView.getSelectionModel().getSelectedIndex();
        m = tableView.getItems().get(indice);
        m.borrarMensaje();
        //folder.close(true);
        Folder folder = ((TreeItemMail)treeview.getSelectionModel().getSelectedItem()).getFolder();
        if (folder.isOpen()){
            folder.close();
        }else
        copumessage(m,folder.trush)
         */




    @FXML
    void enviarCorreo(ActionEvent event) throws GeneralSecurityException, MessagingException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaMensaje.fxml"));
            Parent root = fxmlLoader.load();
            PantallaMensajeControlador pantallaInicio = (PantallaMensajeControlador) fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Nuevo Correo");
            stage.setScene(new Scene(root, 850, 600));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();

    }

    @FXML
    void guardarFichero(ActionEvent event) {

    }

    @FXML
    void quitarFiltro(ActionEvent event) {

    }

    @FXML
    void reenviarCorreo(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaMensaje.fxml"));
            Parent root = fxmlLoader.load();
            PantallaMensajeControlador pantallaInicio = (PantallaMensajeControlador) fxmlLoader.getController();
            Mensaje msg = tableView.getSelectionModel().getSelectedItem();
            pantallaInicio.reenviar(msg);
            pantallaInicio.setStage(stage);
            stage.setTitle("Nuevo Correo");
            stage.setScene(new Scene(root, 850, 600));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }

    @FXML
    void salir(ActionEvent event) {

    }

    private void anadirUsuario() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/login.fxml"));
            Parent root = fxmlLoader.load();
            LoginControlador pantallaInicio = (LoginControlador) fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 850, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(Logica.getInstance().getListaCorreos());

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anadirUsuario();

        try {

            treeview.setRoot(Logica.getInstance().actualizarTree());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //https://medium.com/@keeptoo/javafx-java-modern-ui-design-starter-pack-aab1c331fd3c
        /* Para verdonde esta el filderdel treeview mire en el debug el selectitem y abriendo vi
         un folder que dentro tenia un fullname que es la ruta que necesito parapasarlo
         Para ver los correos del treeView en la tabla*/

        treeview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> stringTreeItem, TreeItem<String> t1) {
                System.out.println(treeview.getSelectionModel().getSelectedItem().toString());

                Logica.getInstance().cargarListaCorreos(((TreeItemMail)t1).getFolder());
            }
        });


        // para ver el contenido del correoseleccionado en la tabla
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

        try {
            Logica.getInstance().actualizarTree();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}




