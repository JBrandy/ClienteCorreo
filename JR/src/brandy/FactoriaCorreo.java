package brandy;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactoriaCorreo {
    public static List<Mensaje> createListaCorreos()
    {
        List<Mensaje> listaCorreos = new ArrayList<>();
        listaCorreos.add(new Mensaje("asunto","contenido", Date.valueOf(LocalDate.now()),"remitente"));
        return listaCorreos;
    }
}