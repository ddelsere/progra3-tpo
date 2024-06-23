
import java.util.ArrayList;
import java.util.List;

public class EncontrarInversionesImplementacion implements EncontrarInversiones {

    @Override
    public ArrayList<ArrayList<InversionSeleccionada>> obtenerInversiones(ArrayList<Inversion> inversiones, double montoAInvertir, int incremental) {
        ArrayList<ArrayList<InversionSeleccionada>> res = new ArrayList<>();
        if (inversiones.size() > 0) {
            SolucionMaxima solucionMaxima = new SolucionMaxima();
            mejores_inversiones(inversiones, incremental, montoAInvertir, 0, new Solucion(), solucionMaxima);
            if (!solucionMaxima.estaVacia()) {
                res = parseSolucionMaxima(solucionMaxima);
            }
        }
        return res;
    }

    private void mejores_inversiones(List<Inversion> inversiones, int incremental, double dineroRestante, int profundidad,
                                     Solucion solucionActual, SolucionMaxima maxSolucion) {
        for (int i = 0; i <= 1; i++) {
            Inversion inversion = inversiones.get(profundidad);
            double montoInvertido = inversion.montoMinimoParaInvertir * i;
            double porcentajeRentabilidad = inversion.porcentajeRentabilidad;
            if (montoInvertido > 0) { // Caso que se realiza la inversion
                int indiceIncremental = 0;
                while (dineroRestante >= (montoInvertido + (incremental * indiceIncremental))) {
                    // TODO: Fix temporal, si el incremental es 0, loopea infinito.
                    if (indiceIncremental == 1 && incremental == 0) {
                        break; // Tal vez puede hacer while(... && indiceincremental <= incremental
                    }
                    montoInvertido += incremental * indiceIncremental;
                    dineroRestante -= montoInvertido;
                    double gananciaObtenida = montoInvertido * (porcentajeRentabilidad / 100);
                    InversionSeleccionada inversionRealizada = inicializarInversionSeleccionada(montoInvertido,
                            inversion.nombreInversion, gananciaObtenida, inversion.riesgo);
                    solucionActual.insertar(inversionRealizada);
                    solucionActual.gananciaTotal += gananciaObtenida;
                    if (profundidad == inversiones.size() - 1) { // Es hoja
                        buscarSolucionesEnHoja(solucionActual, maxSolucion);
                    } else { // No es hoja
                        mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual,
                                maxSolucion);
                    }
                    solucionActual.gananciaTotal -= gananciaObtenida;
                    solucionActual.eliminarUltima();
                    dineroRestante += montoInvertido;
                    montoInvertido -= incremental * indiceIncremental;
                    indiceIncremental++;
                }
            } else { // Caso que no se realiza la inversion
                if (profundidad == inversiones.size() - 1) { // Es hoja
                    buscarSolucionesEnHoja(solucionActual, maxSolucion);
                } else { // No es hoja
                    mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual,
                            maxSolucion);
                }
            }
        }
    }

    private void buscarSolucionesEnHoja(Solucion solucionActual, SolucionMaxima maxSolucion) {
        if (solucionActual.gananciaTotal > maxSolucion.gananciaTotal && solucionActual.gananciaTotal != 0) {
            maxSolucion.gananciaTotal = solucionActual.gananciaTotal;
            maxSolucion.reiniciar();
            maxSolucion.insertar(solucionActual);
        } else if (solucionActual.gananciaTotal == maxSolucion.gananciaTotal && solucionActual.gananciaTotal != 0) {
            maxSolucion.insertar(solucionActual);
        }
    }

    public InversionSeleccionada inicializarInversionSeleccionada(double monto, String nombre, double rentabilidad,
                                                                  int riesgo) {
        InversionSeleccionada inversionSeleccionada = new InversionSeleccionada();
        inversionSeleccionada.montoAInvertir = monto;
        inversionSeleccionada.nombreInversionSeleccionada = nombre;
        inversionSeleccionada.rentabilidadObtenida = rentabilidad;
        inversionSeleccionada.riesgoPromedio = riesgo;
        return inversionSeleccionada;
    }

    private ArrayList<ArrayList<InversionSeleccionada>> parseSolucionMaxima(SolucionMaxima solucionMaxima) {
        ArrayList<ArrayList<InversionSeleccionada>> out = new ArrayList<ArrayList<InversionSeleccionada>>();
        ArrayList<Solucion> soluciones = solucionMaxima.soluciones;
        for (int i = 0; i < soluciones.size(); i++) {
            out.addLast(soluciones.get(i).inversionesRealizadas);
        }
        return out;
    }

}
