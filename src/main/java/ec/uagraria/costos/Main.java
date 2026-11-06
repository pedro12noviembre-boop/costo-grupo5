package ec.uagraria.costos;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Creamos un lote de cultivo de banano
        Lote lote = new Lote(
                "L-01",
                new Banano("Cavendish"),
                4.0,
                List.of(
                        new Insumo("Urea 50 kg", 12, "saco", 32.50),
                        new Insumo("Fungicida", 8, "litro", 21.75),
                        new Insumo("Semilla certificada", 1, "lote", 180.00)
                )
        );

        // Creamos la calculadora
        CalculadoraCostos calc = new CalculadoraCostos();

        // Probamos sin seleccionar una estrategia
        System.out.println("Sin estrategia: " + calc.calcular(lote));

        // Calculamos el costo directo
        calc.setEstrategia(Estrategias.costoDirecto());
        System.out.println("Costo directo: $" +
                CalculadoraCostos.redondear(calc.calcular(lote).orElseThrow()));

        // Calculamos el costo con mano de obra
        calc.setEstrategia(Estrategias.costoConManoDeObra());
        System.out.println("Costo con mano de obra: $" +
                CalculadoraCostos.redondear(calc.calcular(lote).orElseThrow()));

        // Calculamos el costo por hectárea
        calc.setEstrategia(Estrategias.costoPorHectarea());
        System.out.println("Costo por hectárea: $" +
                CalculadoraCostos.redondear(calc.calcular(lote).orElseThrow()));

        // Aplicamos un subsidio del 20% a la urea
        calc.setEstrategia(Estrategias.costoConSubsidioFertilizante(0.20));
        System.out.println("Con subsidio: $" +
                CalculadoraCostos.redondear(calc.calcular(lote).orElseThrow()));

        // Aplicamos el IVA del 15%
        calc.setEstrategia(
                Estrategias.conIva(Estrategias.costoConManoDeObra())
        );
        System.out.println("Con IVA: $" +
                CalculadoraCostos.redondear(calc.calcular(lote).orElseThrow()));
    }
}