package Brandy.controladores;



import Brandy.logica.Logica;
import Brandy.models.ListaTotalCorreos;
import Brandy.models.UsuarioCorreo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.mail.MessagingException;
import java.io.File;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HistoricoControlador implements Initializable {
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    @FXML
    private ComboBox<UsuarioCorreo> combo;

    @FXML
    void Imprimir(ActionEvent event) throws GeneralSecurityException, MessagingException {
        File file = getFile();
        ListaTotalCorreos listaTotalCorreos = new ListaTotalCorreos();
        listaTotalCorreos.iniciarSesion(combo.getSelectionModel().getSelectedItem());
        listaTotalCorreos.cargaCarpetas(combo.getSelectionModel().getSelectedItem(),null,null);
        listaTotalCorreos.cargarDatosInforme(combo.getSelectionModel().getSelectedItem());

        JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(listaTotalCorreos.getList()); //lista sería la colección a mostrar. Típicamente saldría de la lógica de nuestra aplicación
        Map<String,Object> parametros = new HashMap<>(); //En este caso no hay parámetros, aunque podría haberlos
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport("jasper/ListaTodosCorreos.jasper", parametros, jr);

            //print = JasperFillManager.fillReport(getClass().getResourceAsStream("/Brandy/jasper/ListaTodosCorreos.jasper"), parametros, jr);
        } catch (JRException e) {
            e.printStackTrace();
        }
        try {
            JasperExportManager.exportReportToPdfFile(print, file.toPath().toString());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public File getFile() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showSaveDialog(null);
    }
    @FXML
    void salir(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo.setItems((ObservableList<UsuarioCorreo>) Logica.getInstance().getListaUsuarios());
    }
}
