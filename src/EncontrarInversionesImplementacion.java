
import java.util.ArrayList;
import java.util.List;

public class EncontrarInversionesImplementacion implements EncontrarInversiones {

    @Override
    public ArrayList<ArrayList<InversionSeleccionada>> obtenerInversiones(ArrayList<Inversion> inversiones, double montoAInvertir, int incremental) {
        ArrayList<ArrayList<InversionSeleccionada>> res = new ArrayList<>();
        if (!inversiones.isEmpty()) {
            SolucionMaxima solucionMaxima = new SolucionMaxima();
            mejores_inversiones(inversiones, incremental, montoAInvertir, montoAInvertir, 0, new Solucion(), solucionMaxima);
            if (!solucionMaxima.estaVacia()) {
                res = parseSolucionMaxima(solucionMaxima);
            }
        }
        return res;
    }

    private void mejores_inversiones(List<Inversion> inversiones, int incremental, double dineroRestante, double dineroTotal, int profundidad,
                                     Solucion solucionActual, SolucionMaxima maxSolucion) {
        for (int i = 0; i <= 1; i++) {
            Inversion inversion = inversiones.get(profundidad);
            double montoInvertido = inversion.montoMinimoParaInvertir * i;
            double porcentajeRentabilidad = inversion.porcentajeRentabilidad;
            if (montoInvertido > 0) { // Caso que se realiza la inversion
                int indiceIncremental = 0;
                while (dineroRestante >= (montoInvertido + (incremental * indiceIncremental))) {
                    if (indiceIncremental == 1 && incremental == 0) {
                        break;
                    }
                    montoInvertido += incremental * indiceIncremental;
                    dineroRestante -= montoInvertido;
                    double gananciaObtenida = montoInvertido * (porcentajeRentabilidad / 100);
                    InversionSeleccionada inversionRealizada = inicializarInversionSeleccionada(montoInvertido, dineroTotal, inversion.nombreInversion, gananciaObtenida, inversion.riesgo);
                    solucionActual.insertar(inversionRealizada);
                    if (profundidad == inversiones.size() - 1) { // Es hoja
                        buscarSolucionesEnHoja(solucionActual, maxSolucion);
                    } else { // No es hoja
                        mejores_inversiones(inversiones, incremental, dineroRestante, dineroTotal,profundidad + 1, solucionActual, maxSolucion);
                    }
                    solucionActual.eliminarUltima();
                    dineroRestante += montoInvertido;
                    montoInvertido -= incremental * indiceIncremental;
                    indiceIncremental++;
                }
            } else { // Caso que no se realiza la inversion
                if (profundidad == inversiones.size() - 1) { // Es hoja
                    buscarSolucionesEnHoja(solucionActual, maxSolucion);
                } else { // No es hoja
                    mejores_inversiones(inversiones, incremental, dineroRestante, dineroTotal, profundidad + 1, solucionActual,
                            maxSolucion);
                }
            }
        }
    }

    private void buscarSolucionesEnHoja(Solucion solucionActual, SolucionMaxima maxSolucion) {
        String truncatedStringActual = String.format("%.4f", solucionActual.gananciaTotal);
        double truncatedValueActual = Double.parseDouble(truncatedStringActual);

        String truncatedStringMaxima = String.format("%.4f", maxSolucion.gananciaTotal);
        double truncatedValueMaxima= Double.parseDouble(truncatedStringMaxima);

        if (truncatedValueActual > truncatedValueMaxima && solucionActual.gananciaTotal != 0) {
            maxSolucion.reiniciar();
            maxSolucion.insertar(solucionActual);
        }
        else if (truncatedValueActual == truncatedValueMaxima && solucionActual.gananciaTotal != 0) {
            maxSolucion.insertar(solucionActual);
        }
    }

    public InversionSeleccionada inicializarInversionSeleccionada(double monto, double dineroTotal, String nombre, double rentabilidad,
                                                                  int riesgo) {
        InversionSeleccionada inversionSeleccionada = new InversionSeleccionada();
        inversionSeleccionada.montoAInvertir = monto;
        inversionSeleccionada.nombreInversionSeleccionada = nombre;
        inversionSeleccionada.rentabilidadObtenida = rentabilidad;
        inversionSeleccionada.riesgoPromedio = (int) (riesgo * (monto/dineroTotal));
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
