package ec.uagraria.costos;

import java.util.Optional;

public class CalculadoraCostos {

    private EstrategiaCosto estrategia;

    public CalculadoraCostos() {
    }

    public CalculadoraCostos(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    public Optional<Double> calcular(Lote lote) {
        if (estrategia == null || lote == null) {
            return Optional.empty();
        }

        return Optional.of(estrategia.calcular(lote));
    }

    public static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}