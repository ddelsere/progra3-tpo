import java.util.ArrayList;

public class Solucion {

    public ArrayList<InversionSeleccionada> inversionesRealizadas = new ArrayList<InversionSeleccionada>();
    public double gananciaTotal = 0;

    public void insertar (InversionSeleccionada inversionSeleccionada) {
        this.inversionesRealizadas.addLast(inversionSeleccionada);
        this.gananciaTotal += inversionSeleccionada.rentabilidadObtenida;
    }

    public void eliminarUltima() {
        this.gananciaTotal -= this.inversionesRealizadas.getLast().rentabilidadObtenida;
        this.inversionesRealizadas.removeLast();
    }

    public Solucion copiarSolucion() {
        ArrayList<InversionSeleccionada> nuevasInversiones = new ArrayList<InversionSeleccionada>();
        Solucion nuevaSolucion = new Solucion();
        for (int i = 0; i< this.inversionesRealizadas.size(); i++) {
            nuevasInversiones.addLast(this.inversionesRealizadas.get(i));
        }
        nuevaSolucion.inversionesRealizadas = nuevasInversiones;
        nuevaSolucion.gananciaTotal = this.gananciaTotal;
        return nuevaSolucion;
    }

}
