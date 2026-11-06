package ec.uagraria.costos;

public sealed interface Cultivo permits Banano, Cacao, Arroz {

    String nombreComun();

}