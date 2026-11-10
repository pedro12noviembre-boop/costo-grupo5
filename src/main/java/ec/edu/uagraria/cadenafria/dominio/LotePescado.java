package ec.edu.uagraria.cadenafria.dominio;

public record LotePescado(String codigo, String especie, double kilos, double temperaturaOptimaC)
        implements Producto {
}
