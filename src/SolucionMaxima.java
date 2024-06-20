import java.util.ArrayList;

public class SolucionMaxima {

    public ArrayList<Solucion> soluciones;

    public void insertar(Solucion solucion) {
        this.soluciones.addLast(solucion);
    }

    public void reiniciar() {
        this.soluciones = new ArrayList<Solucion>();
    }

}
