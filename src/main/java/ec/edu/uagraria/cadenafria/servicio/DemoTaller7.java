package ec.edu.uagraria.cadenafria.servicio;

public class DemoTaller7 {
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        inv.registrar(new ItemInventario("FLO-0001", "FlorCorte", "Rosas rojas", 300.0));
        inv.registrar(new ItemInventario("PES-0001", "LotePescado", "Tilapia entera", 220.0));
        inv.registrar(new ItemInventario("INS-0001", "InsumoFrio", "Hielo en escamas", 90.0));
        inv.registrar(new ItemInventario("FLO-0002", "FlorCorte", "Rosas blancas", 500.0));
        inv.registrar(new ItemInventario("FLO-0001", "FlorCorte", "Rosas rojas (reingreso)", 40.0));

        System.out.println("Registros (List): " + inv.registros().size());
        System.out.println("Codigos unicos (Set): " + inv.codigosUnicos());
        System.out.println("Total por tipo (Map): " + inv.totalPorTipo());
        System.out.println("Ordenados por cantidad (Comparator):");
        inv.ordenadosPorCantidad().forEach(i ->
                System.out.printf(" %-9s %-17s %7.1f%n", i.codigo(), i.tipo(), i.cantidad()));
        inv.ordenadosPorCantidad().forEach(inv::encolarDespacho);
        System.out.println("Despacho (Deque): " + inv.despacharSiguiente().orElseThrow().codigo()
                + " | pendientes: " + inv.pendientesDespacho());
    }
}
