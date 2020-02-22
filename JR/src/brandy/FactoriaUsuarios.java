package brandy;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactoriaUsuarios {
    public static List<UsuarioCorreo> createListaUsuarios() throws Exception {
        List<UsuarioCorreo> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new UsuarioCorreo("saas","contras1234eña"));
        listaUsuarios.add(new UsuarioCorreo("p@a.es","1234"));
        return listaUsuarios;
    }
}
