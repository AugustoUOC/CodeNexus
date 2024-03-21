import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Excursion {
    private int idExcursion;
    private String descripcion;
    private Date fechaExcursion;
    private int duracionDias;
    private double precioInscripcion;

    public static int contadorExcursiones = 0;
    public static List<Excursion> listaExcursiones = new ArrayList<>();

    // Constructor vacío
    public Excursion() {
    }

    // Constructor con todos los atributos
    public Excursion(int idExcursion, String descripcion, Date fechaExcursion, int duracionDias, double precioInscripcion) {
        this.idExcursion = idExcursion;
        this.descripcion = descripcion;
        this.fechaExcursion = fechaExcursion;
        this.duracionDias = duracionDias;
        this.precioInscripcion = precioInscripcion;
    }

    // Getters y setters
    public int getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(int idExcursion) {
        this.idExcursion = idExcursion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaExcursion() {
        return fechaExcursion;
    }

    public void setFechaExcursion(Date fechaExcursion) {
        this.fechaExcursion = fechaExcursion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    // Método toString para imprimir los detalles de la excursión
    @Override
    public String toString() {
        return "Excursion{" +
                "idExcursion='" + idExcursion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaExcursion=" + fechaExcursion +
                ", duracionDias=" + duracionDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
    }

    // Métodos adicionales
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
}