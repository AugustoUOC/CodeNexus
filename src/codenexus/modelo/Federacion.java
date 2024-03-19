package codenexus.modelo;

public class Federacion {
    private String idFederacion;
    private String nombre;

    // Constructor vacío
    public Federacion() {
    }

    // Constructor con todos los atributos
    public Federacion(String idFederacion, String nombre) {
        this.idFederacion = idFederacion;
        this.nombre = nombre;
    }

    // Getters y setters
    public String getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(String idFederacion) {
        this.idFederacion = idFederacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString para imprimir los detalles de la federación
    @Override
    public String toString() {
        return "codenexus.modelo.Federacion{" +
                "idFederacion='" + idFederacion + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
