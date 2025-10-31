package C10_EJ2;

import src.grafos.grafo.GrafoEst;
import src.grafos.grafo.GrafoTDA;


public class Main {
    public static void main(String[] args) {

        /*
        1) Cargar vértices:
        10, 20, 30, 40

        2) Cargar aristas dirigidas (origen → destino : costo):
        10 → 20 : 5
        10 → 30 : 12
        20 → 30 : 7
        40 → 10 : 3

        3) Pruebas esperadas:

        // Máximo costo saliente
        maxCostoSaliente(g, 10) -> 12      // (10→20:5, 10→30:12) → max = 12
        maxCostoSaliente(g, 20) -> 7       // (20→30:7)
        maxCostoSaliente(g, 40) -> 3       // (40→10:3)
        maxCostoSaliente(g, 30) -> 0       // sin salientes

        // Predecesores (u tales que u → v)
        predecesores(g, 30) -> [10, 20]    // 10→30 y 20→30
        predecesores(g, 10) -> [40]        // 40→10
        predecesores(g, 40) -> []          // nadie apunta a 40
        */

        GrafoEst<Integer> g = new GrafoEst<Integer>();
        g.inicializarGrafo();

        // (1) Vértices
        g.agregarVertice(10);
        g.agregarVertice(20);
        g.agregarVertice(30);
        g.agregarVertice(40);

        // (2) Aristas dirigidas con costo
        g.agregarArista(10, 20, 5);
        g.agregarArista(10, 30, 12);
        g.agregarArista(20, 30, 7);
        g.agregarArista(40, 10, 3);

        // (3) Probar métodos extra (definidos abajo, sin modificar el TDA)
        System.out.println("Max costo saliente(10): " + maxCostoSaliente(g, 10)); // 12
        System.out.println("Max costo saliente(20): " + maxCostoSaliente(g, 20)); // 7
        System.out.println("Max costo saliente(40): " + maxCostoSaliente(g, 40)); // 3
        System.out.println("Max costo saliente(30): " + maxCostoSaliente(g, 30)); // 0

        Integer[] preds30 = predecesores(g, 30);
        System.out.print("Predecesores(30): ");
        imprimirArray(preds30); // [10, 20] (orden no estrictamente importante)

        Integer[] preds10 = predecesores(g, 10);
        System.out.print("Predecesores(10): ");
        imprimirArray(preds10); // [40]

        Integer[] preds40 = predecesores(g, 40);
        System.out.print("Predecesores(40): ");
        imprimirArray(preds40); // []
    }


    // (1) Dado un vértice v, calcular el mayor de los costos de las aristas salientes.
    public static <E> int maxCostoSaliente(GrafoTDA<E> g, E v) {
        E[] vs = g.vertices();
        int max = 0;
        int i = 0;
        while (i < vs.length) {
            int costo = g.pesoArista(v, vs[i]); // 0 si no hay arista
            if (costo > max) {
                max = costo;
            }
            i = i + 1;
        }
        return max;
    }


    @SuppressWarnings("unchecked")
    public static <E> E[] predecesores(GrafoTDA<E> g, E v) {
        E[] vs = g.vertices();

        // 1) Contar predecesores
        int count = 0;
        int i = 0;
        while (i < vs.length) {
            if (g.existeArista(vs[i], v)) {
                count = count + 1;
            }
            i = i + 1;
        }

        // 2) Crear arreglo del MISMO tipo de componente que vs (no Object[])
        Class<?> comp = vs.getClass().getComponentType();
        E[] out = (E[]) Array.newInstance(comp, count);

        // 3) Llenar
        int k = 0;
        i = 0;
        while (i < vs.length) {
            if (g.existeArista(vs[i], v)) {
                out[k] = vs[i];
                k = k + 1;
            }
            i = i + 1;
        }
        return out;
    }

    // Helper simple para imprimir
    private static <T> void imprimirArray(T[] arr) {
        System.out.print("[");
        int i = 0;
        while (i < arr.length) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
            i = i + 1;
        }
        System.out.println("]");
    }
}

