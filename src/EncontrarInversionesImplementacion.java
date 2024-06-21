import com.sun.tools.jconsole.JConsoleContext;

import java.util.ArrayList;
import java.util.List;

public class EncontrarInversionesImplementacion implements EncontrarInversiones {

    @Override
    public ArrayList<ArrayList<InversionSeleccionada>> obtenerInversiones(ArrayList<Inversion> arg0, double arg1, int arg2) { //arg0: inversiones, arg1: monto a invertir, arg2: incremental
        // TODO Auto-generated method stub
        SolucionMaxima solucionMaxima = new SolucionMaxima();
        mejores_inversiones(arg0, arg2, arg1, 0, new Solucion(), solucionMaxima);
        ArrayList<ArrayList<InversionSeleccionada>> res = parseSolucionMaxima(solucionMaxima);
        return res;

    }

    private void mejores_inversiones(List<Inversion> inversiones, int incremental, double dineroRestante, int profundidad,
                                     Solucion solucionActual, SolucionMaxima maxSolucion){
        for (int i = 0; i <= 1; i++) {
            Inversion inversion = inversiones.get(profundidad);
            double montoInvertido = inversion.montoMinimoParaInvertir * i;
            double porcentajeRentabilidad = inversion.porcentajeRentabilidad;
            if (montoInvertido > 0) { // Caso que se realiza la inversion
                int indiceIncremental = 0;
                while (dineroRestante >= (montoInvertido + (incremental * indiceIncremental))) {
                    montoInvertido += incremental * indiceIncremental;
                    dineroRestante -= montoInvertido;
                    double gananciaObtenida = montoInvertido * (porcentajeRentabilidad / 100);
                    InversionSeleccionada inversionRealizada = inicializarInversionSeleccionada(montoInvertido, inversion.nombreInversion, gananciaObtenida, inversion.riesgo);
                    solucionActual.insertar(inversionRealizada);
                    solucionActual.gananciaTotal += gananciaObtenida;
                    if (profundidad == inversiones.size() - 1) { // Es hoja
                        buscarSolucionesEnHoja(solucionActual, maxSolucion);
                    }
                    else { // No es hoja
                        mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual, maxSolucion);
                    }
                    solucionActual.gananciaTotal -= gananciaObtenida;
                    solucionActual.eliminarUltima();
                    dineroRestante += montoInvertido;
                    montoInvertido -= incremental * indiceIncremental;
                    indiceIncremental++;
                }
            }
            else { // Caso que no se realiza la inversion
                if (profundidad == inversiones.size() - 1) { // Es hoja
                    buscarSolucionesEnHoja(solucionActual, maxSolucion);
                }
                else { // No es hoja
                    mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual, maxSolucion);
                }
            }

        }
    }

    private void buscarSolucionesEnHoja(Solucion solucionActual, SolucionMaxima maxSolucion) {
        if (solucionActual.gananciaTotal > maxSolucion.gananciaTotal) {
            maxSolucion.gananciaTotal = solucionActual.gananciaTotal;
            maxSolucion.reiniciar();
            maxSolucion.insertar(solucionActual);
        }
        else if (solucionActual.gananciaTotal == maxSolucion.gananciaTotal){
            maxSolucion.insertar(solucionActual);
        }
    }

    public InversionSeleccionada inicializarInversionSeleccionada(double monto, String nombre, double rentabilidad, int riesgo){
        InversionSeleccionada inversionSeleccionada = new InversionSeleccionada();
        inversionSeleccionada.montoAInvertir = monto;
        inversionSeleccionada.nombreInversionSeleccionada = nombre;
        inversionSeleccionada.rentabilidadObtenida = rentabilidad;
        inversionSeleccionada.riesgoPromedio = riesgo;
        return inversionSeleccionada;
    }

    private ArrayList<ArrayList<InversionSeleccionada>> parseSolucionMaxima(SolucionMaxima solucionMaxima){
        ArrayList<ArrayList<InversionSeleccionada>> out = new ArrayList<ArrayList<InversionSeleccionada>>();
        ArrayList<Solucion> soluciones = solucionMaxima.soluciones;
        for (int i=0; i<soluciones.size(); i++) {
            out.addLast(soluciones.get(i).inversionesRealizadas);
        }
        return out;
    }
}
