package codenexus.modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Socio {
    private int idSocio;
    private String nombre;
    private String tipoSocio;
    private String nif;

    // Constructor vacío
    public Socio() {
    }

    // Constructor con todos los atributos
    public Socio(int idSocio, String nombre, String nif, String tipoSocio) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.tipoSocio = tipoSocio;
        this.nif = nif;
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

    public String getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(String tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    // Implementación para eliminar a un socio
    public void deleteSocio(ArrayList<Socio> listaSocios) {
        // Iterar sobre la lista de socios y eliminar el socio actual
        for (int i = 0; i < listaSocios.size(); i++) {
            if (listaSocios.get(i).getIdSocio() == this.idSocio) {
                listaSocios.remove(i);
                break; // Salir del bucle una vez que se ha eliminado el socio
            }
        }
    }

    // Implementación para añadir un nuevo socio
    public static Socio addSocio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del socio:");
        int idSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        System.out.println("Ingrese el nombre del socio:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el NIF del socio:");
        String nif = scanner.nextLine();

        System.out.println("Ingrese el tipo de socio (Estandar, Federado o Infantil):");
        String tipoSocio = scanner.nextLine();

        return new Socio(idSocio, nombre, nif, tipoSocio);
    }

    // Implementación para mostrar los detalles del socio
    public static void showSocio(ArrayList<Socio> listaSocios, String tipoFiltrado) {
        if (tipoFiltrado == null) {
            // Mostrar todos los socios
            for (Socio socio : listaSocios) {
                socio.mostrarDetalles();
            }
        } else {
            // Mostrar socios filtrados por tipo
            for (Socio socio : listaSocios) {
                if (socio.getTipoSocio().equals(tipoFiltrado)) {
                    socio.mostrarDetalles();
                }
            }
        }
    }

    // Método privado para mostrar los detalles de un socio individual
    private void mostrarDetalles() {
        System.out.println("ID de Socio: " + idSocio);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo de Socio: " + tipoSocio);
        System.out.println("NIF: " + nif);
        System.out.println("------------------------------------------");
    }

    // Método para mostrar la factura de un socio
    public void showFactura(double cuotaMensual, double totalPrecioExcursiones) {
        // Calcular el total de la factura
        double totalFactura = cuotaMensual + totalPrecioExcursiones;

        // Mostrar la factura
        System.out.println("Factura para " + nombre + ":");
        System.out.println("Cuota mensual: " + cuotaMensual + " euros");
        System.out.println("Total precio excursiones: " + totalPrecioExcursiones + " euros");
        System.out.println("Total factura: " + totalFactura + " euros");
    }

    // Implementación para calcular la cuota del socio
    public double calcularCuota() {
        double cuota = 0.0;

        // Calcular la cuota base según el tipo de socio
        switch (tipoSocio) {
            case "Estandar":
                cuota = 10.0; // Cuota base para socios estándar
                break;
            case "Federado":
                cuota = 10.0 * 0.95; // Cuota base con descuento del 5% para socios federados
                break;
            case "Infantil":
                cuota = 10.0 * 0.5; // Cuota base con descuento del 50% para socios infantiles
                break;
        }

        return cuota;
    }


    // Método toString para imprimir los detalles del socio
    @Override
    public String toString() {
        return "codenexus.modelo.Socio{" +
                "idSocio=" + idSocio +
                ", nombre='" + nombre + '\'' +
                ", tipoSocio='" + tipoSocio + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }
}
