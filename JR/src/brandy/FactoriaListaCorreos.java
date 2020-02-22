package brandy;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactoriaListaCorreos {
    public static List<ListaTotalCorreos> createListaUsuarios() throws Exception {
        List<ListaTotalCorreos> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new ListaTotalCorreos("jose@jose.es","carpeta", "asunto","remitente", Date.valueOf(LocalDate.now())));
        return listaUsuarios;
    }
}
