package ec.edu.uagraria.cadenafria.reglas;

import ec.edu.uagraria.cadenafria.dominio.InsumoFrio;

/** Estrategias de costo (Taller 3) como expresiones lambda. */
public final class Estrategias {

    public static final double IVA = 0.15;

    private Estrategias() { }

    /** Suma el costo de todos los insumos de frío. */
    public static EstrategiaCosto costoDirecto() {
        return productos -> productos.stream()
                .filter(p -> p instanceof InsumoFrio)
                .mapToDouble(p -> ((InsumoFrio) p).costo())
                .sum();
    }

    /** Decora otra estrategia sumándole el IVA del 15 %. */
    public static EstrategiaCosto conIva(EstrategiaCosto base) {
        return productos -> base.calcular(productos) * (1 + IVA);
    }
}
