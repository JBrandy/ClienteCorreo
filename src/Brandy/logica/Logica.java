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
    private UsuarioCorreo usuarioCorreo;
    private Message usuarioVer;
    private Store store;

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


    public void cargarListaCorreos(String folderString) {
        listaCorreos.clear();
        try {
           // IMAPFolder folder = (IMAPFolder) store.getFolder("[Gmail]/Todos");
            IMAPFolder folder = (IMAPFolder) store.getFolder(folderString);
            if (!folder.isOpen())
                folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            Mensaje correo;
            System.out.println(messages[0].toString());
            for(int i=0;i<messages.length;i++) {
                correo = new Mensaje(messages[i]);
                System.out.println(correo.toString());
                listaCorreos.add(correo);
            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public TreeItemMail cargaCarpetas(UsuarioCorreo usuarioCorreo1, Folder carpeta,TreeItemMail rootItem) throws MessagingException, GeneralSecurityException {

        Folder[] folders = null;
        if(store!=null) {

            if(carpeta==null){
                folders = store.getDefaultFolder().list(); //todas las del sistema
            }else{
                folders = carpeta.list();      //carpetas de la carpeta en la que estoy
            }
            if(rootItem==null){
                rootItem = new TreeItemMail(usuarioCorreo1.getEmail(), usuarioCorreo1, carpeta);
            }else{
                System.out.println("cojo el delrecursirvo");
            }

            rootItem.setExpanded(true);
             for (Folder folder : folders) {
                 //Añadiendo carpetas al tree
                 TreeItemMail item = new TreeItemMail(folder.getName(), usuarioCorreo1, folder);
                 if ((folder.getType() & Folder.HOLDS_FOLDERS) != 0
                         && folder.list().length>0) { //si tiene carpetas
                     cargaCarpetas(usuarioCorreo1, folder, item);
                }else{
                     System.out.println("La carpeta " + folder.getName() + " no tiene hijos.");
                 }
                 rootItem.getChildren().add(item);
             }
    }
        return rootItem;
    }

    public void iniciarSesion(UsuarioCorreo usuarioCorreo1) throws GeneralSecurityException, MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol", "imaps");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.imaps.ssl.trust", "*");
        prop.put("mail.imaps.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(prop, null);
        store = session.getStore("imaps");
        store.connect("imap.googlemail.com",usuarioCorreo1.getEmail(), usuarioCorreo1.getContra());
    }


}






