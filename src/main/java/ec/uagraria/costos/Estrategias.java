package ec.uagraria.costos;

public final class Estrategias {

    public static final double VALOR_JORNAL = 18.50;
    public static final double IVA = 0.15;

    private Estrategias() {
    }

    public static EstrategiaCosto costoDirecto() {
        return lote -> lote.costoInsumos();
    }

    public static EstrategiaCosto costoConManoDeObra() {
        return lote -> lote.costoInsumos()
                + lote.jornalesPorHectarea() * lote.hectareas() * VALOR_JORNAL;
    }

    public static EstrategiaCosto costoPorHectarea() {
        return lote -> costoConManoDeObra().calcular(lote) / lote.hectareas();
    }

    public static EstrategiaCosto costoConSubsidioFertilizante(double porcentaje) {
        return lote -> {
            double descuento = lote.insumos().stream()
                    .filter(i -> i.nombre().toLowerCase().contains("urea"))
                    .mapToDouble(Insumo::costo)
                    .sum() * porcentaje;

            return costoConManoDeObra().calcular(lote) - descuento;
        };
    }

    public static EstrategiaCosto conIva(EstrategiaCosto base) {
        return lote -> base.calcular(lote) * (1 + IVA);
    }
}