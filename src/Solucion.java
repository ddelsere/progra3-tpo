import java.util.ArrayList;

public class Solucion {

    public ArrayList<InversionSeleccionada> inversionesRealizadas;

    public void insertar (InversionSeleccionada inversionSeleccionada) {
        this.inversionesRealizadas.addLast(inversionSeleccionada);
    }

    public void eliminarUltima() {
        this.inversionesRealizadas.removeLast();
    }

}
