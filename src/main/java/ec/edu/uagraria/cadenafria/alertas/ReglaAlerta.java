package ec.edu.uagraria.cadenafria.alertas;

import ec.edu.uagraria.cadenafria.dominio.LecturaFrio;

import java.util.Optional;

/** Regla: evalúa una lectura y devuelve una alerta solo si corresponde. */
@FunctionalInterface
public interface ReglaAlerta {
    Optional<Alerta> evaluar(LecturaFrio lectura);
}
