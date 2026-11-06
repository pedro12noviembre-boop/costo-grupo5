
package ec.uagraria.costos;

@FunctionalInterface
public interface EstrategiaCosto {

    // Método que calcula el costo de un lote
    double calcular(Lote lote);

    // Descripción general de la estrategia
    default String descripcion() {
        return "Estrategia de costo";
    }
}
