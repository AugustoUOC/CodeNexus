package codenexus.modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Socio {
    private int idSocio;
    private String nombre;

    // Constructor vacío
    public Socio() {
    }

    // Constructor con todos los atributos
    public Socio(int idSocio, String nombre) {
        this.idSocio = idSocio;
        this.nombre = nombre;

    }

    // Getters y setters
    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // Método toString para imprimir los detalles del socio
    @Override
    public String toString() {
        return "codenexus.modelo.Socio{" +
                "idSocio=" + idSocio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
