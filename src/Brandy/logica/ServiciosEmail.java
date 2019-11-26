package Brandy.logica;

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
                System.out.println(ex.getMessage());
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
            //message.setReplyTo(cc); hacer un array de copias
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    //Windows -> Preferencs -> Java -> JRES instalado ->

    private Session getSession(UsuarioCorreo usuarioCorreo){
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");


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
