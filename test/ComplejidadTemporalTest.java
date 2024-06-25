import org.junit.Test;

import java.util.ArrayList;

public class ComplejidadTemporalTest {

    @Test
    public void pruebaAltoVolumenInversiones() {
        // M = 10
        // N = Volumen
        int volumen = 1000;
        ArrayList<Inversion> inversiones = new ArrayList<>();
        for (int i = 0; i < volumen; i++) {
            Inversion inversion = new Inversion();
            inversion.nombreInversion = "Inversion" + i;
            inversion.montoMinimoParaInvertir = 2000;
            inversion.porcentajeRentabilidad = i;
            inversion.riesgo = i;
            inversiones.addLast(inversion);
        }
        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();
        long tiempoInicio = System.currentTimeMillis();
        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 5000.0, 500);
        long tiempoFinal = System.currentTimeMillis();
        double tiempoEjecucion = (tiempoFinal - tiempoInicio) / 1000.0;
        System.out.println("Tiempo de ejcucion del algoritmo: " + tiempoEjecucion + " seg.");
    }

}
