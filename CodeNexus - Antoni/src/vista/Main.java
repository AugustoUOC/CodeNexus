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
                            Datos.mostrarExcursionesPorFechas();
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
                            System.out.println("Ingrese el ID del socio estándar:");
                            int idSocioEstandar = scanner.nextInt();
                            scanner.nextLine();
                            Socio socio = Datos.obtenerSocioPorId(idSocioEstandar);
                            if (socio instanceof Estandar) {
                                Estandar socioEstandar = (Estandar) socio; // Convertimos el socio a tipo Estandar
                                System.out.println("Seleccione el nuevo tipo de seguro:");
                                System.out.println("1. Básico");
                                System.out.println("2. Completo");
                                int opcionSeguro = scanner.nextInt();
                                scanner.nextLine();
                                switch (opcionSeguro) {
                                    case 1:
                                        if (!socioEstandar.getSeguroContratado().isTipo()) {
                                            System.out.println("El socio ya tiene el seguro básico. No se han aplicado cambios.");
                                        } else {
                                            socioEstandar.modificarSeguro(new Seguro(false, 0.0)); // Tipo básico
                                            System.out.println("El seguro del socio se ha cambiado correctamente a Básico.");
                                        }
                                        break;
                                    case 2:
                                        if (socioEstandar.getSeguroContratado().isTipo()) {
                                            System.out.println("El socio ya tiene el seguro completo. No se han aplicado cambios.");
                                        } else {
                                            socioEstandar.modificarSeguro(new Seguro(true, 0.0)); // Tipo completo
                                            System.out.println("El seguro del socio se ha cambiado correctamente a Completo.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                        break;
                                }
                            } else {
                                System.out.println("El socio con ID " + idSocioEstandar + " no es un socio estándar.");
                            }
                            break;
                        case 3:
                            Datos.deleteSocio(Datos.listaSocios);
                            break;
                        case 4:
                            Datos.showSocio(Datos.listaSocios);
                            break;
                        case 5:
                            System.out.println("Introducir ID del socio para ver su Factura");
                            Socio socioFactura = Datos.obtenerSocioPorId(scanner.nextInt());
                            if (socioFactura == null) {
                                return;
                            }
                            System.out.println("Id del Socio: " + socioFactura.getIdSocio());
                            System.out.println("Precio: " + Datos.mostrarFactura(socioFactura));
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
                            Datos.addInscripcion(Datos.listaSocios,Datos.listaExcursiones, fechaActual);
                            break;
                        case 2:
                            Datos.eliminarInscripcion();
                            break;
                        case 3:
                            Datos.showInscripcion();
                            break;
                        default:
                            break;
                    }
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        }
        scanner.close();
    }
}