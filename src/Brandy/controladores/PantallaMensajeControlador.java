package Brandy.controladores;


import Brandy.logica.Logica;
import Brandy.logica.ServiciosEmail;
import Brandy.models.Mensaje;
import Brandy.models.UsuarioCorreo;
import javafx.css.StyleConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaMensajeControlador implements Initializable {


    private Stage stage;
    @FXML
    private HTMLEditor htmlCuerpoMensaje;

    @FXML
    private ComboBox<UsuarioCorreo> cbDe;

    @FXML
    private TextField tfPara;

    @FXML
    private TextField tfCc;

    @FXML
    private TextField tfAsunto;

    @FXML
    private Button btEnviar;

    @FXML
    private TextField tfDe;

    @FXML
    private Button btCancelar;

    private ServiciosEmail serviciosEmail;



    @FXML
    public void enviar() {
      UsuarioCorreo usuarioCorreo = cbDe.getSelectionModel().getSelectedItem();
      String to = tfPara.getText();
      String cc = tfCc.getText();
      String asunto = tfAsunto.getText();
      String cuerpo = htmlCuerpoMensaje.getHtmlText();
      if(usuarioCorreo!=null && to!=null && !to.isEmpty()){
            boolean mailEnviado = serviciosEmail.enviarCorreo(usuarioCorreo, to, cc, asunto, cuerpo);
            if (mailEnviado==true){
                stage.close();
            }
      }




    }

    public void cancelar(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDe.getItems().addAll(Logica.getInstance().getListaUsuarios());
        serviciosEmail = new ServiciosEmail();
       /* for (int i =0; 0<Logica.getInstance().getListaUsuarios().size();i++){
            cbDe.getItems().add(Logica.getInstance().getListaUsuarios().get(i).getEmail());
        }*/

    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void reenviar(Mensaje msg) throws Exception {
        UsuarioCorreo usuarioCorreo = cbDe.getSelectionModel().getSelectedItem();
        tfPara.setText(msg.toString());
        tfCc.setText(msg.getRemitente());
         tfAsunto.setText(msg.getAsunto());
        htmlCuerpoMensaje.setHtmlText(msg.getContent());

          enviar();


    }
}
