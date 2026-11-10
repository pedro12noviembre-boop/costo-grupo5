package ec.edu.uagraria.cadenafria.alertas;

/** Factory de observadores. Los canales SMS y correo son simulados (imprimen). */
public final class FabricaObservadores {

    private FabricaObservadores() { }

    public static ObservadorAlertas crear(TipoObservador tipo) {
        return switch (tipo) {
            case CONSOLA -> a -> System.out.println("[ALERTA] " + a.tipo() + " | "
                    + a.idCamara() + " | " + a.mensaje());
            case SMS -> a -> System.out.println("[SMS simulado] " + a.tipo() + " en " + a.idCamara());
            case CORREO -> a -> System.out.println("[CORREO simulado] " + a.tipo() + ": " + a.mensaje());
        };
    }
}
