package ec.edu.uagraria.cadenafria.dominio;

/**
 * Lectura de un sensor de una cámara de frío.
 *
 * @param idCamara             identificador de la cámara
 * @param temperaturaC         temperatura actual en grados Celsius
 * @param minutosFueraRango    minutos seguidos que lleva fuera del rango permitido
 * @param minutosPuertaAbierta minutos seguidos que lleva la puerta abierta
 */
public record LecturaFrio(String idCamara, double temperaturaC,
                          int minutosFueraRango, int minutosPuertaAbierta) {
}
