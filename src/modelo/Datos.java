package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Datos {


    public Datos() {
    }
    
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
    public static void mostrarExcursionesPorFechas() {
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
    public static void deleteSocio(List<Socio> socios) {
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


        socios.remove(socioAEliminar);
        System.out.println("El socio " + socioAEliminar.getNombre() + " ha sido eliminado correctamente.");

    }
    public static void addSocio() {
        Socio nuevoSocio = null; // Inicialización por defecto
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del socio:");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el tipo de socio:");
        System.out.println("1. Estandar");
        System.out.println("2. Federado");
        System.out.println("3. Infantil");
        int opcionTipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcionTipo) {
            case 1:
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
            case 2:
                System.out.println("Ingrese el NIF del socio:");
                String nifFederado = scanner.nextLine();
                System.out.println("Ingrese el nombre de la federación:");
                String nombreFederacion = scanner.nextLine();
                Federacion federacion = new Federacion(++contadorFederaciones, nombreFederacion);
                nuevoSocio = new Federado(++contadorSocios, nombre, federacion, nifFederado);
                break;
            case 3:
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
                        System.out.println("Socio infantil agregado correctamente.");
                    } else {
                        System.out.println("Se ha cancelado la operación. El socio infantil no se pudo agregar.");
                    }
                }
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        listaSocios.add(nuevoSocio);
        System.out.println("Socio agregado correctamente.");
    }

    public static void modificarSeguro(Seguro nuevoSeguro) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del socio estándar para modificar su seguro:");
        int idSocio = scanner.nextInt();
        scanner.nextLine();

        Socio socio = obtenerSocioPorId(idSocio);
        if (socio == null) {
            System.out.println("No se encontró ningún socio con el ID especificado.");
            return;
        }

        // Verificar si el socio es de tipo estándar
        if (socio instanceof Estandar) {
            Estandar socioEstandar = (Estandar) socio;
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
                System.out.println("2. Federado");
                System.out.println("3. Infantil");
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
    public static double mostrarFactura(Socio socio) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        for(Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdSocio() == socio.getIdSocio()) {
                inscripciones.add(inscripcion);
            }
        }
        double coste = 0;
        for (Inscripcion inscripcion : inscripciones) {
            for (Excursion excursion : listaExcursiones) {
                if (inscripcion.getIdExcursion() == excursion.getIdExcursion()) {
                    coste += calcularCosteExcursion(socio, excursion);
                }
            }
        }
        return coste;
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
            }
            if (!numeroSocioValido) {
                System.out.println("El número de socio ingresado no es válido. La inscripción no pudo ser realizada.");
                return;
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
    }
    public static void eliminarInscripcion() {
        Scanner scanner = new Scanner(System.in);
        mostrarInscripcionesBorrables();
        System.out.println("Elige al ID de la Inscripcion que quieres Eliminar");
        int idParaBorrar = scanner.nextInt();
        Inscripcion inscripcionParaBorrar = obtenerInscripcionPorId(idParaBorrar);
        listaInscripciones.remove(inscripcionParaBorrar);
    }
    public static void showInscripcion() {
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
                mostrarTodasLasInscripciones();
                break;
            case 2:
                mostrarInscripcionPorSocio();
                break;
            case 3:
                mostrarInscripcionPorFecha();
                break;
            case 4:
                mostrarInscripcionesPorTipoSocioYFecha();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }


    private static void mostrarTodasLasInscripciones() {
        if (listaInscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para mostrar.");
            return;
        }
        for (Inscripcion inscripcion : listaInscripciones) {
            System.out.println("Número de socio: " + inscripcion.getIdSocio());

            // Buscar el nombre del socio correspondiente
            Socio socio = obtenerSocioPorId(inscripcion.getIdSocio());
            if (socio != null) {
                System.out.println("Nombre del socio: " + socio.getNombre());
            } else {
                System.out.println("Nombre del socio: No encontrado");
            }

            System.out.println("Fecha de inscripción: " + inscripcion.getFechaInscripcion());

            // Buscar la excursión correspondiente a la inscripción
            Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion());
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

    //Mostrarporsocio

    public static void mostrarInscripcionPorSocio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, elija el tipo de socio para filtrar:");
        System.out.println("1. Estandar");
        System.out.println("2. Federado");
        System.out.println("3. Infantil");
        String tipoSocio = scanner.nextLine();

        // Convertimos la entrada del usuario a los tipos correspondientes
        String tipoElegido;
        switch (tipoSocio) {
            case "1":
                tipoElegido = "Estandar";
                break;
            case "2":
                tipoElegido = "Federado";
                break;
            case "3":
                tipoElegido = "Infantil";
                break;
            default:
                System.out.println("Tipo de socio no válido. Por favor, elija una opción entre 1 y 3.");
                return; //Si el tipo de socio no es valido salimos de la funcion
        }

        mostrarSociosConInscripcionesPorTipo(tipoElegido);

    }

    public static void mostrarSociosConInscripcionesPorTipo(String tipoSocio) {
        List<Socio> sociosConInscripciones = new ArrayList<>();

        // Recorremos todas las inscripciones para buscar socios del tipo solicitado
        for (Inscripcion inscripcion : listaInscripciones) {
            Socio socio = obtenerSocioPorId(inscripcion.getIdSocio());

            // Verificamos que el socio exista, sea del tipo solicitado y no haya sido incluido previamente
            if (socio != null && tipoSocio.equals(socio.tipoSocio) && !contieneSocio(sociosConInscripciones, socio)) {
                // Agregamos el socio a la lista
                sociosConInscripciones.add(socio);
            }
        }

        // Verificamos si hemos encontrado socios del tipo solicitado con inscripciones
        if (!sociosConInscripciones.isEmpty()) {
            System.out.println("Socios de tipo " + tipoSocio + " con inscripciones registradas:");
            for (Socio socio : sociosConInscripciones) {
                System.out.println(socio.toString());
            }
        } else {
            System.out.println("No hay socios del tipo " + tipoSocio + " con inscripciones registradas.");
        }
    }

    private static boolean contieneSocio(List<Socio> lista, Socio socio) {
        for (Socio s : lista) {
            if (s.getIdSocio() == socio.getIdSocio()) {
                return true; // El socio ya está en la lista
            }
        }
        return false; // El socio no está en la lista
    }


    //Mostrarporfechas
    public static void mostrarInscripcionPorFecha() {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Solicitamos al usuario las fechas de inicio y fin para aplicar el filtro
            System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
            Date fechaInicio = sdf.parse(scanner.nextLine());

            System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
            Date fechaFin = sdf.parse(scanner.nextLine());

            // Filtrar y mostrar inscripciones
            List<Inscripcion> inscripcionesFiltradas = filtrarInscripcionesPorFecha(fechaInicio, fechaFin);
            if (inscripcionesFiltradas.isEmpty()) {
                System.out.println("No hay inscripciones disponibles en el rango de fechas especificado.");
            } else {
                System.out.println("Inscripciones disponibles entre las fechas seleccionadas:");
                for (Inscripcion inscripcion : inscripcionesFiltradas) {
                    System.out.println("Inscripción ID: " + inscripcion.getIdInscripcion() + ", Fecha: " + sdf.format(inscripcion.getFechaInscripcion()));
                }
            }
        } catch (ParseException e) {
            System.out.println("Formato de fecha no válido. Por favor, ingrese la fecha en formato dd/MM/yyyy.");
        }

    }

    private static List<Inscripcion> filtrarInscripcionesPorFecha(Date fechaInicio, Date fechaFin) {
        List<Inscripcion> filtradas = new ArrayList<>();
        for (Inscripcion inscripcion : listaInscripciones) {
            if (!inscripcion.getFechaInscripcion().before(fechaInicio) && !inscripcion.getFechaInscripcion().after(fechaFin)) {
                filtradas.add(inscripcion);
            }
        }
        return filtradas;
    }

    //Mostrarporsocioyfechas

    public static void mostrarInscripcionesPorTipoSocioYFecha() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Solicitar y procesar el tipo de socio
        System.out.println("Por favor, elija el tipo de socio para filtrar:");
        System.out.println("1. Estandar");
        System.out.println("2. Federado");
        System.out.println("3. Infantil");
        String input = scanner.nextLine();
        String tipoSocio = "";
        switch (input) {
            case "1":
                tipoSocio = "Estandar";
                break;
            case "2":
                tipoSocio = "Federado";
                break;
            case "3":
                tipoSocio = "Infantil";
                break;
            default:
                System.out.println("Opción no válida.");

        }

        // Solicitar y procesar las fechas
        System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy):");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.println("Ingrese la fecha de fin (dd/MM/yyyy):");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine(), formatter);

        mostrarSociosConInscripcionesPorTipoYFechas(tipoSocio, fechaInicio, fechaFin);


    }

    private static void mostrarSociosConInscripcionesPorTipoYFechas(String tipoSocio, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Socio> sociosFiltrados = new ArrayList<>();

        for (Inscripcion inscripcion : listaInscripciones) {
            Socio socio = obtenerSocioPorId(inscripcion.getIdSocio());
            Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion());
            LocalDate fechaExcursion = excursion.getFechaExcursion().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (socio != null && tipoSocio.equalsIgnoreCase(socio.tipoSocio) &&
                    !sociosFiltrados.contains(socio) &&
                    (fechaExcursion.isEqual(fechaInicio) || fechaExcursion.isAfter(fechaInicio)) &&
                    (fechaExcursion.isEqual(fechaFin) || fechaExcursion.isBefore(fechaFin))) {
                sociosFiltrados.add(socio);
            }
        }

        // Mostrar los socios filtrados
        if (!sociosFiltrados.isEmpty()) {
            System.out.println("Socios de tipo " + tipoSocio + " con inscripciones entre las fechas dadas:");
            for (Socio socio : sociosFiltrados) {
                System.out.println(socio.toString());
            }
        } else {
            System.out.println("No se encontraron socios del tipo " + tipoSocio + " con inscripciones en el rango de fechas dado.");
        }
    }

    //Subfunciones

    //funcion para mostrar la lista de inscripciones que cumplen la condicion de que la fecha
    // sea anterior a la fecha de la excursion en el metodo eliminarInscripciones
    public static void mostrarInscripcionesBorrables() {
        Date fechaActual = new Date(); //fecha actual
        ArrayList<Inscripcion> listaInscripcionesBorrables = new ArrayList<Inscripcion>(); // nueva lista
        for(Inscripcion inscripcion : listaInscripciones) {
            int idExcursion = inscripcion.getIdExcursion();
            Excursion excursion = obtenerExcursionPorId(idExcursion);
            if (fechaActual.before(excursion.getFechaExcursion())) {
                listaInscripcionesBorrables.add(inscripcion);
            }
        }
        System.out.println(listaInscripcionesBorrables);
    }

    private static Inscripcion obtenerInscripcionPorId(int id) {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdInscripcion() == id) {
                return  inscripcion;
            }
        }
        System.out.println("No se encuentra la Inscripcion especificada");
        return null; // Retorna null si no encuentra la inscripcion
    }

    private static Excursion obtenerExcursionPorId(int idExcursion) {
        for (Excursion excursion : listaExcursiones) {
            if (excursion.getIdExcursion() == idExcursion) {
                return excursion;
            }
        }
        return null; // Retorna null si no se encuentra la excursión con el ID dado
    }

    private static double calcularImporteTotal(Excursion excursion, Socio socio) {
        double precioInscripcion = excursion.getPrecioInscripcion();

        // Aplicar descuento según el tipo de socio
        switch (socio.getTipoSocio()) {
            case "Estandar":
                // Para socios estándar, se suma el precio de la inscripción con el precio del seguro
                Estandar estandar = (Estandar) socio;
                precioInscripcion += estandar.getSeguroContratado().getPrecio();
                break;
            case "Infantil":
                // Para socios infantiles, no hay descuento ni otros cargos
                break;
            case "Federado":
                // Para socios federados, se aplica un descuento del 10%
                precioInscripcion *= 0.9;
                break;
            default:
                System.out.println("Tipo de socio no reconocido.");
        }

        return precioInscripcion;
    }
    public static Socio obtenerSocioPorId(int idSocio) {
        for (Socio socio : listaSocios) {
            if (socio.getIdSocio() == idSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra el socio con el ID dado
    }

    public static double obtenerTotalPrecioInscripcionesPorSocio(int idSocio) {
        double totalPrecio = 0.0;
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdSocio() == idSocio) {
               Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion());
                if (excursion != null) {
                    totalPrecio += excursion.getPrecioInscripcion();
                }
            }
        }
        return totalPrecio;
    }

    public static boolean socioParticipoEnExcursion(Socio socio, Excursion excursion) {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getIdSocio() == socio.getIdSocio() && inscripcion.getIdExcursion() == excursion.getIdExcursion()) {
                return true; // El socio participó en esta excursión
            }
        }
        return false; // El socio no participó en esta excursión
    }

}
