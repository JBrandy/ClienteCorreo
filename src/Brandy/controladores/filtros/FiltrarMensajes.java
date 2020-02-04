package Brandy.controladores.filtros;

import Brandy.logica.Logica;
import Brandy.models.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FiltrarMensajes {


    private ObservableList<Mensaje> listaMensajes;
    private ObservableList<Mensaje> listaFiltrada;

    public FiltrarMensajes(ObservableList<Mensaje> listaMensajes) {
        this.listaMensajes = Logica.getInstance().getListaCorreos();
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Mensaje> filtrar(String textofiltrado) throws Exception {
        if (textofiltrado != null && !"".equals(textofiltrado)) {
            //Necesitamos filtrar
            listaFiltrada.clear();
            for (Mensaje mensaje : listaMensajes) {
                if (mensaje.getAsunto() != null && mensaje.getAsunto().toUpperCase().contains(textofiltrado.toUpperCase()))
                    listaFiltrada.add(mensaje);
            }
            return listaFiltrada;
        } else {
            //Tenemos que mostrar todos los registros
            return listaMensajes;
        }

    }
}
