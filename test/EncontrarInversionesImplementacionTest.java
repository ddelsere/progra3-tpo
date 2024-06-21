import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class EncontrarInversionesImplementacionTest {

    @Test
    public void testObtenerInversionesMaxima() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 1000.0;
        inversionA.porcentajeRentabilidad = 5;
        inversionA.riesgo = 1;
        inversiones.add(inversionA);

        Inversion inversionB = new Inversion();
        inversionB.nombreInversion = "Inversion B";
        inversionB.montoMinimoParaInvertir = 2000.0;
        inversionB.porcentajeRentabilidad = 10;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 3000.0, 500);

        assertEquals(1, resultado.size(), "Debe haber una solución máxima");

        ArrayList<InversionSeleccionada> maxInversion = resultado.get(0);



        double gananciaTotal = 0;
        for (InversionSeleccionada inversion : maxInversion) {
            gananciaTotal += inversion.rentabilidadObtenida;
        }

        System.out.print(gananciaTotal);

        assertTrue(gananciaTotal > 0, "La ganancia total debe ser mayor que cero");
        assertEquals(1, maxInversion.size(), "Debe haber 2 inversiones seleccionadas");
    }

    @Test
    public void testObtenerDosInversionesMaxima() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 1000.0;
        inversionA.porcentajeRentabilidad = 5;
        inversionA.riesgo = 1;
        inversiones.add(inversionA);

        Inversion inversionB = new Inversion();
        inversionB.nombreInversion = "Inversion B";
        inversionB.montoMinimoParaInvertir = 2000.0;
        inversionB.porcentajeRentabilidad = 10;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        Inversion inversionC = new Inversion();
        inversionB.nombreInversion = "Inversion C";
        inversionB.montoMinimoParaInvertir = 2000.0;
        inversionB.porcentajeRentabilidad = 10;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 4000.0, 500);

        assertEquals(3, resultado.size(), "Debe haber dos soluciones máximas");

        ArrayList<InversionSeleccionada> maxInversion = new ArrayList<>();

        maxInversion.add(resultado.get(0).getFirst());
        maxInversion.add(resultado.get(1).get(1));


        double gananciaTotal = 0;
        for (InversionSeleccionada inversion : maxInversion) {
            gananciaTotal += inversion.rentabilidadObtenida;
        }

        System.out.print(gananciaTotal);

        assertTrue(gananciaTotal > 0, "La ganancia total debe ser mayor que cero");
        assertEquals(2, maxInversion.size(), "Debe haber 2 inversiones seleccionadas");
    }
}
