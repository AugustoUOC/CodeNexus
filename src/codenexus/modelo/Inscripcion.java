package codenexus.modelo;

import java.util.Date;

public class Inscripcion {
    private int idInscripcion;
    private Socio socio;
    private String idExcursion;
    private Date fechaInscripcion;

    // Constructor vacío
    public Inscripcion() {
    }

    // Constructor con todos los atributos
    public Inscripcion(int idInscripcion, Socio socio, String idExcursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.socio = socio;
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

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
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
                ", socio=" + socio +
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
