package ec.edu.uagraria.cadenafria.alertas;

/** Observador: recibe cada alerta que publica el motor. */
@FunctionalInterface
public interface ObservadorAlertas {
    void notificar(Alerta alerta);
}
