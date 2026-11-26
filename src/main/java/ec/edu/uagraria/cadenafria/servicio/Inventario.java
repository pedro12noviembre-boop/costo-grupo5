package ec.edu.uagraria.cadenafria.servicio;

import java.util.*;

/** Taller 7: inventario del proyecto integrador con el Java Collections Framework. */
public class Inventario {
    private final List<ItemInventario> registros = new ArrayList<>(); // orden de llegada
    private final Deque<ItemInventario> colaDespacho = new ArrayDeque<>(); // FIFO

    // 1. LIST: registrar en orden de llegada (admite repetidos)
    public void registrar(ItemInventario item) {
        registros.add(Objects.requireNonNull(item));
    }

    // 2. SET: codigos sin duplicados y ordenados alfabeticamente
    public Set<String> codigosUnicos() {
        Set<String> codigos = new TreeSet<>();
        for (ItemInventario i : registros) codigos.add(i.codigo());
        return codigos;
    }

    // 3. MAP: total de cantidad por tipo de producto
    public Map<String, Double> totalPorTipo() {
        Map<String, Double> totales = new TreeMap<>();
        for (ItemInventario i : registros) totales.merge(i.tipo(), i.cantidad(), Double::sum);
        return totales;
    }

    // 4. COMPARATOR: copia ordenada de mayor a menor cantidad
    public List<ItemInventario> ordenadosPorCantidad() {
        List<ItemInventario> copia = new ArrayList<>(registros);
        copia.sort(Comparator.comparingDouble(ItemInventario::cantidad).reversed()
                .thenComparing(ItemInventario::codigo));
        return copia;
    }

    // 5. DEQUE: cola de despacho (el primero que entra es el primero que sale)
    public void encolarDespacho(ItemInventario item) { colaDespacho.offerLast(item); }

    public Optional<ItemInventario> despacharSiguiente() {
        return Optional.ofNullable(colaDespacho.pollFirst());
    }

    public int pendientesDespacho() { return colaDespacho.size(); }

    public List<ItemInventario> registros() { return List.copyOf(registros); } // solo lectura
}
