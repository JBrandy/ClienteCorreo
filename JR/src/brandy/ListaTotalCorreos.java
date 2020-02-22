package brandy;

import java.util.Date;

public class ListaTotalCorreos {

    private String Correo;
    private String folder;
    private String asunto;
    private String remitente;
    private Date fecha;


    public ListaTotalCorreos(String correo, String folder, String asunto, String remitente, Date fecha) {
        Correo = correo;
        this.folder = folder;
        this.asunto = asunto;
        this.remitente = remitente;
        this.fecha = fecha;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
