package brandy;

import org.apache.commons.mail.util.MimeMessageParser;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

public class Email {




    private String asunto;
    private String contenido;
    private Date fecha;
    private String remitente;


    public Email(String asunto, String contenido, Date fecha, String remitente) {
        this.asunto = asunto;
        this.contenido = contenido;
        this.fecha = fecha;
        this.remitente = remitente;
    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
}