package Brandy.logica;

import Brandy.models.Mensaje;
import com.sun.mail.util.MailSSLSocketFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import com.sun.mail.imap.IMAPFolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Properties;

public class Logica {

    private static Logica INSTANCE = null;


    private ObservableList<Mensaje> listaCorreos;


    private Logica() {
        listaCorreos = FXCollections.observableArrayList();

    }

    public static Logica getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logica();
            System.out.println("!sdasdas");
        }
        return INSTANCE;
    }

    public ObservableList<Mensaje> getListaCorreos() {
        System.out.println("!sdasassasaasasdas");
        return listaCorreos;

    }

    public void cargarListaCorreos() {
        System.out.println("!sdasdas");
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
            store.connect("imap.googlemail.com","damdijb@gmail.com", "123456A@");



            folder = (IMAPFolder) store.getFolder("[Gmail]/Todos"); // This doesn't work for other email account


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
}

  /* public void printTable(TableView<ObservableList> table, String subject, String from) {


        ObservableList<ObservableList> data = FXCollections.observableArrayList();


        javafx.scene.control.TableColumn col = new javafx.scene.control.TableColumn();
        col.setText("de");
        table.getColumns().addAll(col);

        javafx.scene.control.TableColumn col2 = new javafx.scene.control.TableColumn();
        col.setText("asunto");
        table.getColumns().addAll(col2);




        ObservableList<String> row = FXCollections.observableArrayList();

        row.add(subject);
        row.add(from);


        //Adding the row to the data.
        data.add(row);


    }*/





