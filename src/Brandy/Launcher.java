package Brandy;



import Brandy.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Launcher extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vistas/PantallaInicio.fxml"));
        stage.setTitle("Bandeja");
        stage.setScene(new Scene(root, 850, 400));
        stage.show();

    }

    public static void main(String args[])
    {


        launch(args);


    }
}