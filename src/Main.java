import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        EncontrarInversionesImplementacion invertir = new EncontrarInversionesImplementacion();
        /*
        ArrayList<Inversion> inversiones = new ArrayList<Inversion>();
        Inversion inversion = new Inversion();
        inversion.nombreInversion = "A";
        inversion.montoMinimoParaInvertir= 120000;
        inversion.porcentajeRentabilidad = 45;
        inversion.riesgo = 8;
        inversiones.add(inversion);

        inversion = new Inversion();
        inversion.nombreInversion = "B";
        inversion.montoMinimoParaInvertir= 80000;
        inversion.porcentajeRentabilidad = 52;
        inversion.riesgo = 12;
        inversiones.add(inversion);

        inversion = new Inversion();
        inversion.nombreInversion = "C";
        inversion.montoMinimoParaInvertir= 90000;
        inversion.porcentajeRentabilidad = 70;
        inversion.riesgo = 11;
        inversiones.add(inversion);

        double montoAInvertir = 10000000;
        int valorIncremental = 20000;
        ArrayList<ArrayList<InversionSeleccionada>> res= invertir.obtenerInversiones(inversiones, montoAInvertir, valorIncremental);
        imprimirResultado(res);
         */

        ArrayList<Inversion> inversiones = new ArrayList<Inversion>();
        Inversion inversion = new Inversion();
        inversion.nombreInversion = "A";
        inversion.montoMinimoParaInvertir= 80;
        inversion.porcentajeRentabilidad = 45;
        inversion.riesgo = 8;
        inversiones.add(inversion);

        inversion = new Inversion();
        inversion.nombreInversion = "B";
        inversion.montoMinimoParaInvertir= 80;
        inversion.porcentajeRentabilidad = 52;
        inversion.riesgo = 12;
        inversiones.add(inversion);

        inversion = new Inversion();
        inversion.nombreInversion = "C";
        inversion.montoMinimoParaInvertir= 80;
        inversion.porcentajeRentabilidad = 45;
        inversion.riesgo = 11;
        inversiones.add(inversion);

        double montoAInvertir = 1000;
        int valorIncremental = 28;
        ArrayList<ArrayList<InversionSeleccionada>> res= invertir.obtenerInversiones(inversiones, montoAInvertir, valorIncremental);
        imprimirResultado(res);
    }

    private static void imprimirResultado(ArrayList<ArrayList<InversionSeleccionada>> res) {
        for (int i=0; i < res.size();i++) {
            ArrayList<InversionSeleccionada> resIndividual = res.get(i);
            System.out.println("Inversion "+i);
            for (int j=0; j < resIndividual.size();j++) {
                InversionSeleccionada invRes = resIndividual.get(j);
                System.out.println("Option "+ j + " "+invRes.nombreInversionSeleccionada+ " "+ invRes.montoAInvertir+ " "+invRes.rentabilidadObtenida+" " + invRes.riesgoPromedio);
            }
        }
    }
}
