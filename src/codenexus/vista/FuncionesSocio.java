package codenexus.vista;

import codenexus.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class FuncionesSocio {

    private List<Socio> listaSocios;
    private List<Federacion> listaFederacion;

    public FuncionesSocio() {
        this.listaSocios = new ArrayList<>();
        this.listaFederacion = new ArrayList<>();
    }


    public Socio buscarSocioId(int idSocio) {
        for (Socio socio : listaSocios) {
            if (socio.getIdSocio() == idSocio) {
                return socio;
            }
        }
        return null; // No encuentra al Socio
    }

    public Federacion buscarFederacionnombre(String nombreFederacion) {
        for (Federacion federacion : listaFederacion) {
            if (Objects.equals(federacion.getNombreFederacion(), nombreFederacion)) {
                System.out.println("La federacion: " + nombreFederacion + " existe");
                return federacion;
            }
        }
        System.out.println("No existe la federacion " + nombreFederacion);
        return null; // No encuentra la Federacion
    }



    public Socio crear() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Que tipo de Socio va a ser: 1-Estandar, 2-Federado o 3-Infantil");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                Estandar socioEstandar = new Estandar();
                System.out.println("Inserta el Nombre del Socio");
                socioEstandar.setNombre(scanner.next());
                System.out.println("Inserta el ID del Socio");
                socioEstandar.setIdSocio(scanner.nextInt());
                System.out.println("Inserta el NIF del socio");
                socioEstandar.setNif(scanner.next());
                return socioEstandar;

            case 2:
                Federado socioFederado = new Federado();
                Federacion federacion = new Federacion();
                System.out.println("Inserta el Nombre del Socio");
                socioFederado.setNombre(scanner.next());
                System.out.println("Inserta el ID del Socio");
                socioFederado.setIdSocio(scanner.nextInt());
                System.out.println("Inserta el NIF del socio");
                socioFederado.setNif(scanner.next());
                String nombreFederacion;
                // hasta que no inserte la federacion y se compruebe si existe o no, no se puede avanzar
                do {
                    System.out.println("Inserta El nombre de la Federacion a la cual pertenece");
                    nombreFederacion = scanner.next();
                    Federacion federacionEncontrada = buscarFederacionnombre(nombreFederacion);
                    if (federacionEncontrada != null) {
                        socioFederado.setFederacion(federacionEncontrada);
                    }
                } while (federacion.getNombreFederacion() == null);

                return socioFederado;

            case 3:
                Infantil socioInfantil = new Infantil();
                Socio socio = new Socio();
                System.out.println("Inserta el Nombre del Socio");
                socioInfantil.setNombre(scanner.next());
                System.out.println("Inserta el ID del Socio");
                socioInfantil.setIdSocio(scanner.nextInt());
                System.out.println("Inserta la Id del Socio que lo Tutoriza");
                socioInfantil.setIdTutor(scanner.nextInt());
                return socioInfantil;
        }

        return null;
    }

}
