package Brandy;


import Brandy.controladores.MainPrincipalControlador;
import Brandy.controladores.PantallaMensajeControlador;
import Brandy.logica.Logica;
import Brandy.models.UsuarioCorreo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import reloj.Reloj;

import javax.mail.MessagingException;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


public class Launcher extends Application {
    private static List<UsuarioCorreo> list;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/mainPrincipal.fxml"));
        Parent root = fxmlLoader.load();
        MainPrincipalControlador pantallaInicio = (MainPrincipalControlador) fxmlLoader.getController();
        stage.setTitle("Bandeja");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                pantallaInicio.finalizarReloj();

//

            }
        });


    }

    public static void main(String args[]) throws GeneralSecurityException, MessagingException {
        leerFichero();
        inicio();
        launch(args);
        guardarFichero();




    }




    public static void inicio() throws GeneralSecurityException, MessagingException {
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                Logica.getInstance().iniciarSesion(list.get(i));
                Logica.getInstance().actualizarTree();
            }


    }

    public static void guardarFichero() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        List<UsuarioCorreo> aux = new ArrayList<>(Logica.getInstance().getListaUsuarios());

        try {
            fos = new FileOutputStream("bbdd/bbdd.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(aux);

            oos.flush();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {


            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void leerFichero() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {

            fis = new FileInputStream("bbdd/bbdd.dat");
            ois = new ObjectInputStream(fis);
            try {
                list = (List<UsuarioCorreo>) ois.readObject(); //
                System.out.println(list.get(0).toString());
                Logica.getInstance().getListaUsuarios().addAll(list);
            } catch (IndexOutOfBoundsException e) {

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}