package Brandy.models;

import Brandy.logica.Logica;
import com.sun.mail.util.MailSSLSocketFactory;
import javafx.collections.FXCollections;

import javax.mail.*;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ListaTotalCorreos {

    private String Correo;
    private String folder;
    private String asunto;
    private String remitente;
    private Date fecha;
    private List <ListaTotalCorreos> list = FXCollections.observableArrayList();

    private  Folder[] folders;
    private Store store;

    public ListaTotalCorreos() {

    }

    public ListaTotalCorreos(String correo, String folder, String asunto, String remitente, Date fecha) {
        Correo = correo;
        this.folder = folder;
        this.asunto = asunto;
        this.remitente = remitente;
        this.fecha = fecha;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ListaTotalCorreos> getList() {
        return list;
    }

    public void cargarDatosInforme(UsuarioCorreo usuarioCorreo){

        for (int i = 0; i < folders.length; i++) {
            Folder folder1 = folders[i];
            if (folder1 != null) {
                try {

                    if (!folder1.isOpen())
                        folder1.open(Folder.READ_WRITE);
                    Message[] messages = folder1.getMessages();
                    Mensaje correo;
                    System.out.println(messages[0].toString());
                    System.out.println(folder1.getFullName());
                    for (int j = 0; j < messages.length; j++) {
                        correo = new Mensaje(messages[j]);

                        list.add(new ListaTotalCorreos(usuarioCorreo.getEmail(), folder1.getFullName(),
                                correo.getAsunto(),correo.getRemitente(), correo.getFecha()));

                    }
                } catch (NoSuchProviderException e) {
                    //e.printStackTrace();
                } catch (MessagingException e) {
                    //e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }
            }
        }


    }


    public void cargaCarpetas(UsuarioCorreo usuarioCorreo1, Folder carpeta, TreeItemMail rootItem) throws MessagingException, GeneralSecurityException {

        folders = null;
        if (store != null) {

            if (carpeta == null) {
                folders = store.getDefaultFolder().list(); //todas las del sistema
            } else {
                folders = carpeta.list();
//carpetas de la carpeta en la que estoy
            }
            if (rootItem == null) {
                rootItem = new TreeItemMail(usuarioCorreo1.getEmail(), usuarioCorreo1, carpeta);
                //System.out.println("La carpeta " + rootItem.toString() );
            } else {
                // System.out.println("cojo el delrecursirvo");
            }

            rootItem.setExpanded(true);
            for (Folder folder : folders) {
                //Añadiendo carpetas al tree
                TreeItemMail item = new TreeItemMail(folder.getName(), usuarioCorreo1, folder);
                if ((folder.getType() & Folder.HOLDS_FOLDERS) != 0
                        && folder.list().length > 0) { //si tiene carpetas
                    cargaCarpetas(usuarioCorreo1, folder, item);
                } else {
                    //System.out.println("La carpeta " + folder.getName() + " no tiene hijos.");
                }
                rootItem.getChildren().add(item);
            }
        }
    }

    public void iniciarSesion(UsuarioCorreo usuarioCorreo1) throws GeneralSecurityException, MessagingException {
        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol", "imaps");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.imaps.ssl.trust", "*");
        prop.put("mail.imaps.ssl.socketFactory", sf);
        store = usuarioCorreo1.getStore();
        Session session = Session.getDefaultInstance(prop, null);
        store = session.getStore("imaps");
        store.connect("imap.googlemail.com", usuarioCorreo1.getEmail(), usuarioCorreo1.getContra());
    }


}
