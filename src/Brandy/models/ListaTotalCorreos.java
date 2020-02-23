package Brandy.models;

import Brandy.logica.Logica;
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
    private List <ListaTotalCorreos> list = FXCollections.observableArrayList();



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

        for (int i = 0; i < Logica.getInstance().getFolders().length; i++) {
            Folder folder1 = Logica.getInstance().getFolders()[i];
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


}
