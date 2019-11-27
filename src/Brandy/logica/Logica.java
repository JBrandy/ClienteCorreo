package Brandy.logica;

import Brandy.controladores.MainPrincipalControlador;
import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import Brandy.models.UsuarioCorreo;
import com.sun.mail.util.MailSSLSocketFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import com.sun.mail.imap.IMAPFolder;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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

    private TreeItem nodoRaiz =new TreeItem("Correos");

    private String email;
    private String contra;
    private UsuarioCorreo usuarioCorreo;
    private Message usuarioVer;
    private Store store;

    private Logica() {
        listaCorreos = FXCollections.observableArrayList();
        listaUsuarios= FXCollections.observableArrayList();
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
           // IMAPFolder folder = (IMAPFolder) store.getFolder("[Gmail]/Todos"); el final es la ruta
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



    private TreeItemMail cargaCarpetas(UsuarioCorreo usuarioCorreo1, Folder carpeta,TreeItemMail rootItem) throws MessagingException, GeneralSecurityException {

        Folder[] folders = null;
        if(store!=null) {

            if(carpeta==null){
                folders = store.getDefaultFolder().list(); //todas las del sistema
                System.out.println("La carpeta " + folders.toString() );
            }else{
                folders = carpeta.list();
                System.out.println("La carpeta " + carpeta.getName() );
//carpetas de la carpeta en la que estoy
            }
            if(rootItem==null){
                rootItem = new TreeItemMail(usuarioCorreo1.getEmail(), usuarioCorreo1, carpeta);
                System.out.println("La carpeta " + rootItem.toString() );
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


    /*
    Clase de configurar mwetodos
     */
    public void eliminar (UsuarioCorreo u) throws GeneralSecurityException, MessagingException {
        Logica.getInstance().getListaUsuarios().remove(u);


    }


    public  TreeItem actualizarTree() throws GeneralSecurityException, MessagingException {
        nodoRaiz.getChildren().clear();
        //nodoRaiz = new TreeItem("Correos");
        for (int i = 0; i< getListaUsuarios().size(); i++){
           iniciarSesion(getListaUsuarios().get(i));
            nodoRaiz.setExpanded(true);
            nodoRaiz.getChildren().add((cargaCarpetas(getListaUsuarios().get(i), null, null)));
        }

        return nodoRaiz;
        //devolvemos el nodo raiz para que el controller pueda pasarlselo al treeview
    }



    public  void borrar_email(Mensaje m_borrar, TreeItemMail email_tree) {

        if(email_tree.getFolder().toString().equals("[Gmail]/Papelera")){

            try {
                m_borrar.getMensaje().setFlag(Flags.Flag.DELETED, true);
                email_tree.getFolder().close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }else {
            Message[] m = new Message[]{m_borrar.getMensaje()};
            Folder trash = null;
            try {
                trash = store.getFolder("[Gmail]/Papelera");
                email_tree.getFolder().copyMessages(m, trash);
                email_tree.getFolder().close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }


}






