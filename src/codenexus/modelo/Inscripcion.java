package codenexus.modelo;

import java.util.Date;

public class Inscripcion {
    private int idInscripcion;
    private int idSocio;
    private String idExcursion;
    private Date fechaInscripcion;

    // Constructor vacío
    public Inscripcion() {
    }

    // Constructor con todos los atributos
    public Inscripcion(int idInscripcion, int idSocio, String idExcursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idExcursion = idExcursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y setters
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(String idExcursion) {
        this.idExcursion = idExcursion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Método toString para imprimir los detalles de la inscripción
    @Override
    public String toString() {
        return "codenexus.modelo.Inscripcion{" +
                "idInscripcion=" + idInscripcion +
                ", idSocio=" + idSocio +
                ", idExcursion='" + idExcursion + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }

    // Métodos adicionales
    public void addInscripcion() {
        // Lógica para agregar una inscripción
    }

    public void deleteInscripcion() {
        // Lógica para eliminar una inscripción
    }

    public void showInscripcion() {
        // Lógica para mostrar detalles de una inscripción
    }

    public double totalPrecioExcursiones() {
        // Lógica para calcular el precio total de las excursiones
        return 0;
    }
}
