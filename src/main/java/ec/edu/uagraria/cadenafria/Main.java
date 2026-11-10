package ec.edu.uagraria.cadenafria;

import ec.edu.uagraria.cadenafria.alertas.*;
import ec.edu.uagraria.cadenafria.dominio.*;
import ec.edu.uagraria.cadenafria.fabrica.DatosProducto;
import ec.edu.uagraria.cadenafria.fabrica.FabricaProductos;
import ec.edu.uagraria.cadenafria.reglas.Estrategias;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Fábrica de productos
        List<Producto> productos = List.of(
                FabricaProductos.crear(TipoProducto.FLOR_CORTE, new DatosProducto("Rosa Explorer", 500, 2.0)),
                FabricaProductos.crear(TipoProducto.LOTE_PESCADO, new DatosProducto("Atún", 120.5, 0.0)),
                FabricaProductos.crear(TipoProducto.INSUMO_FRIO, new DatosProducto("Hielo en escamas", 40, 1.5)));
        productos.forEach(System.out::println);
        System.out.println("Costo directo: " + Estrategias.costoDirecto().calcular(productos));
        System.out.println("Costo con IVA: " + Estrategias.conIva(Estrategias.costoDirecto()).calcular(productos));

        // Motor de alertas con tres reglas y observadores
        MotorAlertas motor = new MotorAlertas();
        motor.agregarRegla(Reglas.temperaturaFueraRango());
        motor.agregarRegla(Reglas.rupturaProlongada());
        motor.agregarRegla(Reglas.puertaAbierta());

        List<Alerta> bitacora = new ArrayList<>();
        motor.suscribir(FabricaObservadores.crear(TipoObservador.CONSOLA));
        motor.suscribir(FabricaObservadores.crear(TipoObservador.SMS));
        motor.suscribir(bitacora::add);

        int n = motor.procesar(new LecturaFrio("CAM-01", 9.5, 45, 12));
        System.out.println("Alertas publicadas: " + n + " | En bitácora: " + bitacora.size());
    }
}
