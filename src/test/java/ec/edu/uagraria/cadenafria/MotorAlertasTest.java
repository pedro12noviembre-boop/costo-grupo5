package ec.edu.uagraria.cadenafria;

import ec.edu.uagraria.cadenafria.alertas.*;
import ec.edu.uagraria.cadenafria.dominio.LecturaFrio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorAlertasTest {

    @BeforeEach
    void restablecerConfiguracion() {
        ConfiguracionFrio.INSTANCIA.restablecer();
    }

    // ---- TEMPERATURA_FUERA_RANGO ----
    @Test
    @DisplayName("Temperatura fuera de rango se dispara por encima del máximo")
    void temperaturaAltaDispara() {
        var alerta = Reglas.temperaturaFueraRango().evaluar(new LecturaFrio("CAM-01", 4.1, 0, 0));
        assertTrue(alerta.isPresent());
        assertEquals(TipoAlerta.TEMPERATURA_FUERA_RANGO, alerta.get().tipo());
    }

    @Test
    @DisplayName("Temperatura fuera de rango no se dispara en el umbral exacto")
    void temperaturaNoDisparaEnElUmbral() {
        ConfiguracionFrio.INSTANCIA.cambiarRangoTemperatura(0.0, 4.0);
        assertTrue(Reglas.temperaturaFueraRango().evaluar(new LecturaFrio("CAM-01", 4.0, 0, 0)).isEmpty());
        assertTrue(Reglas.temperaturaFueraRango().evaluar(new LecturaFrio("CAM-01", 0.0, 0, 0)).isEmpty());
    }

    // ---- RUPTURA_PROLONGADA ----
    @Test
    @DisplayName("Ruptura prolongada se dispara por encima del umbral")
    void rupturaDispara() {
        var alerta = Reglas.rupturaProlongada().evaluar(new LecturaFrio("CAM-02", 6.0, 31, 0));
        assertTrue(alerta.isPresent());
        assertEquals(TipoAlerta.RUPTURA_PROLONGADA, alerta.get().tipo());
    }

    @Test
    @DisplayName("Ruptura prolongada no se dispara en el umbral exacto")
    void rupturaNoDisparaEnElUmbral() {
        ConfiguracionFrio.INSTANCIA.cambiarMinutosRuptura(30);
        assertTrue(Reglas.rupturaProlongada().evaluar(new LecturaFrio("CAM-02", 6.0, 30, 0)).isEmpty());
    }

    // ---- PUERTA_ABIERTA ----
    @Test
    @DisplayName("Puerta abierta se dispara por encima del umbral")
    void puertaDispara() {
        var alerta = Reglas.puertaAbierta().evaluar(new LecturaFrio("CAM-03", 2.0, 0, 6));
        assertTrue(alerta.isPresent());
        assertEquals(TipoAlerta.PUERTA_ABIERTA, alerta.get().tipo());
    }

    @Test
    @DisplayName("Puerta abierta no se dispara en el umbral exacto")
    void puertaNoDisparaEnElUmbral() {
        ConfiguracionFrio.INSTANCIA.cambiarMinutosPuertaAbierta(5);
        assertTrue(Reglas.puertaAbierta().evaluar(new LecturaFrio("CAM-03", 2.0, 0, 5)).isEmpty());
    }

    // ---- Motor y observadores ----
    @Test
    @DisplayName("Los umbrales son configurables sin tocar la regla")
    void umbralConfigurable() {
        ConfiguracionFrio.INSTANCIA.cambiarRangoTemperatura(-2.0, 8.0);
        assertTrue(Reglas.temperaturaFueraRango().evaluar(new LecturaFrio("CAM-01", 6.0, 0, 0)).isEmpty());
    }

    @Test
    @DisplayName("El motor notifica a todos los observadores")
    void motorNotificaATodos() {
        MotorAlertas motor = new MotorAlertas();
        motor.agregarRegla(Reglas.temperaturaFueraRango());
        List<Alerta> a = new ArrayList<>(), b = new ArrayList<>();
        motor.suscribir(a::add);
        motor.suscribir(b::add);
        assertEquals(1, motor.procesar(new LecturaFrio("CAM-01", 9.0, 0, 0)));
        assertEquals(1, a.size());
        assertEquals(1, b.size());
    }

    @Test
    @DisplayName("El motor publica las tres alertas con una lectura crítica")
    void motorPublicaLasTres() {
        MotorAlertas motor = new MotorAlertas();
        motor.agregarRegla(Reglas.temperaturaFueraRango());
        motor.agregarRegla(Reglas.rupturaProlongada());
        motor.agregarRegla(Reglas.puertaAbierta());
        List<Alerta> bitacora = new ArrayList<>();
        motor.suscribir(bitacora::add);
        assertEquals(3, motor.procesar(new LecturaFrio("CAM-01", 9.0, 45, 12)));
        assertEquals(3, bitacora.size());
    }

    @Test
    @DisplayName("Sin alertas no se notifica a nadie")
    void sinAlertasNoNotifica() {
        MotorAlertas motor = new MotorAlertas();
        motor.agregarRegla(Reglas.temperaturaFueraRango());
        List<Alerta> bitacora = new ArrayList<>();
        motor.suscribir(bitacora::add);
        assertEquals(0, motor.procesar(new LecturaFrio("CAM-01", 2.0, 0, 0)));
        assertTrue(bitacora.isEmpty());
    }
}
