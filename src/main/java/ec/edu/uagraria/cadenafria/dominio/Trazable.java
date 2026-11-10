package ec.edu.uagraria.cadenafria.dominio;

/** Todo lo que se puede rastrear en la cadena de frío tiene un código único. */
public interface Trazable {

    String codigo();

    /** Genera un código con formato PREFIJO-0001. */
    static String generarCodigo(String prefijo, int secuencia) {
        return String.format("%s-%04d", prefijo, secuencia);
    }
}
