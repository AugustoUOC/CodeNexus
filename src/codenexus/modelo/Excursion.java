package codenexus.modelo;

import java.util.Date;
public class Excursion {
    private String idExcursion;
    private String descripcion;
    private Date fechaExcursion;
    private int duracionDias;
    private double precioInscripcion;

    // Constructor vacío
    public Excursion() {
    }

    // Constructor con todos los atributos
    public Excursion(String idExcursion, String descripcion, Date fechaExcursion, int duracionDias, double precioInscripcion) {
        this.idExcursion = idExcursion;
        this.descripcion = descripcion;
        this.fechaExcursion = fechaExcursion;
        this.duracionDias = duracionDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters y setters
    public String getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(String idExcursion) {
        this.idExcursion = idExcursion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaExcursion() {
        return fechaExcursion;
    }

    public void setFechaExcursion(Date fechaExcursion) {
        this.fechaExcursion = fechaExcursion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    // Método toString para imprimir los detalles de la excursión
    @Override
    public String toString() {
        return "codenexus.modelo.Excursion{" +
                "idExcursion='" + idExcursion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaExcursion=" + fechaExcursion +
                ", duracionDias=" + duracionDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
    }

    // Métodos adicionales
    public void addExcursion() {
        // Lógica para agregar una excursión
    }

    public void mostrarExcursion() {
        // Lógica para mostrar detalles de una excursión
    }
}