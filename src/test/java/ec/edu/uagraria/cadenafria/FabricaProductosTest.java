package ec.edu.uagraria.cadenafria;

import ec.edu.uagraria.cadenafria.dominio.*;
import ec.edu.uagraria.cadenafria.fabrica.DatosProducto;
import ec.edu.uagraria.cadenafria.fabrica.FabricaProductos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricaProductosTest {

    @Test
    @DisplayName("La fábrica crea una FlorCorte con código FLO-")
    void creaFlorCorte() {
        Producto p = FabricaProductos.crear(TipoProducto.FLOR_CORTE, new DatosProducto("Rosa", 500, 2.0));
        FlorCorte f = assertInstanceOf(FlorCorte.class, p);
        assertTrue(f.codigo().startsWith("FLO-"));
        assertEquals(500, f.tallos());
        assertEquals(2.0, f.temperaturaOptimaC());
    }

    @Test
    @DisplayName("La fábrica crea un LotePescado con código PES-")
    void creaLotePescado() {
        Producto p = FabricaProductos.crear(TipoProducto.LOTE_PESCADO, new DatosProducto("Atún", 120.5, 0.0));
        LotePescado l = assertInstanceOf(LotePescado.class, p);
        assertTrue(l.codigo().startsWith("PES-"));
        assertEquals(120.5, l.kilos());
    }

    @Test
    @DisplayName("La fábrica crea un InsumoFrio y calcula su costo")
    void creaInsumoFrio() {
        Producto p = FabricaProductos.crear(TipoProducto.INSUMO_FRIO, new DatosProducto("Hielo", 40, 1.5));
        InsumoFrio i = assertInstanceOf(InsumoFrio.class, p);
        assertTrue(i.codigo().startsWith("INS-"));
        assertEquals(60.0, i.costo(), 0.001);
    }

    @Test
    @DisplayName("Los códigos generados son únicos")
    void codigosUnicos() {
        Producto a = FabricaProductos.crear(TipoProducto.INSUMO_FRIO, new DatosProducto("A", 1, 1));
        Producto b = FabricaProductos.crear(TipoProducto.INSUMO_FRIO, new DatosProducto("B", 1, 1));
        assertNotEquals(a.codigo(), b.codigo());
    }
}
