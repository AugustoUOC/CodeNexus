package codenexus.vista;
import codenexus.modelo.Socio;
import codenexus.modelo.Excursion;
import codenexus.modelo.Estandar;
import codenexus.modelo.Federacion;
import codenexus.modelo.Infantil;
import codenexus.modelo.Federado;
import codenexus.modelo.Inscripcion;
import codenexus.modelo.Seguro;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        ArrayList<Socio> socios = new ArrayList<>(); // Lista de socios
        ArrayList<Excursion> excursiones = new ArrayList<>(); // Lista de excursiones

        Scanner scanner = new Scanner(System.in);

        // Menú principal
        int opcion;
        do {
            System.out.println("---- MENÚ PRINCIPAL ----");
            System.out.println("1. Agregar socio");
            System.out.println("2. Mostrar socios");
            System.out.println("3. Eliminar Socio");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.println("Agregar socio:");
                    Socio socio = Socio.addSocio();
                    socios.add(socio);
                    break;
                case 2:
                    System.out.println("Socios registrados:");
                    for (Socio s : socios) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    System.out.println("Eliminar socio:");
                    System.out.print("Ingrese el ID del socio a eliminar: ");
                    int idSocioEliminar = scanner.nextInt();
                    boolean eliminado = false;
                    for (int i = 0; i < socios.size(); i++) {
                        if (socios.get(i).getIdSocio() == idSocioEliminar) {
                            socios.remove(i);
                            eliminado = true;
                            break;
                        }
                    }
                    if (eliminado) {
                        System.out.println("Socio eliminado exitosamente.");
                    } else {
                        System.out.println("No se encontró ningún socio con el ID especificado.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Métodos auxiliares (buscarSocioPorId, calcularTotalPrecioExcursiones)...

    // Resto del código...
}