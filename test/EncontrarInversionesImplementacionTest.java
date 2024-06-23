import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EncontrarInversionesImplementacionTest {

    @Test
    public void testConDinero0() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 10000.0;
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

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 0.0, 1500);

    }

    @Test
    public void testConInversionesVacias() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 10000.0, 1000);

        assertEquals("El resultado no debe contener ninguna inversion", 0, resultado.size());

    }

    @Test
    public void testConIncremental0() {
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

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 2000.0, 0);

        assertEquals( "La solucion tiene una unica inversion realizada",1 , resultado.size());

        ArrayList<InversionSeleccionada> maxInversion = resultado.get(0);
        InversionSeleccionada inversionSeleccionada= maxInversion.get(0);

        assertEquals("La solucion dada es la correcta", "Inversion B", inversionSeleccionada.nombreInversionSeleccionada);

        assertEquals("El monto invertido es el correcto",2000, inversionSeleccionada.montoAInvertir, 0);

        assertEquals("La ganancia obtenida es la correcta", 200, inversionSeleccionada.rentabilidadObtenida, 0);
    }

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

    @Test
    public void testConInversionMaximaDoble() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 1000.0;
        inversionA.porcentajeRentabilidad = 5;
        inversionA.riesgo = 1;
        inversiones.add(inversionA);

        Inversion inversionB = new Inversion();
        inversionB.nombreInversion = "Inversion B";
        inversionB.montoMinimoParaInvertir = 2500.0;
        inversionB.porcentajeRentabilidad = 5;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 3000.0, 500);

        assertEquals( "La solucion tiene una unica inversion realizada",2 , resultado.size());

        int i = 0;
        ArrayList<String> nombresInversiones = new ArrayList<String>();
        for (ArrayList<InversionSeleccionada> camino : resultado) {
            double gananciaTotal = 0;
            for (InversionSeleccionada inversion : camino) {
                gananciaTotal += inversion.rentabilidadObtenida;
                assertEquals("El monto invertido del camino: " + i + " es el correcto", 3000, inversion.montoAInvertir, 0);
            }
            assertEquals("La ganancia obtenida del camino: " + i + " es la correcta", 150, gananciaTotal, 0);
            i++;
        }
        // TODO VERIFICAR QUE LAS INVERTIDAS SEAN (A), (B) -> pueden volver en cualquier orden.

    }

    @Test
    public void testConInversionMaximaTripe() {
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
                if  (camino.size() == 2) {
                    assertEquals("El monto invertido del camino: " + i + " es el correcto", 2000, inversion.montoAInvertir, 0);
                } else {
                    assertEquals("El monto invertido del camino: " + i + " es el correcto", 4000, inversion.montoAInvertir, 0);
                }
            }
            assertEquals("La ganancia obtenida del camino: " + i + " Es la correcta", 400, gananciaTotal, 0);
            i++;
        }
        // TODO VERIFICAR QUE LAS INVERTIDAS SEAN (A), (A,B), (B) -> pueden volver en cualquier orden.
    }


    @Test
    public void testConInversionesMultiplesYSoloUnaInversion() {
        ArrayList<Inversion> inversiones = new ArrayList<>();

        Inversion inversionA = new Inversion();
        inversionA.nombreInversion = "Inversion A";
        inversionA.montoMinimoParaInvertir = 1000000.0;
        inversionA.porcentajeRentabilidad = 5;
        inversionA.riesgo = 1;
        inversiones.add(inversionA);

        Inversion inversionB = new Inversion();
        inversionB.nombreInversion = "Inversion B";
        inversionB.montoMinimoParaInvertir = 2000000.0;
        inversionB.porcentajeRentabilidad = 10;
        inversionB.riesgo = 2;
        inversiones.add(inversionB);

        Inversion inversionC = new Inversion();
        inversionC.nombreInversion = "Inversion C";
        inversionC.montoMinimoParaInvertir = 1500000.0;
        inversionC.porcentajeRentabilidad = 6;
        inversionC.riesgo = 2;
        inversiones.add(inversionC);

        Inversion inversionD = new Inversion();
        inversionD.nombreInversion = "Inversion D";
        inversionD.montoMinimoParaInvertir = 3000000.0;
        inversionD.porcentajeRentabilidad = 8;
        inversionD.riesgo = 3;
        inversiones.add(inversionD);

        Inversion inversionE = new Inversion();
        inversionE.nombreInversion = "Inversion E";
        inversionE.montoMinimoParaInvertir = 2500.0;
        inversionE.porcentajeRentabilidad = 15;
        inversionE.riesgo = 1;
        inversiones.add(inversionE);

        EncontrarInversiones encontrarInversiones = new EncontrarInversionesImplementacion();

        ArrayList<ArrayList<InversionSeleccionada>> resultado = encontrarInversiones.obtenerInversiones(inversiones, 3000.0, 500);

        // Asumiendo que la mejor inversión es la segunda (Inversion B)
        assertEquals("La solución tiene una única inversión realizada", 1, resultado.size());

        ArrayList<InversionSeleccionada> maxInversion = resultado.get(0);
        InversionSeleccionada inversionSeleccionada = maxInversion.get(0);

        assertEquals("La solución dada es la correcta", "Inversion E", inversionSeleccionada.nombreInversionSeleccionada);

        assertEquals("El monto invertido es el correcto", 3000, inversionSeleccionada.montoAInvertir, 0);

        assertEquals("La ganancia obtenida es la correcta", 450, inversionSeleccionada.rentabilidadObtenida, 0);
    }
}


