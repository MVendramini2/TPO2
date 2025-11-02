package C10_EJ3;

import TDAs.grafos.List.LinkedList;
import TDAs.grafos.grafo.GrafoDin;
import TDAs.grafos.grafo.GrafoTDA;

public class Main {
    public static void main(String[] args) {
        GrafoTDA<Integer> g = new GrafoDin<Integer>();
        g.inicializarGrafo();

       /* 1) Vértices a cargar:
        1, 2, 3, 4, 5

        2) Aristas dirigidas (origen → destino : costo):
        1 → 3 : 1
        2 → 3 : 1
        3 → 4 : 1
        // El vértice 5 queda sin aristas (posible aislado)

        3) Resultados esperados:

        // (1) Vértices aislados (sin entradas NI salidas)
        aislados(g)  => [5]

        // (2) Vértices puente entre o y d (p tal que o→p y p→d)
        puentesEntre(g, 1, 4) => [3]    // 1→3 y 3→4
        puentesEntre(g, 2, 4) => [3]    // 2→3 y 3→4
        puentesEntre(g, 1, 3) => []     // no hay p con 1→p y p→3
        puentesEntre(g, 4, 1) => []     // no hay camino de ese tipo

        */

        // Vértices
        g.agregarVertice(1);
        g.agregarVertice(2);
        g.agregarVertice(3);
        g.agregarVertice(4);
        g.agregarVertice(5); // aislado

        // Aristas (dirigidas)
        g.agregarArista(1, 3, 1);
        g.agregarArista(2, 3, 1);
        g.agregarArista(3, 4, 1);

        // (1) Aislados
        LinkedList<Integer> ais = GrafoExtrasDin.aislados(g);
        System.out.print("Aislados: ");
        imprimirLista(ais);   // esperado: [5]

        // (2) Puentes entre o y d (p tal que o->p y p->d)
        LinkedList<Integer> p14 = GrafoExtrasDin.puentesEntre(g, 1, 4);
        System.out.print("Puentes entre 1 y 4: ");
        imprimirLista(p14);   // esperado: [3]

        LinkedList<Integer> p24 = GrafoExtrasDin.puentesEntre(g, 2, 4);
        System.out.print("Puentes entre 2 y 4: ");
        imprimirLista(p24);   // esperado: [3]

        LinkedList<Integer> p13 = GrafoExtrasDin.puentesEntre(g, 1, 3);
        System.out.print("Puentes entre 1 y 3: ");
        imprimirLista(p13);   // esperado: []
    }

    // Imprime una List.LinkedList<T> del ZIP
    private static <T> void imprimirLista(LinkedList<T> lista) {
        System.out.print("[");
        lista.First();
        boolean primero = true;
        while (!lista.atEnd()) {
            if (!primero) System.out.print(", ");
            System.out.print(lista.getCurrent());
            primero = false;
            lista.advance();
        }
        System.out.println("]");
    }
}
