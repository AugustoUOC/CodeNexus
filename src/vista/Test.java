package vista;

import modelo.*;

public class Test {
    public static void main(String[] args) {

        Estandar estandar = new Estandar();
        estandar.setNombre("Pepe");
        Federado federado = new Federado();
        federado.setNombre("Paco");
        Infantil infantil = new Infantil();
        infantil.setNombre("manu");

        System.out.println(Datos.calcularCuota(estandar));
        System.out.println(Datos.calcularCuota(federado));
        System.out.println(Datos.calcularCuota(infantil));



    }
}
