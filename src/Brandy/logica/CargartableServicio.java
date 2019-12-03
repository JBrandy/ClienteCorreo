package Brandy.logica;

import Brandy.models.Mensaje;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

/* se tipa con el metodok que queramos*/
public class CargartableServicio extends Service<Boolean> {


  private Mensaje mensaje;
  private Message messge;

    public CargartableServicio(Mensaje mensaje) {
        this.messge=messge;
        this.mensaje = new Mensaje(messge);
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception{
                return mensaje.isRead();
            }
        };
    }



    /* ecuando esxtendemos devolvera un taSK TIPADO CON LO QUE LE PASAMOS POR EL EXTENDS
     *
     * */
}
