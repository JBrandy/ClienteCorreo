package Brandy.controladores.filtros;

import Brandy.models.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class filtrar {


        private ObservableList<Mensaje> listaMensajes;
        private ObservableList<Mensaje> listaFiltrada;

        public filtrar(ObservableList<Mensaje> listaPersonas) {
            this.listaMensajes = listaPersonas;
            listaFiltrada = FXCollections.observableArrayList();
        }

        public ObservableList<Mensaje> filtrar(String apellidosFiltrar) throws Exception {
            if (apellidosFiltrar!=null && !"".equals(apellidosFiltrar))
            {
                //Necesitamos filtrar
                listaFiltrada.clear();
                for (Mensaje mensaje : listaMensajes)
                {
                    if (mensaje.getContent().contains(apellidosFiltrar))
                        listaFiltrada.add(mensaje);
                }
                return listaFiltrada;
            }
            else
            {
                //Tenemos que mostrar todos los registros
                return listaMensajes;
            }

        }
}
