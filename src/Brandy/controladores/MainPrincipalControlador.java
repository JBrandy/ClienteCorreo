package Brandy.controladores;


import Brandy.controladores.filtros.FiltrarMensajes;
import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import Brandy.models.TreeItemMail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import reloj.Evento;
import reloj.Reloj;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import reloj.Tarea;

import javax.mail.MessagingException;

public class MainPrincipalControlador implements Initializable {


    static Scanner teclado = new Scanner(System.in);

    private FiltrarMensajes filtrarMensajes;

    private ButtonType buttonTypeOne = new ButtonType("Realizado");
    private ButtonType buttonTypeTwo = new ButtonType("NO Realizado");
    private ButtonType buttonTypeCancel = new ButtonType("Nota", ButtonBar.ButtonData.CANCEL_CLOSE);


    @FXML
    private Menu mInicio;

    @FXML
    private MenuItem miInicioSesion;
    @FXML
    private MenuItem mConfigurarCuentas;
    @FXML
    private MenuItem configurarTema;

    @FXML
    private MenuItem miSalir;

    @FXML
    private Button btNuevoCorreo;

    @FXML
    private Button btBorrar;

    @FXML
    private Button btReenviar;

    @FXML
    private Button btGuardar;

    @FXML
    private TextField tfBuscador;

    @FXML
    private Button btQuitarFiltro;

    @FXML
    private Button btResponder;

    @FXML
    public Reloj reloj;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private TableView<Mensaje> tableView;

    @FXML
    private WebView webView;
    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private Label labelRemitente;

    @FXML
    public TreeView<String> treeview;

    @FXML
    private MenuItem btCongif;

    private TreeItemMail TreeItem;

    @FXML
    private Button btImprimir;



    @FXML
    void imprimir(ActionEvent event) {
        JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(lista); //lista sería la colección a mostrar. Típicamente saldría de la lógica de nuestra aplicación
        Map<String,Object> parametros = new HashMap<>(); //En este caso no hay parámetros, aunque podría haberlos
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/Brandy/controladores/informes/jasper/primerinforme.jasper"), parametros, jr);
        JasperExportManager.exportReportToPdfFile(print, "informes/primerinforme.pdf");
    }



    @FXML
    private Button btExamen;


    @FXML
    void examen(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/examen.fxml"));
            Parent root = fxmlLoader.load();
            ExamenControlador pantallaConfigCorreo = (ExamenControlador) fxmlLoader.getController();
            pantallaConfigCorreo.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();


    }


    @FXML
    private Button btTareas;


    public Reloj getReloj() {
        return reloj;
    }

    @FXML
    void abrirTareas(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaTareas.fxml"));
            Parent root = fxmlLoader.load();
            PantallaTareasControlador pantallaTareasControlador = (PantallaTareasControlador) fxmlLoader.getController();
            pantallaTareasControlador.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 750, 600));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }


    @FXML
    void configuarCuentas(ActionEvent event) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/configurarCorreo.fxml"));
            Parent root = fxmlLoader.load();
            ConfigurarCorreoControlador pantallaConfigCorreo = (ConfigurarCorreoControlador) fxmlLoader.getController();
            pantallaConfigCorreo.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();

    }

    @FXML
    void caragarListaMensajes(MouseEvent event) {

    }

    @FXML
    void filtrarTable(ActionEvent event) {

    }

    @FXML
    void abrirVentana(ActionEvent event) {
        anadirUsuario();

    }

    @FXML
    void cambiartema(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        ConfiguracionTemaControlador configuracionTemaControlador = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/configuracionTema.fxml"));
            Parent root = fxmlLoader.load();
            configuracionTemaControlador = (ConfiguracionTemaControlador) fxmlLoader.getController();
            // pantallaConfigCorreo.setMainController(this);
            configuracionTemaControlador.setStage(stage);
            stage.setTitle("Configuracion visual");
            stage.setScene(new Scene(root, 400, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        reloj.setFormato24Horas(configuracionTemaControlador.formatoHora());
       


    }


    @FXML
    void borrarCorreo(ActionEvent event) throws MessagingException, GeneralSecurityException {

        Mensaje m = tableView.getSelectionModel().getSelectedItem();
        TreeItemMail email_tree = (TreeItemMail) treeview.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert_null = new Alert(Alert.AlertType.WARNING);
            alert_null.setTitle("Alerta");
            alert_null.setContentText("No hay mensajes seleccionados");
            alert_null.showAndWait();
        } else {
            Logica.getInstance().borrar_email(m, email_tree.getFolder());
            webView.getEngine().loadContent(""); // Preguntar si es la manera correcta?
            Logica.getInstance().actualizaTable(((TreeItemMail) treeview.getSelectionModel().getSelectedItem()).getFolder());


        }
    }


    @FXML
    void enviarCorreo(ActionEvent event) throws GeneralSecurityException, MessagingException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaMensaje.fxml"));
            Parent root = fxmlLoader.load();
            PantallaMensajeControlador pantallaInicio = (PantallaMensajeControlador) fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Nuevo Correo");
            stage.setScene(new Scene(root, 850, 600));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();


    }

    @FXML
    void enviarRecibir(ActionEvent event) {
        webView.getEngine().loadContent(""); // Preguntar si es la manera correcta?
        Logica.getInstance().actualizaTable(((TreeItemMail) treeview.getSelectionModel().getSelectedItem()).getFolder());
    }

    @FXML
    void quitarFiltro(ActionEvent event) {
        tfBuscador.setText("");
    }

    @FXML
    void reenviarCorreo(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaMensaje.fxml"));
            Parent root = fxmlLoader.load();
            PantallaMensajeControlador pantallaInicio = (PantallaMensajeControlador) fxmlLoader.getController();
            Mensaje msg = tableView.getSelectionModel().getSelectedItem();
            pantallaInicio.reenviar(msg);
            pantallaInicio.setStage(stage);
            stage.setTitle("Nuevo Correo");
            stage.setScene(new Scene(root, 850, 600));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }

    @FXML
    void salir(ActionEvent event) {

    }

    private void anadirUsuario() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/login.fxml"));
            Parent root = fxmlLoader.load();
            LoginControlador pantallaInicio = (LoginControlador) fxmlLoader.getController();
            pantallaInicio.setStage(stage);
            stage.setTitle("Correo");
            stage.setScene(new Scene(root, 850, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        tableView.setItems(Logica.getInstance().getListaCorreos());

    }


    @FXML
    void responder(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Brandy/vistas/pantallaMensaje.fxml"));
            Parent root = fxmlLoader.load();
            PantallaMensajeControlador pantallaInicio = (PantallaMensajeControlador) fxmlLoader.getController();
            Mensaje msg = tableView.getSelectionModel().getSelectedItem();
            pantallaInicio.responder(msg);
            pantallaInicio.setStage(stage);
            stage.setTitle("Nuevo Correo");
            stage.setScene(new Scene(root, 850, 600));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.showAndWait();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reloj.start();
        reloj.setFormato24Horas(false);

        reloj.addEvento(new Evento() {
            @Override
            public void inicioTarea(Tarea tarea) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Recordatorio de Tareas");
                alert.setHeaderText("Has realizado la tarea: " + tarea.getTarea());
                alert.setContentText("Escoge unaopción.");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    tarea.setRealizado("Realizado");
                } else if (result.get() == buttonTypeTwo) {
                    // ... user chose "Two"
                    tarea.setRealizado("NO Realizado");

                } else {
                    TextInputDialog dialog = new TextInputDialog("Escribe aquí tu nota");
                    dialog.setTitle("Recordatorio de Tareas");
                    dialog.setHeaderText("Escribe pendiente, posponer etc ");
                    Optional<String> result2 = dialog.showAndWait();

                    result2.ifPresent(nota -> tarea.setRealizado(result2.get()));

                    ;
                }

            }
        });


        try {

            treeview.setRoot(Logica.getInstance().actualizarTree());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //https://medium.com/@keeptoo/javafx-java-modern-ui-design-starter-pack-aab1c331fd3c
        /* Para verdonde esta el filderdel treeview mire en el debug el selectitem y abriendo vi
         un folder que dentro tenia un fullname que es la ruta que necesito parapasarlo
         Para ver los correos del treeView en la tabla*/

        treeview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observableValue, TreeItem<String> stringTreeItem, TreeItem<String> t1) {
                System.out.println(treeview.getSelectionModel().getSelectedItem().toString());

                Logica.getInstance().cargarListaCorreos(((TreeItemMail) t1).getFolder());
            }
        });

        // para ver el contenido del correoseleccionado en la tabla
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mensaje>() {
            @Override
            public void changed(ObservableValue<? extends Mensaje> observable, Mensaje oldValue, Mensaje newValue) {
                try {
                    if (newValue != null)
                        webView.getEngine().loadContent(newValue.getContent());
                    else
                        webView.getEngine().loadContent("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Logica.getInstance().actualizarTree();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //Poner en negrita
        tableView.setRowFactory(new Callback<TableView<Mensaje>, TableRow<Mensaje>>() {
            @Override
            public TableRow<Mensaje> call(TableView<Mensaje> param) {
                return new TableRow<>() {
                    @Override
                    protected void updateItem(Mensaje item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            if (!item.isRead()) {
                                setStyle("-fx-font-weight:bold");
                            } else
                                setStyle("");
                        }
                    }
                };
            }
        });

        filtrarMensajes = new FiltrarMensajes(Logica.getInstance().getListaCorreos());
        //Nos subscribimos a cambios en la propiedad text del textfield
        tfBuscador.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {

                    tableView.setItems(filtrarMensajes.filtrar(newValue));

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

}
