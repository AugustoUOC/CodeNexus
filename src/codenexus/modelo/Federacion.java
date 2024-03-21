package codenexus.modelo;

public class Federacion {
    private String idFederacion;
    private String nombreFederacion;

    // Constructor vacío
    public Federacion() {
    }

    // Constructor con todos los atributos
    public Federacion(String idFederacion, String nombreFederacion) {
        this.idFederacion = idFederacion;
        this.nombreFederacion = nombreFederacion;
    }

    // Getters y setters
    public String getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(String idFederacion) {
        this.idFederacion = idFederacion;
    }

    public String getNombreFederacion() {
        return nombreFederacion;
    }

    public void setNombreFederacion(String nombre) {
        this.nombreFederacion = nombre;
    }

    // Método toString para imprimir los detalles de la federación
    @Override
    public String toString() {
        return "codenexus.modelo.Federacion{" +
                "idFederacion='" + idFederacion + '\'' +
                ", nombre='" + nombreFederacion + '\'' +
                '}';
    }
}
