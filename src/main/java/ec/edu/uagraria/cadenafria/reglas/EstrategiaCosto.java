package ec.edu.uagraria.cadenafria.reglas;

import ec.edu.uagraria.cadenafria.dominio.Producto;

import java.util.List;

/** Interfaz funcional del Taller 3: cada estrategia calcula un costo. */
@FunctionalInterface
public interface EstrategiaCosto {
    double calcular(List<Producto> productos);
}
