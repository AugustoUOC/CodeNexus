package modelo;


public class Federado extends Socio {
    private String nif;
    public Federacion federacion;
    private double descuentoExcursion;
    private double descuentoCuota;

    // Constructor vacío
    public Federado() {
    }

    // Constructor con todos los atributos
    public Federado(int idSocio, String nombre, Federacion federacion, String nif) {
        super(idSocio, nombre, "Federado");
        this.federacion = federacion;
        this.nif = nif;


    }

    // Getter y setter para el NIF
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    // Getter y setter para la federación
    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    // Getter y setter para el descuento en excursiones
    public double getDescuentoExcursion() {
        return descuentoExcursion;
    }

    public void setDescuentoExcursion(double descuentoExcursion) {
        this.descuentoExcursion = descuentoExcursion;
    }

    // Getter y setter para el descuento en la cuota mensual
    public double getDescuentoCuota() {
        return descuentoCuota;
    }

    public void setDescuentoCuota(double descuentoCuota) {
        this.descuentoCuota = descuentoCuota;
    }

    // Método toString para imprimir los detalles del socio federado
    @Override
    public String toString() {
        return "Federado{" +
                "idSocio='" + getIdSocio() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", tipoSocio='" + getTipoSocio() + '\'' +
                ", federacion=" + federacion +
                ", nif='" + nif + '\'' +
                '}';
    }
}
