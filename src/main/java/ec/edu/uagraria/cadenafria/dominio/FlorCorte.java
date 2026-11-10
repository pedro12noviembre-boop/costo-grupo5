package ec.edu.uagraria.cadenafria.dominio;

public record FlorCorte(String codigo, String variedad, int tallos, double temperaturaOptimaC)
        implements Producto {
}
