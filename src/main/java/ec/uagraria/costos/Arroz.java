package ec.uagraria.costos;

public record Arroz(int ciclosPorAnio) implements Cultivo {

    @Override
    public String nombreComun() {
        return "Arroz (" + ciclosPorAnio + " ciclos/año)";
    }
}