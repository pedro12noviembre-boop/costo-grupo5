package ec.uagraria.costos;

public record Cacao(String variedad, boolean organico) implements Cultivo {

    @Override
    public String nombreComun() {
        return "Cacao " + variedad;
    }
}