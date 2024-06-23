import java.util.ArrayList;

public class SolucionMaxima {

    public ArrayList<Solucion> soluciones = new ArrayList<Solucion>();
    public double gananciaTotal = 0;

    public void insertar(Solucion solucion) {
        Solucion copiaSolucion = solucion.copiarSolucion();
        this.soluciones.addLast(copiaSolucion);
    }

    public void reiniciar() {
        this.soluciones = new ArrayList<Solucion>();
    }

    public boolean estaVacia() {
        return this.soluciones.size() == 0;
    }

}
