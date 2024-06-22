import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EncontrarInversionesImplementacionTest {

    @Test
    public void testConInversionMaximaUnica() {
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

        assertEquals( "La solucion tiene una unica inversion realizada",1 , resultado.size());

        ArrayList<InversionSeleccionada> maxInversion = resultado.get(0);
        InversionSeleccionada inversionSeleccionada= maxInversion.get(0);

        assertEquals("La solucion dada es la correcta", "Inversion B", inversionSeleccionada.nombreInversionSeleccionada);

        assertEquals("El monto invertido es el correcto",3000, inversionSeleccionada.montoAInvertir, 0);

        assertEquals("La ganancia obtenida es la correcta", 300, inversionSeleccionada.rentabilidadObtenida, 0);

    }

    // TODO PRUEBA CON INVERSION MAXIMA DOBLE

    @Test
    public void testConTripleInversionMaxima() {
        ArrayList<Inversion> inversiones = new ArrayList<>();
        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 2000.0;
        inversionA.porcentajeRentabilidad = 10;
        inversionA.riesgo = 1;
        inversiones.add(inversionA);

        Inversion inversionB = new Inversion();
        inversionB.nombreInversion = "Inversion B";
        inversionB.montoMinimoParaInvertir = 2000.0;
        inversionB.porcentajeRentabilidad = 10;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 4000.0, 500);

        assertEquals( "La solucion posee tres caminos posibles",3 , resultado.size());

        int i = 0;
        for (ArrayList<InversionSeleccionada> camino : resultado) {
            double gananciaTotal = 0;
            for (InversionSeleccionada inversion : camino) {
                gananciaTotal += inversion.rentabilidadObtenida;
            }
            assertEquals("La ganancia obtenida del camino: " + i + " Es la correcta", 400, gananciaTotal, 0);
            i++;
        }
        // TODO VERIFICAR QUE LOS MONTOS E INVERSIONES SEAN CORRECTAS

    }
}
