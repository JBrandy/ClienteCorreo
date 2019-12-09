package Brandy.models;

import com.sun.mail.imap.IMAPFolder;

import javax.mail.MessagingException;
import javax.mail.Store;
import java.io.Serializable;

public class UsuarioCorreo implements Serializable {

    private String email;
    private String contra;
    private Store store;

    public UsuarioCorreo(String email, String contra) {
        this.email = email;
        this.contra = contra;
    }

    public UsuarioCorreo() {

    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


public IMAPFolder getImapFolder (String direcion){
    IMAPFolder folder = null;
    try {
        folder = (IMAPFolder) store.getFolder(direcion);
    } catch (MessagingException e) {
        e.printStackTrace();
    }

    return folder;
}

    @Override
    public String toString() {
        return "<" +
                  email + '\'' +
                '>';
    }
}
