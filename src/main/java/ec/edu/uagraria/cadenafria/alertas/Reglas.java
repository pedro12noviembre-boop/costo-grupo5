package ec.edu.uagraria.cadenafria.alertas;

import java.util.Optional;

/** Las tres alertas de CadenaFria, cada una como una regla separada (lambda). */
public final class Reglas {

    private Reglas() { }

    public static ReglaAlerta temperaturaFueraRango() {
        return l -> {
            ConfiguracionFrio c = ConfiguracionFrio.INSTANCIA;
            boolean fuera = l.temperaturaC() > c.temperaturaMaximaC()
                    || l.temperaturaC() < c.temperaturaMinimaC();
            return fuera
                    ? Optional.of(new Alerta(TipoAlerta.TEMPERATURA_FUERA_RANGO, l.idCamara(),
                        "Temperatura " + l.temperaturaC() + " °C fuera del rango ["
                        + c.temperaturaMinimaC() + ", " + c.temperaturaMaximaC() + "]"))
                    : Optional.empty();
        };
    }

    public static ReglaAlerta rupturaProlongada() {
        return l -> l.minutosFueraRango() > ConfiguracionFrio.INSTANCIA.minutosRupturaMaximos()
                ? Optional.of(new Alerta(TipoAlerta.RUPTURA_PROLONGADA, l.idCamara(),
                    l.minutosFueraRango() + " min fuera de rango (máximo "
                    + ConfiguracionFrio.INSTANCIA.minutosRupturaMaximos() + ")"))
                : Optional.empty();
    }

    public static ReglaAlerta puertaAbierta() {
        return l -> l.minutosPuertaAbierta() > ConfiguracionFrio.INSTANCIA.minutosPuertaAbiertaMaximos()
                ? Optional.of(new Alerta(TipoAlerta.PUERTA_ABIERTA, l.idCamara(),
                    "Puerta abierta " + l.minutosPuertaAbierta() + " min (máximo "
                    + ConfiguracionFrio.INSTANCIA.minutosPuertaAbiertaMaximos() + ")"))
                : Optional.empty();
    }
}
