package ec.edu.uagraria.cadenafria.alertas;

/** Singleton (enum): todos los umbrales viven aquí, ninguno dentro de una regla. */
public enum ConfiguracionFrio {
    INSTANCIA;

    private double temperaturaMinimaC = 0.0;
    private double temperaturaMaximaC = 4.0;
    private int minutosRupturaMaximos = 30;
    private int minutosPuertaAbiertaMaximos = 5;

    public double temperaturaMinimaC() { return temperaturaMinimaC; }
    public double temperaturaMaximaC() { return temperaturaMaximaC; }
    public int minutosRupturaMaximos() { return minutosRupturaMaximos; }
    public int minutosPuertaAbiertaMaximos() { return minutosPuertaAbiertaMaximos; }

    public void cambiarRangoTemperatura(double minimaC, double maximaC) {
        if (minimaC >= maximaC) {
            throw new IllegalArgumentException("El mínimo debe ser menor que el máximo");
        }
        this.temperaturaMinimaC = minimaC;
        this.temperaturaMaximaC = maximaC;
    }

    public void cambiarMinutosRuptura(int minutos) { this.minutosRupturaMaximos = minutos; }

    public void cambiarMinutosPuertaAbierta(int minutos) { this.minutosPuertaAbiertaMaximos = minutos; }

    /** Vuelve a los valores por defecto (útil en las pruebas). */
    public void restablecer() {
        temperaturaMinimaC = 0.0;
        temperaturaMaximaC = 4.0;
        minutosRupturaMaximos = 30;
        minutosPuertaAbiertaMaximos = 5;
    }
}
