package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Datos {

    //Contadores

    public static int contadorExcursiones = 0;
    public static int contadorSocios = 0;
    public static int contadorFederaciones = 0;
    public static int contadorInscripciones = 0;

    //Listados
    public static List<Excursion> listaExcursiones = new ArrayList<>();
    public static List<Socio> listaSocios = new ArrayList<>();
    public static List<Inscripcion> listaInscripciones = new ArrayList<>();



    //Métodos para excursiones
    public static void addExcursion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la descripción de la excursión:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese la fecha de la excursión (formato: dd/MM/yyyy):");
        Date fechaExcursion = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        while (fechaExcursion == null) {
            try {
                fechaExcursion = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente.");
            }
        }

        System.out.println("Ingrese la duración en días de la excursión:");
        int duracionDias = scanner.nextInt();

        System.out.println("Ingrese el precio de inscripción:");
        double precioInscripcion = scanner.nextDouble();

        Excursion excursion = new Excursion(++contadorExcursiones, descripcion, fechaExcursion, duracionDias, precioInscripcion);
        listaExcursiones.add(excursion);
        System.out.println("Excursión agregada correctamente: " + excursion);
    }
    public static void mostrarExcursionesPorFechas(List<Excursion> listaExcursiones) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
        Date fechaInicio = leerFecha(scanner, dateFormat);

        System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
        Date fechaFin = leerFecha(scanner, dateFormat);

        if (fechaInicio.after(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
            return;
        }

        System.out.println("Excursiones entre " + dateFormat.format(fechaInicio) + " y " + dateFormat.format(fechaFin) + ":");

        boolean excursionesEncontradas = false;
        for (Excursion excursion : listaExcursiones) {
            Date fechaExcursion = excursion.getFechaExcursion();
            if (fechaExcursion.after(fechaInicio) && fechaExcursion.before(fechaFin)) {
                System.out.println(excursion);
                excursionesEncontradas = true;
            }
        }

        if (!excursionesEncontradas) {
            System.out.println("No se encontraron excursiones en el rango de fechas especificado.");
        }
    }

   //Subfunción de mostrarExcursionesPorFechas
    private static Date leerFecha(Scanner scanner, SimpleDateFormat dateFormat) {
        Date fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                String fechaStr = scanner.nextLine();
                fecha = dateFormat.parse(fechaStr);
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato dd/MM/yyyy: ");
            }
        }
        return fecha;
    }

    //Métodos para Socios
    public static void deleteSocio(List<Socio> socios, List<Inscripcion> listaInscripciones) {
        if (socios.isEmpty()) {
            System.out.println("No hay socios para eliminar.");
            return;
        }

        // Mostrar lista de socios con números asociados
        System.out.println("Lista de socios:");
        for (int i = 0; i < socios.size(); i++) {
            System.out.println((i + 1) + ". " + socios.get(i).getNombre());
        }

        // Pedir al usuario que elija un número de socio para eliminar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de socio que desea eliminar: ");
        int opcion = scanner.nextInt();

        if (opcion < 1 || opcion > socios.size()) {
            System.out.println("Número de socio inválido.");
            return;
        }
        // Eliminar el socio seleccionado
        Socio socioAEliminar = socios.get(opcion - 1);

        //Confirmamos si ha hecho alguna excursion
        boolean confirmacion = true;
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdSocio() == socioAEliminar.getIdSocio()) {
                confirmacion = false; // El socio participó en esta excursión
            } else {
                confirmacion = true;
            }
        }
        if (confirmacion) {
            socios.remove(socioAEliminar);
            System.out.println("El socio " + socioAEliminar.getNombre() + " ha sido eliminado correctamente.");
        } else {
            System.out.println("El socio " + socioAEliminar.getNombre() + " no puede ser eliminado ya que ha realizado o va a realizar una excursión.");
        }
    }

    public static void addSocio() {
        Socio nuevoSocio = null; // Inicialización por defecto
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del socio:");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el tipo de socio:\n1. Socio Estandar\n2. Socio Federado\n3. Socio Infantil");
        String tipoSocio = scanner.nextLine();

        switch (tipoSocio.toLowerCase()) {
            case "1":
                System.out.println("Ingrese el NIF del socio:");
                String nifEstandar = scanner.nextLine();
                System.out.println("Seleccione el tipo de seguro:");
                System.out.println("1. Básico");
                System.out.println("2. Completo");
                int opcionSeguro = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                boolean seguro;
                switch (opcionSeguro) {
                    case 1:
                        seguro = false;
                        System.out.println("Se ha seleccionado el seguro básico.");
                        break;
                    case 2:
                        seguro = true;
                        System.out.println("Se ha seleccionado el seguro completo.");
                        break;
                    default:
                        System.out.println("Opción no válida. Se asignará el seguro básico por defecto.");
                        seguro = false;
                        break;
                }
                Seguro seguroEstandar = new Seguro(seguro, 0.0);
                nuevoSocio = new Estandar(++contadorSocios, nombre, nifEstandar, seguroEstandar);
                break;
            case "2":
                System.out.println("Ingrese el NIF del socio:");
                String nifFederado = scanner.nextLine();
                System.out.println("Ingrese el nombre de la federación:");
                String nombreFederacion = scanner.nextLine();
                Federacion federacion = new Federacion(++contadorFederaciones, nombreFederacion);
                nuevoSocio = new Federado(++contadorSocios, nombre, federacion, nifFederado);
                break;
            case "3":
                String nombreTutor = scanner.nextLine();
                System.out.println("Ingrese el ID del tutor:");
                int idTutor = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                // Busca el tutor en la lista de socios
                Socio tutor = null;
                for (Socio s : listaSocios) {
                    if (s.getIdSocio() == idTutor) {
                        tutor = s;
                        nombreTutor = s.getNombre();
                        break;
                    }
                }

                // Verifica si se encontró al tutor
                if (tutor == null) {
                    System.out.println("No se encontró ningún socio con el ID especificado. El socio infantil no se pudo agregar.");
                } else {
                    // Pregunta al usuario si el tutor encontrado es el correcto
                    System.out.println("Se ha encontrado al siguiente tutor:");
                    System.out.println(tutor);
                    System.out.println("El nombre de ese socio es:");
                    System.out.println(nombreTutor);
                    System.out.println("¿Es este el tutor que desea asociar al socio infantil? (s/n)");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        // Crea el socio infantil con el tutor encontrado
                        nuevoSocio = new Infantil(++contadorSocios, nombre, idTutor);
                        System.out.println("modelo.Socio infantil agregado correctamente.");
                    } else {
                        System.out.println("Se ha cancelado la operación. El socio infantil no se pudo agregar.");
                    }
                }
                break;
            default:
                System.out.println("Tipo de socio no válido.");
                return;
        }

        listaSocios.add(nuevoSocio);
        System.out.println("Socio agregado correctamente.");
    }

    public static void modificarSeguro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del socio estándar para modificar su seguro:");
        int idSocio = scanner.nextInt();
        scanner.nextLine();

        Socio socio = obtenerSocioPorId(idSocio, listaSocios);
        if (socio == null) {
            System.out.println("No se encontró ningún socio con el ID especificado.");
            return;
        }


        // Verificar si el socio es de tipo estándar
        if (socio instanceof Estandar socioEstandar) {
            // Mostrar el seguro actual del socio
            System.out.println("El seguro actual del socio es: " + socioEstandar.getSeguroContratado());

            // Mostrar opciones de seguro
            System.out.println("Seleccione el nuevo seguro:");
            System.out.println("1. Seguro Básico");
            System.out.println("2. Seguro Completo");
            int opcionSeguro = scanner.nextInt();
            scanner.nextLine();

            // Asignar el nuevo seguro al socio
            switch (opcionSeguro) {
                case 1:
                    socioEstandar.modificarSeguro(new Seguro(false, 0.0)); // Tipo básico
                    break;
                case 2:
                    socioEstandar.modificarSeguro(new Seguro(true, 0.0)); // Tipo completo
                    break;
                default:
                    System.out.println("Opción no válida. No se realizaron cambios en el seguro.");
                    break;
            }

            // Mostrar el nuevo seguro del socio
            System.out.println("El nuevo seguro del socio es: " + socioEstandar.getSeguroContratado());
        } else {
            System.out.println("El socio no es de tipo estándar. No se puede modificar el seguro.");
        }
    }
    public static void showSocio(List<Socio> socios) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Qué socios deseas mostrar?");
        System.out.println("1. Mostrar todos los socios");
        System.out.println("2. Mostrar socios por tipo");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Lista de todos los socios:");
                for (Socio socio : socios) {
                    System.out.println(socio);
                }
                break;
            case 2:
                System.out.println("¿Qué tipo de socio deseas mostrar?");
                System.out.println("1. Estándar");
                System.out.println("2. modelo.Federado");
                System.out.println("3. modelo.Infantil");
                int tipoSocio = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Lista de socios de tipo " + tipoSocio + ":");

                for (Socio socio : socios) {
                    if (tipoSocio == 1 && socio instanceof Estandar) {
                        System.out.println(socio);
                    } else if (tipoSocio == 2 && socio instanceof Federado) {
                        System.out.println(socio);
                    } else if (tipoSocio == 3 && socio instanceof Infantil) {
                        System.out.println(socio);
                    }
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    //Funcion para mostrar el Importe total de la Factura segun el Socio y las excursiones que tiene asignadas
    public static void mostrarFactura(List<Socio> listaSocios, List<Excursion> listaExcursiones, List<Inscripcion> listaInscripciones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del socio para mostrar su factura:");
        int idSocio = scanner.nextInt();
        Socio socioFactura = obtenerSocioPorId(idSocio, listaSocios);
        if (socioFactura == null) {
            System.out.println("Socio no encontrado");
        } else {
            System.out.println("Id del Socio: " + socioFactura.getIdSocio());

            ArrayList<Inscripcion> inscripciones = new ArrayList<>();
            for (Inscripcion inscripcion : listaInscripciones) {
                if (inscripcion.getIdSocio() == socioFactura.getIdSocio()) {
                    inscripciones.add(inscripcion);
                }
            }
            double coste = 0;
            for (Inscripcion inscripcion : inscripciones) {
                for (Excursion excursion : listaExcursiones) {
                    if (inscripcion.getIdExcursion() == excursion.getIdExcursion()) {
                        coste += calcularCosteExcursion(socioFactura, excursion);
                    }
                }
            }
            System.out.println("Factura mensual del socio numero: " + idSocio + ", es igual a: " + coste + " Euros.");
        }
    }

    // Funcion para la logica de calcular la cuota + el coste de las inscripciones segun el Socio
    public static double calcularCosteExcursion(Socio socio, Excursion excursion) {
        double precio = 0;
        if (socio instanceof Estandar) {
            precio = calcularCuota(socio) + excursion.getPrecioInscripcion() + ((Estandar) socio).getSeguroContratado().getPrecio();
        } else if (socio instanceof Federado) {
            double precioTemporal = calcularCuota(socio) + excursion.getPrecioInscripcion();
            precio = precioTemporal * 0.9;
        } else if (socio instanceof Infantil) {
            precio = calcularCuota(socio) + excursion.getPrecioInscripcion();
        }
        return precio;
    }


    // Funcion para Calcular la cuenta segun el tipo de Socio que sea
    public static double calcularCuota(Socio socio) {
        double cuotaBase = 10.0; // Cuota base
        if (socio instanceof Estandar) {
            // La cuota para Estandar es la cuotaBase sin cambios
        } else if (socio instanceof Federado) {
            // Federado tiene un descuento en la cuota
            cuotaBase *= 0.95;
        } else if (socio instanceof Infantil) {
            // Infantil tiene un 50% de descuento
            cuotaBase *= 0.5;
        }
        return cuotaBase;
    }


    //Métodos para inscripciones
    public static void addInscripcion(List<Socio> listaSocios, List<Excursion> excursiones, Date fechaInscripcion) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del socio en el que desea inscribirse (o pulse 0 para un nuevo socio): ");
        int numeroSocioElegido = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Socio socioElegido = null;
        if (numeroSocioElegido == 0) {
            // Agregar un nuevo socio
            Datos.addSocio();
            // Obtener el último socio agregado
            socioElegido = listaSocios.get(listaSocios.size() - 1);
            numeroSocioElegido = socioElegido.getIdSocio(); // Obtener el ID del nuevo socio
        } else {
            // Verificar si el número de socio elegido es válido
            boolean numeroSocioValido = false;
            for (Socio socio : listaSocios) {
                if (socio.getIdSocio() == numeroSocioElegido) {
                    numeroSocioValido = true;
                    socioElegido = socio;
                    break;
                }

                if (!numeroSocioValido) {
                    System.out.println("El número de socio ingresado no es válido. La inscripción no pudo ser realizada.");
                    return;
                }
            }
        }
            // Mostrar los detalles del socio elegido y verificar si es correcto
            System.out.println("Se ha encontrado al siguiente socio:");
            System.out.println("Número de socio: " + socioElegido.getIdSocio());
            System.out.println("Nombre: " + socioElegido.getNombre());
            System.out.println("¿Es este el socio en el que desea inscribirse? (s/n)");
            String respuesta = scanner.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                System.out.println("Se ha cancelado la inscripción. La inscripción no pudo ser realizada.");
                return;
            }
            // Mostrar un listado de excursiones disponibles con sus IDs y nombres
            System.out.println("Excursiones disponibles:");
            for (Excursion excursion : excursiones) {
                System.out.println("ID: " + excursion.getIdExcursion() + " - Nombre: " + excursion.getDescripcion());
            }

            // Solicitar al usuario que elija el ID de la excursión
            System.out.print("Ingrese el ID de la excursión en la que desea inscribirse: ");
            int idExcursionElegida = scanner.nextInt();
            scanner.nextLine();

            // Verificar si el ID de la excursión elegida es válido
            boolean idValido = false;
            for (Excursion excursion : excursiones) {
                if (excursion.getIdExcursion() == idExcursionElegida) {
                    idValido = true;
                    break;
                }
            }

            if (!idValido) {
                System.out.println("El ID de la excursión ingresado no es válido. La inscripción no pudo ser realizada.");
                return;
            }
            Date fechaActual = new Date();
            // Crear la inscripción con el ID de la excursión elegida
            Inscripcion inscripcion = new Inscripcion(++contadorInscripciones, socioElegido.getIdSocio(), idExcursionElegida, fechaActual);
            listaInscripciones.add(inscripcion);
            System.out.println("Inscripción agregada correctamente.");
        }

    public static void eliminarInscripcion(List<Excursion> listaExcursiones, List<Inscripcion> listaInscripciones) {
        Scanner scanner = new Scanner(System.in);
        mostrarInscripcionesBorrables(listaExcursiones,listaInscripciones);
        System.out.println("Elige al ID de la Inscripcion que quieres Eliminar");
        int idParaBorrar = scanner.nextInt();
        Inscripcion inscripcionParaBorrar = obtenerInscripcionPorId(idParaBorrar, listaInscripciones);
        listaInscripciones.remove(inscripcionParaBorrar);
    }
    //funcion para mostrar la lista de inscripciones que cumplen la condicion de que la fecha
    // sea anterior a la fecha de la excursion en el metodo eliminarInscripciones
    public static void mostrarInscripcionesBorrables(List<Excursion> listaExcursiones, List<Inscripcion> listaInscripciones) {
        Date fechaActual = new Date(); //fecha actual
        ArrayList<Inscripcion> listaInscripcionesBorrables = new ArrayList<Inscripcion>(); // nueva lista
        for(Inscripcion inscripcion : listaInscripciones) {
            int idExcursion = inscripcion.getIdExcursion();
            Excursion excursion = obtenerExcursionPorId(idExcursion, listaExcursiones);
            if (fechaActual.before(excursion.getFechaExcursion())) {
                listaInscripcionesBorrables.add(inscripcion);
            }
        }
        System.out.println(listaInscripcionesBorrables);
    }

    public static void showInscripcion(List<Inscripcion> listaInscripciones, List<Socio> listaSocios,List<Excursion> listaExcursiones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción:");
        System.out.println("1. No aplicar filtros");
        System.out.println("2. Aplicar filtro por socio");
        System.out.println("3. Aplicar filtro por fecha");
        System.out.println("4. Aplicar ambos filtros");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                mostrarTodasLasInscripciones(listaInscripciones, listaSocios, listaExcursiones);
                break;
            case 2:
                mostrarInscripcionPorSocio(listaInscripciones, listaSocios, listaExcursiones);
                break;
            case 3:
                mostrarInscripcionPorFecha(listaInscripciones, listaSocios, listaExcursiones);
                break;
            case 4:
                mostrarInscripcionPorSocioYFecha(listaInscripciones, listaSocios, listaExcursiones);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void mostrarTodasLasInscripciones(List<Inscripcion> listaInscripciones, List<Socio> listaSocios, List<Excursion> listaExcursiones) {
        if (listaInscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para mostrar.");
            return;
        } else {
            for (Inscripcion inscripcion : listaInscripciones) {

                System.out.println("Número de socio: " + inscripcion.getIdSocio());

                // Buscar el nombre del socio correspondiente
                Socio socio = obtenerSocioPorId(inscripcion.getIdSocio(), listaSocios);
                if (socio != null) {
                    System.out.println("Nombre del socio: " + socio.getNombre());
                } else {
                    System.out.println("Nombre del socio: No encontrado");
                }

                // Buscar la excursión correspondiente a la inscripción
                Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion(), listaExcursiones);
                if (excursion != null) {
                    // Mostrar fecha de la excursión y descripción
                    System.out.println("Fecha de la excursión: " + excursion.getFechaExcursion());
                    System.out.println("Descripción de la excursión: " + excursion.getDescripcion());

                    ;
                    // Calcular e imprimir el importe con los cargos o descuentos aplicados
                    double importeTotal = calcularImporteTotal(excursion, socio);
                    System.out.println("Importe total: " + importeTotal);
                } else {
                    System.out.println("No se encontró información de la excursión para esta inscripción.");
                }

                System.out.println(); // Separador entre cada inscripción
            }
        }
    }

    //Mostrarporsocio
    public static void mostrarInscripcionPorSocio(List<Inscripcion> listaInscripciones, List<Socio> listaSocios, List<Excursion> listaExcursiones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del socio:");
        int idSocioInscripciones = scanner.nextInt();
        scanner.nextLine();

        if (listaInscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para mostrar.");
            return;
        } else {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (idSocioInscripciones == inscripcion.getIdSocio()) {
                System.out.println("Número de socio: " + inscripcion.getIdSocio());

                // Buscar el nombre del socio correspondiente
                Socio socio = obtenerSocioPorId(inscripcion.getIdSocio(), listaSocios);
                if (socio != null) {
                    System.out.println("Nombre del socio: " + socio.getNombre());
                } else {
                    System.out.println("Nombre del socio: No encontrado");
                }

                // Buscar la excursión correspondiente a la inscripción
                Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion(), listaExcursiones);
                if (excursion != null) {
                    // Mostrar fecha de la excursión y descripción
                    System.out.println("Fecha de la excursión: " + excursion.getFechaExcursion());
                    System.out.println("Descripción de la excursión: " + excursion.getDescripcion());

                    ;
                    // Calcular e imprimir el importe con los cargos o descuentos aplicados
                    double importeTotal = calcularImporteTotal(excursion, socio);
                    System.out.println("Importe total: " + importeTotal);
                } else {
                    System.out.println("No se encontró información de la excursión para esta inscripción.");
                }

                System.out.println(); // Separador entre cada inscripción
            }
        }
        }
    }
    //Mostrarporfechas
    public static void mostrarInscripcionPorFecha(List<Inscripcion> listaInscripciones, List<Socio> listaSocios, List<Excursion> listaExcursiones) {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Solicitamos al usuario las fechas de inicio y fin para aplicar el filtro
            System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
            Date fechaInicio = leerFecha(scanner, dateFormat);

            System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
            Date fechaFin = leerFecha(scanner, dateFormat);

            if (fechaInicio.after(fechaFin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            System.out.println("Inscripciones entre " + dateFormat.format(fechaInicio) + " y " + dateFormat.format(fechaFin) + ":");

            boolean inscripcionesEncontradas = false;
            for (Inscripcion inscripcion : listaInscripciones) {
                Date fechaInscripcion = inscripcion.getFechaInscripcion();
                if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {

                    System.out.println("Número de socio: " + inscripcion.getIdSocio());

                    // Buscar el nombre del socio correspondiente
                    Socio socio = obtenerSocioPorId(inscripcion.getIdSocio(), listaSocios);
                    if (socio != null) {
                        System.out.println("Nombre del socio: " + socio.getNombre());
                    } else {
                        System.out.println("Nombre del socio: No encontrado");
                    }

                    // Buscar la excursión correspondiente a la inscripción
                    Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion(), listaExcursiones);
                    if (excursion != null) {
                        // Mostrar fecha de la excursión y descripción
                        System.out.println("Fecha de la excursión: " + excursion.getFechaExcursion());
                        System.out.println("Descripción de la excursión: " + excursion.getDescripcion());

                        ;
                        // Calcular e imprimir el importe con los cargos o descuentos aplicados
                        double importeTotal = calcularImporteTotal(excursion, socio);
                        System.out.println("Importe total: " + importeTotal);
                    } else {
                        System.out.println("No se encontró información de la excursión para esta inscripción.");
                    }
                    inscripcionesEncontradas = true;
                }
            }
            if (!inscripcionesEncontradas) {
                System.out.println("No se encontraron inscripciones en el rango de fechas especificado.");
            }

    }

    public static void mostrarInscripcionPorSocioYFecha(List<Inscripcion> listaInscripciones, List<Socio> listaSocios, List<Excursion> listaExcursiones){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del socio:");
        int idSocioInscripciones = scanner.nextInt();
        scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Solicitamos al usuario las fechas de inicio y fin para aplicar el filtro
        System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
        Date fechaInicio = leerFecha(scanner, dateFormat);

        System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
        Date fechaFin = leerFecha(scanner, dateFormat);

        if (fechaInicio.after(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
            return;
        }

        System.out.println("Inscripciones del socio número: "+ idSocioInscripciones+", entre " + dateFormat.format(fechaInicio) + " y " + dateFormat.format(fechaFin) + ":");

        boolean inscripcionesEncontradas = false;
        for (Inscripcion inscripcion : listaInscripciones) {
            Date fechaInscripcion = inscripcion.getFechaInscripcion();
            if (fechaInscripcion.after(fechaInicio) && fechaInscripcion.before(fechaFin)) {
                if (idSocioInscripciones == inscripcion.getIdSocio()) {
                    System.out.println("Número de socio: " + inscripcion.getIdSocio());

                    // Buscar el nombre del socio correspondiente
                    Socio socio = obtenerSocioPorId(inscripcion.getIdSocio(), listaSocios);
                    if (socio != null) {
                        System.out.println("Nombre del socio: " + socio.getNombre());
                    } else {
                        System.out.println("Nombre del socio: No encontrado");
                    }

                    // Buscar la excursión correspondiente a la inscripción
                    Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion(), listaExcursiones);
                    if (excursion != null) {
                        // Mostrar fecha de la excursión y descripción
                        System.out.println("Fecha de la excursión: " + excursion.getFechaExcursion());
                        System.out.println("Descripción de la excursión: " + excursion.getDescripcion());

                        ;
                        // Calcular e imprimir el importe con los cargos o descuentos aplicados
                        double importeTotal = calcularImporteTotal(excursion, socio);
                        System.out.println("Importe total: " + importeTotal);
                    } else {
                        System.out.println("No se encontró información de la excursión para esta inscripción.");
                    }
                    inscripcionesEncontradas = true;
                }
            }
        }
        if (!inscripcionesEncontradas) {
            System.out.println("No se encontraron inscripciones en el rango de fechas especificado.");
        }

    }
    //Subfunciones
    private static Excursion obtenerExcursionPorId(int idExcursion, List<Excursion> listaExcursiones) {
        for (Excursion excursion : listaExcursiones) {
            if (excursion.getIdExcursion() == idExcursion) {
                return excursion;
            }
        }
        return null; // Retorna null si no se encuentra la excursión con el ID dado
    }
    private static Inscripcion obtenerInscripcionPorId(int idInscripcion, List<Inscripcion> listaInscripciones) {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdInscripcion() == idInscripcion) {
                return inscripcion;
            }
        }
        return null; // Retorna null si no se encuentra la excursión con el ID dado
    }
    private static Socio obtenerSocioPorId(int idSocio, List<Socio> listaSocios) {
        for (Socio socio : listaSocios) {
            if (socio.getIdSocio() == idSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra el socio con el ID dado
    }

    private static double calcularImporteTotal(Excursion excursion, Socio socio) {
        double precioInscripcion = excursion.getPrecioInscripcion();

        // Aplicar descuento según el tipo de socio
        switch (socio.getTipoSocio()) {
            case "modelo.Estandar":
                // Para socios estándar, se suma el precio de la inscripción con el precio del seguro
                Estandar estandar = (Estandar) socio;
                precioInscripcion += estandar.getSeguroContratado().getPrecio();
                break;
            case "modelo.Infantil":
                // Para socios infantiles, no hay descuento ni otros cargos
                break;
            case "modelo.Federado":
                // Para socios federados, se aplica un descuento del 10%
                precioInscripcion *= 0.9;
                break;
            default:
                System.out.println("Tipo de socio no reconocido.");
        }

        return precioInscripcion;
    }

}
