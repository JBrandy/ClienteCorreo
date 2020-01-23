package Brandy.models;

import java.time.LocalDate;

public class Tarea {

    private String tarea;
    private LocalDate fecha;
    private Integer hora;
    private Integer minuto;
    private String realizado;

    public Tarea(String tarea, LocalDate fecha, Integer hora, Integer minuto, String realizado) {
        this.tarea = tarea;
        this.fecha = fecha;
        this.hora = hora;
        this.minuto = minuto;
        this.realizado = realizado;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "tarea='" + tarea + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", minuto=" + minuto +
                ", realizado='" + realizado + '\'' +
                '}';
    }
}
