package Brandy.models;
import org.apache.commons.mail.util.MimeMessageParser;

import java.io.IOException;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class Mensaje  {

    private Message message;
    public Mensaje(Message message) {
        this.message = message;
    }

    public String getAsunto(){
        String sub=null;
        try {
            sub = message.getSubject();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sub;
    }

    public Object getContenido(){
        Object obj=null;
        try {
            obj = message.getContent();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public  String getRemitente(){
        Address[] sub=null;
        try {
            sub= message.getFrom();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.valueOf(sub[0]);
    }

    public  Date getFecha(){
        Date sub=null;
        try {
            sub= message.getReceivedDate();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sub;
    }


    @Override
    public String toString() {
        return " " + getRemitente() + "," + getFecha() +
                '}';
    }




    public String getContent() throws Exception {
        String resultado = "";
        MimeMessageParser parser = new MimeMessageParser((MimeMessage) message);
        parser.parse();

        if (message.isMimeType("text/plain")) {
            resultado = parser.getPlainContent();
        } else if (message.isMimeType("multipart/*")) {
            resultado = parser.getHtmlContent();
        } else if (message.isMimeType(" text/html")) {
            resultado = parser.getHtmlContent();

        }

        return resultado;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isRead()  {
        try {
            return message.isSet(Flags.Flag.SEEN);
        } catch (MessagingException e) {
            e.printStackTrace();
        }return true;
    }



}