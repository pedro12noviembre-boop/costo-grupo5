package ec.edu.uagraria.cadenafria.fabrica;

import ec.edu.uagraria.cadenafria.dominio.*;

import java.util.concurrent.atomic.AtomicInteger;

/** Factory: único lugar donde se hace new de los records del dominio. */
public final class FabricaProductos {

    private static final AtomicInteger SECUENCIA = new AtomicInteger();

    private FabricaProductos() { }

    public static Producto crear(TipoProducto tipo, DatosProducto d) {
        // Switch exhaustivo sobre el enum: sin default, el compilador exige los tres casos.
        return switch (tipo) {
            case FLOR_CORTE -> new FlorCorte(
                    Trazable.generarCodigo("FLO", SECUENCIA.incrementAndGet()),
                    d.nombre(), (int) d.cantidad(), d.valor());
            case LOTE_PESCADO -> new LotePescado(
                    Trazable.generarCodigo("PES", SECUENCIA.incrementAndGet()),
                    d.nombre(), d.cantidad(), d.valor());
            case INSUMO_FRIO -> new InsumoFrio(
                    Trazable.generarCodigo("INS", SECUENCIA.incrementAndGet()),
                    d.nombre(), d.cantidad(), d.valor());
        };
    }
}
