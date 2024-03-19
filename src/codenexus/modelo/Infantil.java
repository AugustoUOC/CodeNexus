package codenexus.modelo;

public class Infantil extends Socio {
    private Socio idTutor;
    private double descuentoCuota;

    // Constructor vacío
    public Infantil() {
    }

    // Constructor con todos los atributos
    public Infantil(int idSocio, String nombre, String tipoSocio, Seguro seguroContratado, Federacion federacion, Socio idTutor, double descuentoCuota) {
        super(idSocio, nombre, tipoSocio, null); // No se requiere NIF para los socios infantiles
        this.idTutor = idTutor;
        this.descuentoCuota = descuentoCuota;
    }

    // Getter y setter para el tutor asociado
    public Socio getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Socio idTutor) {
        this.idTutor = idTutor;
    }

    // Getter y setter para el descuento en la cuota mensual
    public double getDescuentoCuota() {
        return descuentoCuota;
    }

    public void setDescuentoCuota(double descuentoCuota) {
        this.descuentoCuota = descuentoCuota;
    }

    // Método toString para imprimir los detalles del socio infantil
    @Override
    public String toString() {
        return "codenexus.modelo.Infantil{" +
                "idSocio=" + getIdSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", tipoSocio='" + getTipoSocio() + '\'' +
                ", idTutor=" + idTutor +
                ", descuentoCuota=" + descuentoCuota +
                '}';
    }
}

