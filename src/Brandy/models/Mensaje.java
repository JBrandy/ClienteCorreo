package Brandy.models;
import org.apache.commons.mail.util.MimeMessageParser;

import java.io.IOException;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class Mensaje  {

    private Message mensaje;
    public Mensaje(Message mensaje) {
        this.mensaje = mensaje;
    }


    public String getAsunto(){
        String sub=null;
        try {
            sub = mensaje.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sub;
    }

    public Object getContenido(){
        Object obj=null;
        try {
            obj = mensaje.getContent();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public  String getRemitente(){
        Address[] sub=null;
        try {
            sub=mensaje.getFrom();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.valueOf(sub[0]);
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "mensaje=" + mensaje +
                '}';
    }




    public String getContent() throws Exception {
        String resultado = "";
        MimeMessageParser parser = new MimeMessageParser((MimeMessage) mensaje);
        parser.parse();

        if (mensaje.isMimeType("text/plain")) {
            resultado = parser.getPlainContent();
        } else if (mensaje.isMimeType("multipart/*")) {
            resultado = parser.getHtmlContent();
        } else if (mensaje.isMimeType(" text/html")) {
            resultado = parser.getHtmlContent();

        }

        return resultado;
    }

    public Message getMensaje() {
        return mensaje;
    }

    public void setMensaje(Message mensaje) {
        this.mensaje = mensaje;
    }
}