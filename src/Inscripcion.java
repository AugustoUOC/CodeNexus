import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Inscripcion {
    private int idInscripcion;
    private int idSocio;
    private int idExcursion;
    private Date fechaInscripcion;

    public static int contadorInscripciones = 0;
    public static List<Inscripcion> listaInscripciones = new ArrayList<>();

    // Constructor vacío
    public Inscripcion() {
    }

    // Constructor con todos los atributos
    public Inscripcion(int idInscripcion, int idSocio, int idExcursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idExcursion = idExcursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y setters
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(int idExcursion) {
        this.idExcursion = idExcursion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Método toString para imprimir los detalles de la inscripción
    @Override
    public String toString() {
        return "Inscripcion{" +
                "idInscripcion=" + idInscripcion +
                ", idSocio=" + idSocio +
                ", idExcursion='" + idExcursion + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }

    // Métodos adicionales
    public static void addInscripcion(List<Socio> listaSocios, List<Excursion> excursiones, Date fechaInscripcion) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del socio en el que desea inscribirse (o pulse 0 para un nuevo socio): ");
        int numeroSocioElegido = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Socio socioElegido = null;
        if (numeroSocioElegido == 0) {
            // Agregar un nuevo socio
            Socio.addSocio();
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

    public static void showInscripcion(List<Inscripcion> listaInscripciones, List<Socio> listaSocios) {
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
                mostrarTodasLasInscripciones(listaInscripciones, listaSocios);
                break;
            case 2:
                //mostrarPorSocio(listaInscripciones, listaSocios);
                break;
            case 3:
                //mostrarPorFechas(listaInscripciones);
                break;
            case 4:
                //mostrarPorSocioYFechas(listaInscripciones, listaSocios);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }


    public void deleteInscripcion() {
        // Lógica para eliminar una inscripción
    }

    private static void mostrarTodasLasInscripciones(List<Inscripcion> listaInscripciones, List<Socio> listaSocios) {
        if (listaInscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para mostrar.");
            return;
        }

        for (Inscripcion inscripcion : listaInscripciones) {
            System.out.println("Número de socio: " + inscripcion.getIdSocio());

            // Buscar el nombre del socio correspondiente
            Socio socio = obtenerSocioPorId(inscripcion.getIdSocio(), listaSocios);
            if (socio != null) {
                System.out.println("Nombre del socio: " + socio.getNombre());
            } else {
                System.out.println("Nombre del socio: No encontrado");
            }

            System.out.println("Fecha de inscripción: " + inscripcion.getFechaInscripcion());

            // Buscar la excursión correspondiente a la inscripción
            Excursion excursion = obtenerExcursionPorId(inscripcion.getIdExcursion(), Excursion.listaExcursiones);
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
    private static Excursion obtenerExcursionPorId(int idExcursion, List<Excursion> listaExcursiones) {
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
    private static Socio obtenerSocioPorId(int idSocio, List<Socio> listaSocios) {
        for (Socio socio : listaSocios) {
            if (socio.getIdSocio() == idSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra el socio con el ID dado
    }
}

