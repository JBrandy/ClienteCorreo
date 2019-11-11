package Brandy.logica;

public class UsuarioCorreo {

    private String email;
    private String contra;

    public UsuarioCorreo(String email, String contra) {
        this.email = email;
        this.contra = contra;
    }

    public UsuarioCorreo() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
