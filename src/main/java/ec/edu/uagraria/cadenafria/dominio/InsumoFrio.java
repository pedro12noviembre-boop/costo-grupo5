package ec.edu.uagraria.cadenafria.dominio;

public record InsumoFrio(String codigo, String nombre, double cantidad, double precioUnitario)
        implements Producto {

    public double costo() {
        return cantidad * precioUnitario;
    }
}
