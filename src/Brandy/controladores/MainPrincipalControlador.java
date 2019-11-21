package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
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
    void abrirVentana(ActionEvent event) {
        anadirUsuario();

    }

    @FXML
    void abrir_CargarMensajes(ActionEvent event) {

    }

    @FXML
    void borrarCorreo(ActionEvent event) {
        /*Mensaje m;
        int indice = tableView.getSelectionModel().getSelectedIndex();
        m = tableView.getItems().get(indice);
        m.setFlag(Flags.Flag.DELETED, true);
        folder.close(true);*/
    }

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

    }

    @FXML
    void salir(ActionEvent event) {

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
                Logica.getInstance().cargarListaCorreos(((TreeItemMail)t1).getFolder().getFullName());
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
 // Funciona lo tengo uesto en lboton de enviar correo
    // tengo dudas decomo uponerlo tmb lo tengo en logica este mismo metodo AYUDA!!!!!!!!!!!!!!!
   /* public  void actualizarTree() throws GeneralSecurityException, MessagingException {
        TreeItem nodoRaiz = new TreeItem("Correos");
        treeview.setRoot(nodoRaiz);
        for (int i = 0; i< Logica.getInstance().getListaUsuarios().size(); i++){
            Logica.getInstance().iniciarSesion(Logica.getInstance().getListaUsuarios().get(i));
            nodoRaiz.setExpanded(true);
            nodoRaiz.getChildren().add((Logica.getInstance().cargaCarpetas(Logica.getInstance().getListaUsuarios().get(i), null, null)));
        }
    }*/


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
}




