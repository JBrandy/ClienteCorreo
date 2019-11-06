package Brandy.logica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Logica {

    public void printTable(TableView<ObservableList> table, String subject, String from) {

        //Data.
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        //Columns.
        javafx.scene.control.TableColumn col = new javafx.scene.control.TableColumn();
        col.setText("de");
        table.getColumns().addAll(col);

        javafx.scene.control.TableColumn col2 = new javafx.scene.control.TableColumn();
        col.setText("asunto");
        table.getColumns().addAll(col2);



        //Row.
        ObservableList<String> row = FXCollections.observableArrayList();

        row.add(subject);
        row.add(from);


        //Adding the row to the data.
        data.add(row);


    }
}

