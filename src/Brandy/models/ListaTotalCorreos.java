package Brandy.models;

import javafx.collections.FXCollections;

import javax.mail.*;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

public class ListaTotalCorreos {

    private String Correo;
    private String folder;
    private String asunto;
    private String remitente;
    private Date fecha;
    private Store store;
    private List<ListaTotalCorreos> listaTotalCorreosList = FXCollections.observableArrayList();

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

    public List<ListaTotalCorreos> getListaTotalCorreosList() {
        return listaTotalCorreosList;
    }

    public void setListaTotalCorreosList(List<ListaTotalCorreos> listaTotalCorreosList) {
        this.listaTotalCorreosList = listaTotalCorreosList;
    }

    public void cargarListaCorreos(Folder folder, UsuarioCorreo usuarioCorreo) {

        if (folder != null) {
            try {
                // IMAPFolder folder = (IMAPFolder) store.getFolder("[Gmail]/Todos"); el final es la ruta
                //IMAPFolder folder = (IMAPFolder) store.getFolder(folderString);

                if (!folder.isOpen())
                    folder.open(Folder.READ_WRITE);
                Message[] messages = folder.getMessages();
                Mensaje correo;
                System.out.println(messages[0].toString());
                System.out.println(folder.getFullName());
                if (messages.length > 0) {
                    for (int i = 0; i < messages.length; i++) {
                        correo = new Mensaje(messages[i]);
                        listaTotalCorreosList.add(new ListaTotalCorreos(usuarioCorreo.getEmail(),folder.getName(),correo.getAsunto(),correo.getRemitente(), correo.getFecha()));
                    }
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


    public void cargarTodosCorreos(UsuarioCorreo usuarioCorreo1, Folder carpeta, TreeItemMail rootItem) throws MessagingException, GeneralSecurityException {

        Folder[] folders = null;
        if (store != null) {

            if (carpeta == null) {
                folders = store.getDefaultFolder().list(); //todas las del sistema
                System.out.println("La carpeta " + folders.toString());
                cargarListaCorreos(carpeta, usuarioCorreo1);
            } else {
                folders = carpeta.list();
                System.out.println("La carpeta " + carpeta.getName());
                cargarListaCorreos(carpeta, usuarioCorreo1);
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
                    cargarTodosCorreos(usuarioCorreo1, folder, item);
                } else {
                    //System.out.println("La carpeta " + folder.getName() + " no tiene hijos.");
                }
                rootItem.getChildren().add(item);
            }
        }
    }
}
