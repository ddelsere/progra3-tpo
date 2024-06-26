import org.junit.Test;

import java.util.ArrayList;

public class ComplejidadTemporalTest {

    @Test
    public void pruebaAltoVolumenInversiones() {
        int volumen = 35;
        int repeticiones = 10;
        double dinero = 5000;
        int incremental = 500;
        int monto_inversiones = 500;
        double promedioTiempo = 0;
        System.out.println("N=" + volumen + ", M=" + dinero/incremental);
        for (int j = 0; j < repeticiones; j++) {
            ArrayList<Inversion> inversiones = new ArrayList<>();
            for (int i = 0; i < volumen; i++) {
                Inversion inversion = new Inversion();
                inversion.nombreInversion = "Inversion" + i;
                inversion.montoMinimoParaInvertir = monto_inversiones;
                inversion.porcentajeRentabilidad = i;
                inversion.riesgo = i;
                inversiones.addLast(inversion);
            }
            EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();
            long tiempoInicio = System.currentTimeMillis();
            ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, dinero, incremental);
            long tiempoFinal = System.currentTimeMillis();
            double tiempoEjecucion = (tiempoFinal - tiempoInicio) / 1000.0;
            promedioTiempo += tiempoEjecucion;
            System.out.println("Tiempo de ejcucion del algoritmo: " + tiempoEjecucion + " seg.");
        }
        System.out.println("Promedio de tiempo de ejecucion: " + promedioTiempo/repeticiones);
    }
}
