package Brandy.controladores;


import Brandy.logica.EnviarMensajeService;
import Brandy.logica.Logica;
import Brandy.logica.ServiciosEmail;
import Brandy.models.Mensaje;
import Brandy.models.UsuarioCorreo;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private ProgressIndicator progressIndicator;

    private ServiciosEmail serviciosEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDe.getItems().addAll(Logica.getInstance().getListaUsuarios());
        serviciosEmail = new ServiciosEmail();
        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        progressIndicator.setVisible(false);
    /**
     * Asigno al botonel servicio
     */
        btEnviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                    enviar();
            }
        });

    }

    public void enviar() {

      UsuarioCorreo usuarioCorreo = cbDe.getSelectionModel().getSelectedItem();
      String to = tfPara.getText();
      String cc = tfCc.getText();
      String asunto = tfAsunto.getText();
      String cuerpo = htmlCuerpoMensaje.getHtmlText();
      if(usuarioCorreo!=null && to!=null && !to.isEmpty()){
            //boolean mailEnviado = serviciosEmail.enviarCorreo(usuarioCorreo, to, cc, asunto, cuerpo);
          EnviarMensajeService enviarMensajeService = new EnviarMensajeService(usuarioCorreo, to, cc, asunto, cuerpo);
          enviarMensajeService.start();

          enviarMensajeService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
              @Override
              public void handle(WorkerStateEvent workerStateEvent) {
                  //Recuperamos el valor de retorno
                  progressIndicator.setVisible(false);
                  stage.close();
              }
          });
          enviarMensajeService.setOnFailed(new EventHandler<WorkerStateEvent>() {
              @Override
              public void handle(WorkerStateEvent workerStateEvent) {
                  Alert alert_null = new Alert(Alert.AlertType.WARNING);
                  alert_null.setTitle("Alerta");
                  alert_null.setContentText("Error al enviar");
                  alert_null.showAndWait();

              }
          });

          enviarMensajeService.setOnRunning(new EventHandler<WorkerStateEvent>() {
              @Override
              public void handle(WorkerStateEvent workerStateEvent) {

                  progressIndicator.setVisible(true);
              }
          });


      }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("ERROR");
          alert.setHeaderText("Debes seleccionar una valor en el combo y ara quien va el mensaje");
          alert.show();
      }


        }




    public void cancelar(ActionEvent event) {
        stage.close();
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
