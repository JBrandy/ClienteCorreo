package Brandy.controladores;

import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class LoginControlador implements Initializable {

    UsuarioCorreo u ;

    @FXML
    private TextField tvCorreo;

    @FXML
    private PasswordField tvContrasena;

    @FXML
    private Button btInicio;

    @FXML
    private Button btSalir;
    private Stage stage;

    @FXML
    void cancelar(ActionEvent event) {
stage.close();
    }


    @FXML
    void iniciarSesion(ActionEvent event) throws IOException, GeneralSecurityException, MessagingException {
        try{
            String email = tvCorreo.getText();
            String contra =tvContrasena.getText();
            u =new UsuarioCorreo(email,contra);
            Logica.getInstance().anadirUsuario(u);
            System.out.println("AAAAAÑÑÑÑÑADIMOOOOSS!!!");
            Logica.getInstance().actualizarTree();
            stage.close();
        }catch (GeneralSecurityException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error en el usuario o la contraseña");
            alert.show();
            System.out.println(e.getMessage());
        }catch (MessagingException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error en el usuario o la contraseña");
            alert.show();
            System.out.println(e.getMessage());

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tvCorreo.setText("damdijb@gmail.com");
        tvContrasena.setText("123456A@");

       /*ValidationSupport vs = new ValidationSupport();
        vs.registerValidator(tvContrasena, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tvCorreo, Validator.createEmptyValidator("el campo no puede estar vacio"));


        btInicio.disableProperty().bind(vs.invalidProperty());

        validationSupport.registerValidator(textField3, new Validator<String>()
{
      @Override
      public ValidationResult apply(Control control, String value)
      {
          try {
              int i = Integer.parseInt(value);
              if (i>=100)
                 return ValidationResult.fromError( control, "El número no es menor de 100");
          } catch (NumberFormatException n)
          {
              return ValidationResult.fromError( control, "No es un número");
          }
          return null;
      }
});*/


}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}