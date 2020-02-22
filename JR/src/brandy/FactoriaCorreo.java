package brandy;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactoriaCorreo {
    public static List<Email> createListaCorreos() throws Exception {
        List<Email> listaCorreos = new ArrayList<>();
        listaCorreos.add(new Email("asunto","contenido", Date.valueOf(LocalDate.now()),"remitente"));
        return listaCorreos;
    }
}