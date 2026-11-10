package ec.edu.uagraria.cadenafria.alertas;

import ec.edu.uagraria.cadenafria.dominio.LecturaFrio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sujeto: compone reglas y observadores en lugar de heredar. */
public class MotorAlertas {

    private final List<ReglaAlerta> reglas = new ArrayList<>();
    private final List<ObservadorAlertas> observadores = new ArrayList<>();

    public void agregarRegla(ReglaAlerta regla) { reglas.add(regla); }

    public void suscribir(ObservadorAlertas observador) { observadores.add(observador); }

    /** Evalúa todas las reglas y devuelve cuántas alertas se publicaron. */
    public int procesar(LecturaFrio lectura) {
        int publicadas = 0;
        for (ReglaAlerta regla : reglas) {
            Optional<Alerta> alerta = regla.evaluar(lectura);
            if (alerta.isPresent()) {
                observadores.forEach(o -> o.notificar(alerta.get()));
                publicadas++;
            }
        }
        return publicadas;
    }
}
