package vista;

import modelo.Datos;

import modelo.*;


import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true; // Variable para controlar si se debe continuar ejecutando el programa

        // Bucle principal del menú
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestión de excursiones");
            System.out.println("2. Gestión de socios");
            System.out.println("3. Gestión de inscripciones");
            System.out.println("0. Cerrar el programa");
            ;
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("1. Añadir excursión");
                    System.out.println("2. Mostrar escursiones");
                    System.out.println("0. Volver atrás");
                    int opcionExcursion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionExcursion) {
                        case 1:
                            Datos.addExcursion();
                            break;
                        case 2:
                            Datos.mostrarExcursionesPorFechas(Datos.listaExcursiones);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Añadir socio");
                    System.out.println("2. Modificar tipo de seguro de socio Estándar");
                    System.out.println("3. Eliminar socio");
                    System.out.println("4. Mostrar socios");
                    System.out.println("5. Mostrar factura mensual por socio");
                    System.out.println("0. Volver atrás");
                    int opcionSocio = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionSocio) {
                        case 1:
                            Datos.addSocio();
                            break;
                        case 2:
                            //Modificar tipo de seguro de socio estandar
                            break;
                        case 3:
                            Datos.deleteSocio(Datos.listaSocios);
                            break;
                        case 4:
                            Datos.showSocio(Datos.listaSocios);
                            break;
                        case 5:
                            //Modificar factura mensual
                            break;
                        default:
                            break;
                    }
                    break;


                case 3:
                    System.out.println("1. Añadir inscripción");
                    System.out.println("2. Eliminar inscripción");
                    System.out.println("3. Mostrar inscripciones");
                    System.out.println("0. Volver atrás");
                    int opcionInscripcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionInscripcion) {
                        case 1:
                            Date fechaActual = new Date();
                            Datos.addInscripcion(Datos.listaSocios, Datos.listaExcursiones, fechaActual);
                            break;
                        case 2:
                            // Eliminar Inscripciones
                            break;
                        case 3:
                            System.out.println("Seleccione una opción:");
                            System.out.println("1. Mostrar todas las inscripciones");
                            System.out.println("2. Aplicar filtro por socio");
                            System.out.println("3. Aplicar filtro por fecha");
                            System.out.println("4. Aplicar ambos filtros");
                            System.out.println("0. Volver atrás");
                            int opcionFiltro = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcionFiltro) {
                                case 1:
                                    Datos.showInscripcion(Datos.listaInscripciones, Datos.listaSocios);
                                    break;
                                case 2:
                                    Datos.mostrarPorTipoSocio(Datos.listaInscripciones, Datos.listaSocios);
                                    break;
                                case 3:
                                    Datos.mostrarPorFecha(Datos.listaInscripciones);
                                    break;
                                case 4:
                                    Datos.mostrarPorTipoSocioYFecha(Datos.listaInscripciones, Datos.listaSocios);
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;
            }

        }
        scanner.close();
    }
}