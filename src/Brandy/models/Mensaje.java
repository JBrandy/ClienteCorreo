package Brandy.models;
import Brandy.logica.Logica;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javafx.beans.Observable;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;

public class Mensaje   {

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
}