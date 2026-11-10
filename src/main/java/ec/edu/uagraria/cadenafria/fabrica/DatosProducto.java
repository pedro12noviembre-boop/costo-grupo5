package ec.edu.uagraria.cadenafria.fabrica;

/**
 * Datos de entrada de la fábrica.
 *
 * @param nombre   variedad (flor), especie (pescado) o nombre (insumo)
 * @param cantidad tallos (flor), kilos (pescado) o unidades (insumo)
 * @param valor    temperatura óptima en °C (flor, pescado) o precio unitario (insumo)
 */
public record DatosProducto(String nombre, double cantidad, double valor) {
}
