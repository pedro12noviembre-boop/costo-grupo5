package ec.edu.uagraria.cadenafria.dominio;

/** Jerarquía sellada: solo estos tres productos existen en la cadena de frío. */
public sealed interface Producto extends Trazable
        permits FlorCorte, LotePescado, InsumoFrio {
}
