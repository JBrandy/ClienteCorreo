package Brandy.logica;

import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import Brandy.models.UsuarioCorreo;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiciosEmail {


    // https://www.javatpoint.com/example-of-sending-email-using-java-mail-api

    public boolean enviarCorreo(UsuarioCorreo usuarioCorreo, String to, String cc, String asunto, String cuerpo) {
       Session session = getSession(usuarioCorreo);
        //Prepare email message
        Message message = prepareMessage(session, usuarioCorreo.getEmail(), to, asunto, cuerpo, cc);
        if(message!=null){
            try {
                //Send mail
                Transport.send(message);
                System.out.println("Message sent successfully");
                return true;
            }catch (MessagingException ex){
               return false;
            }
        }
        return false;
    }

    private Message prepareMessage(Session session, String from, String to, String asunto, String cuerpo, String cc) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");
            //message.setReplyTo(cc); hacer un array de cuentas a enviar
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    //Message.RecipientType.BCC Destinatario al que se envía copia, pero sin que los demás destinatarios puedan verlo.


    private Session getSession(UsuarioCorreo usuarioCorreo){
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.imaps.ssl.trust", "*");
        properties.put("mail.imaps.ssl.socketFactory", sf);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = usuarioCorreo.getEmail();
        //Your gmail password
        String password = usuarioCorreo.getContra();
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        return session;
    }


}