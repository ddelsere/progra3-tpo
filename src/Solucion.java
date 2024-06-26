import java.util.ArrayList;

public class Solucion {

    public ArrayList<InversionSeleccionada> inversionesRealizadas = new ArrayList<InversionSeleccionada>();
    public double gananciaTotal = 0;

    public void insertar (InversionSeleccionada inversionSeleccionada) {
        this.inversionesRealizadas.addLast(inversionSeleccionada);
    }

    public void eliminarUltima() {
        this.inversionesRealizadas.removeLast();
    }

    public Solucion copiarSolucion() {
        ArrayList<InversionSeleccionada> nuevasInversiones = new ArrayList<InversionSeleccionada>();
        Solucion nuevaSolucion = new Solucion();
        for (int i = 0; i<this.inversionesRealizadas.size(); i++) {
            nuevasInversiones.addLast(inversionesRealizadas.get(i));
        }
        nuevaSolucion.inversionesRealizadas = nuevasInversiones;
        nuevaSolucion.gananciaTotal = this.gananciaTotal;
        return nuevaSolucion;
    }

}
