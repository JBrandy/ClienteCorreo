package Brandy.logica;

import Brandy.models.UsuarioCorreo;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
/* se tipa con el metodok que queramos*/
public class EnviarMensajeService extends Service<Boolean> {

    private UsuarioCorreo usuarioCorreo;
    private String to;
    private String cc;
    private String asunto;
    private String cuerpo;
    private ServiciosEmail serviciosEmail;

    public EnviarMensajeService(UsuarioCorreo usuarioCorreo, String to, String cc, String asunto, String cuerpo) {
        this.usuarioCorreo = usuarioCorreo;
        this.to = to;
        this.cc = cc;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.serviciosEmail = new ServiciosEmail();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception{
                return serviciosEmail.enviarCorreo(usuarioCorreo,to,cc,asunto,cuerpo);
            }
        };
    }



    /* ecuando esxtendemos devolvera un taSK TIPADO CON LO QUE LE PASAMOS POR EL EXTENDS
        *
    * */
}
