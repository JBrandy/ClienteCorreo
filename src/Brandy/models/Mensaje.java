package Brandy.models;
import Brandy.logica.Logica;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class Mensaje {

    private Message message;

    public Mensaje (Message message){
        this.message=message;
    }

    public String getSubject()
    {
        try {
            return this.message.getSubject();
        } catch (MessagingException e) {
            return "";
        }
    }

    public  void cargarMensajes() throws MessagingException, IOException, GeneralSecurityException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flags.Flag flag = null;
        try
        {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.imaps.ssl.trust", "*");
            props.put("mail.imaps.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(props, null);
            store = session.getStore("imaps");
            store.connect("imap.googlemail.com","damdijb@gmail.com", "123456A@");



            folder = (IMAPFolder) store.getFolder("[Gmail]/Todos"); // This doesn't work for other email account
            //folder = (IMAPFolder) store.getFolder("inbox"); This works for both email account


            if(!folder.isOpen())
                folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
            System.out.println(messages.length);
            for (int i=0; i < messages.length;i++)
            {

                System.out.println("*****************************************************************************");
                System.out.println("MESSAGE " + (i + 1) + ":");
                Message msg =  messages[i];
                //System.out.println(msg.getMessageNumber());
                //Object String;
                //System.out.println(folder.getUID(msg)


                subject = msg.getSubject();

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: "+msg.getAllRecipients()[0]);
                System.out.println("Date: "+msg.getReceivedDate());
                System.out.println("Size: "+msg.getSize());
                System.out.println(msg.getFlags());
                System.out.println("Body: \n"+ msg.getContent());
                System.out.println(msg.getContentType());



                Logica.getInstance().getMensajes().add(msg);

// for each message message: folder.getMessager
                //lita.add(new Email(message)
            }
        }
        finally
        {
            if (folder != null && folder.isOpen()) { folder.close(true); }
            if (store != null) { store.close(); }
        }

    }

}

