package Brandy.logica;

import Brandy.models.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Logica {



        private static Logica INSTANCE = null;
        private ObservableList<Mensaje> mensajes = FXCollections.observableArrayList();


        private Logica() {

        }



        public static Logica getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new Logica();
            }

            return INSTANCE;
        }

    public ObservableList<Mensaje> getMensajes() {
            return mensajes;
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


}


