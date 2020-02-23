package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.Email;
import Brandy.models.Mensaje;
import Brandy.models.UsuarioCorreo;
import javafx.collections.ObservableList;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;

public class ConfigurarCorreoControlador implements Initializable {

    private Stage stage;

    @FXML
    private TableView<UsuarioCorreo> tableCorreos;

    @FXML
    private Button btAnadirr;

    @FXML
    private Button btDelete;

    @FXML
    private Button btSalir;

    @FXML
    private Button btImprimir;





    @FXML
    void imprimir(ActionEvent event) {

        MainPrincipalControlador mainPrincipalControlador = new MainPrincipalControlador();
        File file = mainPrincipalControlador.getFile();
        List<UsuarioCorreo> lista = new ArrayList<>();
        lista = tableCorreos.getItems();


        JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(lista); //lista sería la colección a mostrar. Típicamente saldría de la lógica de nuestra aplicación
        Map<String,Object> parametros = new HashMap<>(); //En este caso no hay parámetros, aunque podría haberlos
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(getClass().getResourceAsStream("/Brandy/jasper/ListaUsuarios.jasper"), parametros, jr);
        } catch (JRException e) {
            e.printStackTrace();
        }
        try {
            JasperExportManager.exportReportToPdfFile(print, file.getPath()
            );
        } catch (JRException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void anadir(ActionEvent event) throws GeneralSecurityException, MessagingException {
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

    }

    @FXML
    void eliminar(ActionEvent event) throws GeneralSecurityException, MessagingException {

        Logica.getInstance().eliminar(tableCorreos.getSelectionModel().getSelectedItem());
        Logica.getInstance().actualizarTree();


    }

    @FXML
    void salir(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCorreos.setItems((ObservableList<UsuarioCorreo>) Logica.getInstance().getListaUsuarios());

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}




