package ec.uagraria.costos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraCostosTest {

    private Lote crearLoteBanano() {
        return new Lote(
                "L-01",
                new Banano("Cavendish"),
                4.0,
                List.of(
                        new Insumo("Urea 50 kg", 12, "saco", 32.50),
                        new Insumo("Fungicida", 8, "litro", 21.75),
                        new Insumo("Semilla certificada", 1, "lote", 180.00)
                )
        );
    }

    @Test
    void debeRetornarVacioSinEstrategia() {
        CalculadoraCostos calc = new CalculadoraCostos();

        assertTrue(calc.calcular(crearLoteBanano()).isEmpty());
    }

    @Test
    void debeCalcularCostoDirecto() {
        CalculadoraCostos calc =
                new CalculadoraCostos(Estrategias.costoDirecto());

        assertEquals(744.00, calc.calcular(crearLoteBanano()).orElseThrow(), 0.01);
    }

    @Test
    void debeCalcularCostoConManoDeObra() {
        CalculadoraCostos calc =
                new CalculadoraCostos(Estrategias.costoConManoDeObra());

        assertEquals(2076.00, calc.calcular(crearLoteBanano()).orElseThrow(), 0.01);
    }

    @Test
    void debeCalcularCostoPorHectarea() {
        CalculadoraCostos calc =
                new CalculadoraCostos(Estrategias.costoPorHectarea());

        assertEquals(519.00, calc.calcular(crearLoteBanano()).orElseThrow(), 0.01);
    }

    @Test
    void debeAplicarSubsidio() {
        CalculadoraCostos calc =
                new CalculadoraCostos(
                        Estrategias.costoConSubsidioFertilizante(0.20));

        assertEquals(1998.00, calc.calcular(crearLoteBanano()).orElseThrow(), 0.01);
    }

    @Test
    void debeAplicarIva() {
        CalculadoraCostos calc =
                new CalculadoraCostos(
                        Estrategias.conIva(Estrategias.costoConManoDeObra()));

        assertEquals(2387.40, calc.calcular(crearLoteBanano()).orElseThrow(), 0.01);
    }
}