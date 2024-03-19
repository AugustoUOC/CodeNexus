package codenexus.modelo;

public class Seguro {
    private boolean tipo;
    private double precio;

    // Constructor vacío
    public Seguro() {
    }

    // Constructor con todos los atributos
    public Seguro(boolean tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    // Getters y setters
    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString para imprimir los detalles del seguro
    @Override
    public String toString() {
        return "codenexus.modelo.Seguro{" +
                "tipo=" + (tipo ? "Completo" : "Básico") +
                ", precio=" + precio +
                '}';
    }
}
