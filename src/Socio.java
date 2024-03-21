import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

public abstract class Socio {
    private int idSocio;
    private String nombre;
    private String tipoSocio;


   public static int contadorSocios = 0;
    public static int contadorFederaciones = 0;


    public static List<Socio> listaSocios = new ArrayList<>();
    // Constructor vacío
    public Socio() {
    }

    // Constructor con todos los atributos
    public Socio(int idSocio, String nombre, String tipoSocio) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.tipoSocio = tipoSocio;
    }
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

    public String getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(String tipoSocio) {
        this.tipoSocio = tipoSocio;
    }


    // Otros métodos
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

        System.out.println("Seleccione el tipo de socio (Estandar / Federado / Infantil):");
        String tipoSocio = scanner.nextLine();

        switch (tipoSocio.toLowerCase()) {
            case "estandar":
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
            case "federado":
                System.out.println("Ingrese el NIF del socio:");
                String nifFederado = scanner.nextLine();
                System.out.println("Ingrese el nombre de la federación:");
                String nombreFederacion = scanner.nextLine();
                Federacion federacion = new Federacion(++contadorFederaciones, nombreFederacion);
                nuevoSocio = new Federado(++contadorSocios, nombre, federacion, nifFederado);
                break;
            case "infantil":
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
                System.out.println("Tipo de socio no válido.");
                return;
        }

        listaSocios.add(nuevoSocio);
        System.out.println("Socio agregado correctamente.");
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

    public void showFactura() {
        // Implementación para mostrar la factura del socio
    }

    public double calcularCuota() {
        // Implementación para calcular la cuota del socio
        return 0.0; // Retorna un valor temporal, debes reemplazarlo con la lógica real
    }

    // Método toString para imprimir los detalles del socio
    @Override
    public String toString() {
        return "Socio{" +
                "idSocio=" + idSocio +
                ", nombre='" + nombre + '\'' +
                ", tipoSocio='" + tipoSocio + '\'' +

                '}';
    }
}
