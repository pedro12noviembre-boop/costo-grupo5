package ec.edu.uagraria.cadenafria.servicio;

/** Taller 7: una linea del inventario del proyecto (record inmutable). */
public record ItemInventario(String codigo, String tipo, String descripcion, double cantidad) {
    public ItemInventario {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
    }
}
