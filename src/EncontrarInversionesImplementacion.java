import java.util.ArrayList;
import java.util.List;

public class EncontrarInversionesImplementacion implements EncontrarInversiones {

    @Override
    public ArrayList<ArrayList<InversionSeleccionada>> obtenerInversiones(ArrayList<Inversion> arg0, double arg1, int arg2) { //arg0: inversiones, arg1: monto a invertir, arg2: incremental
        // TODO Auto-generated method stub
        SolucionMaxima solucionMaxima = new SolucionMaxima();
        mejores_inversiones(arg0, arg2, arg1, 0, new Solucion(), 0, 0, solucionMaxima);
        ArrayList<ArrayList<InversionSeleccionada>> res = parseSolucionMaxima(solucionMaxima);
        return null;

    }

    private void mejores_inversiones(List<Inversion> inversiones, int incremental, double dineroRestante, int profundidad,
                                     Solucion solucionActual, double ganancia, double maxGanancia, SolucionMaxima maxSolucion){
        for (int i = 0; i <= 1; i++) {
            double porcentaje = inversiones.get(profundidad).porcentajeRentabilidad;
            double inversion = inversiones.get(profundidad).montoMinimoParaInvertir * i; //determina cuanto se va a invertir en la inversion actual
            if (inversion > 0){ // Caso: Realizo la inversion
                int indiceIncremental = 0;
                while(dineroRestante >= (inversion+incremental*indiceIncremental)){
                    inversion += incremental * indiceIncremental;
                    dineroRestante -= inversion;
                    ganancia += inversion*porcentaje; //aca deberia ir la reglita para calcular porcentaje
                    InversionSeleccionada parcial = inicializarInversionSeleccionada(inversion, inversiones.get(profundidad).nombreInversion, ganancia, inversiones.get(profundidad).riesgo);
                    solucionActual.insertar(parcial);
                    if (profundidad == inversiones.size()){//es hoja
                        if (ganancia > maxGanancia){
                            maxGanancia = ganancia;
                            maxSolucion.reiniciar();
                            maxSolucion.insertar(solucionActual);
                        } else if (ganancia == maxGanancia) {
                            maxSolucion.insertar(solucionActual);
                        }
                    }else{ //no es hoja
                        mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual, ganancia, maxGanancia, maxSolucion);
                    }
                    solucionActual.eliminarUltima();
                    ganancia -= inversion * porcentaje;
                    dineroRestante += inversion;
                    inversion -= incremental * indiceIncremental;
                    indiceIncremental++;
                }
            }else{ //Caso: no realizo la inversion
                if (profundidad == inversiones.size()){
                    if (ganancia > maxGanancia){
                        maxGanancia = ganancia;
                        maxSolucion.reiniciar();
                        maxSolucion.insertar(solucionActual);
                    } else if (ganancia == maxGanancia) {
                        maxSolucion.insertar(solucionActual);
                    }
                }else{ //no es hoja
                    mejores_inversiones(inversiones, incremental, dineroRestante, profundidad + 1, solucionActual, ganancia, maxGanancia, maxSolucion);
                }
            }
        }
    }

    public InversionSeleccionada inicializarInversionSeleccionada(double monto, String nombre, double rentabilidad, int riesgo){
        InversionSeleccionada inversionSeleccionada = new InversionSeleccionada();
        inversionSeleccionada.montoAInvertir = monto;
        inversionSeleccionada.nombreInversionSeleccionada = nombre;
        inversionSeleccionada.rentabilidadObtenida = rentabilidad; //?????????????????????????? no se si esta bien pasarle ganancia
        inversionSeleccionada.riesgoPromedio = riesgo;
        return inversionSeleccionada;
    }

    private ArrayList<ArrayList<InversionSeleccionada>> parseSolucionMaxima(SolucionMaxima solucionMaxima){
        //TODO: parsear nuestra solucion a lo que pide la profe
        return null;
    }
}
