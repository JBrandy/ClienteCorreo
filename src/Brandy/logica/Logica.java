package Brandy.logica;

import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import Brandy.models.UsuarioCorreo;
import com.sun.mail.util.MailSSLSocketFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import com.sun.mail.imap.IMAPFolder;
import javafx.scene.control.TreeItem;

import javax.mail.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Logica  {

    private static Logica INSTANCE = null;


    private ObservableList<Mensaje> listaCorreos;
    private List<UsuarioCorreo> listaUsuarios;

    private String email;
    private String contra;
    private Message usuarioVer;

    private Logica() {
        listaCorreos = FXCollections.observableArrayList();
        listaUsuarios= new ArrayList<>();
    }

    public static Logica getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logica();
        }
        return INSTANCE;
    }

    public ObservableList<Mensaje> getListaCorreos() {
        return listaCorreos;
    }
    public List<UsuarioCorreo> getListaUsuarios() {
        return listaUsuarios;
    }
    public void anadirUsuario(UsuarioCorreo u){
        listaUsuarios.add(u);
    }


    public void cargarListaCorreos(UsuarioCorreo usuarioCorreo) {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flags.Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.imaps.ssl.trust", "*");
            props.put("mail.imaps.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(props, null);
            store = session.getStore("imaps");
            store.connect("imap.googlemail.com",usuarioCorreo.getEmail(), usuarioCorreo.getContra());

            folder = (IMAPFolder) store.getFolder("[Gmail]/Todos");

            if (!folder.isOpen())
                folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            Mensaje correo;
            System.out.println(messages[0].toString());
            for(int i=0;i<messages.length;i++)
            {
                correo=new Mensaje(messages[i]/*,messages[i].getSubject()*/);
             System.out.println(correo.toString());
                listaCorreos.add(correo);
            }




        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void verContenido(Message usuarioCorreoVer) throws IOException, MessagingException {

        this.usuarioVer = usuarioCorreoVer;

        Multipart multipart = (Multipart) usuarioCorreoVer.getContent();
        for(int k = 0; k < multipart.getCount(); k++){
            BodyPart bodyPart = multipart.getBodyPart(k);
            InputStream stream =
                    (InputStream) bodyPart.getInputStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(stream));

            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
        }



    }
    //----------https://stackoverflow.com/questions/11240368/how-to-read-text-inside-body-of-mail-using-javax-mail



    public String getMessageContent(Mensaje correo) throws MessagingException {
        Message message =correo.getMensaje();
        try {
            Object content = message.getContent();
            if (content instanceof Multipart) {
                StringBuffer messageContent = new StringBuffer();
                Multipart multipart = (Multipart) content;
                for (int i = 0; i < multipart.getCount(); i++) {
                    Part part = multipart.getBodyPart(i);
                    if (part.isMimeType("text/plain")) {
                        messageContent.append(part.getContent().toString());
                    }
                }
                return messageContent.toString();
            }
            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public TreeItemMail cargaCarpetas() throws MessagingException {
        TreeItemMail rootItem = new TreeItemMail("nombre",usuarioCorreo,"direccion email");
        Folder[] folders = store.getDefaultFolder().list(/*"*"*/);
        rootItem.setExpanded(true);
        for (Folder folder : folders) {
            if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
                TreeItemMail item = new TreeItemMail("nombre",usuarioCorreo,folder.getName().toString());
                rootItem.getChildren().add(item);

            }
        }
        return rootItem;
    }

    public TreeItemMail cargaCarpetas() throws MessagingException {

        TreeItemMail rootItem = new TreeItemMail(listaUsuarios.get(0).getEmail(), listaUsuarios.get(0));

        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol", "imaps");
        Session sesion = Session.getInstance(prop);
        Store store = sesion.getStore("imaps");
        store.connect("imap.googlemail.com",listaUsuarios.get(0).getEmail(), listaUsuarios.get(0).getContra());
        Folder[] folders = store.getDefaultFolder().list(/*"*"*/);
        rootItem.setExpanded(true);
        for (Folder folder : folders) {
            if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
                TreeItemMail item = new TreeItemMail(folder.getName().toString(), listaUsuarios.get(0));
                rootItem.getChildren().add(item);
            }
        }
        return rootItem;
    }

}






