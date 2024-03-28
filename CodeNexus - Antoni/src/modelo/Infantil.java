package modelo;

import modelo.Socio;

public class Infantil extends Socio {
    private int idTutor;
    private double descuentoCuota;

    // Constructor vacío
    public Infantil() {
    }

    // Constructor con todos los atributos
    public Infantil(int idSocio, String nombre, int idTutor) {
        super(idSocio, nombre, "Infantil");
        this.idTutor = idTutor;
        this.descuentoCuota = descuentoCuota;
    }

    // Getter y setter para el tutor asociado
    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
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
        return "Infantil{" +
                "idSocio=" + getIdSocio() +
                ", nombre='" + getNombre() + '\'' +
                ", tipoSocio='" + getTipoSocio() + '\'' +
                ", idTutor=" + idTutor +
                '}';
    }
}

