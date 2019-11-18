package Brandy.controladores;
import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
    void anadir(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCorreos.setItems((ObservableList<UsuarioCorreo>) Logica.getInstance().getListaUsuarios());

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}




