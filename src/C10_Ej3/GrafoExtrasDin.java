package C10_EJ3;

import TDAs.grafos.List.LinkedList;
import TDAs.grafos.grafo.GrafoTDA;

public final class GrafoExtrasDin {
    private GrafoExtrasDin() {}

    /** (1) Vértices aislados: sin aristas de salida NI de entrada. */
    public static <E> LinkedList<E> aislados(GrafoTDA<E> g) {
        E[] vs = g.vertices();
        LinkedList<E> res = new LinkedList<E>();

        int i = 0;
        while (i < vs.length) {
            boolean salida = tieneSalida(g, vs[i], vs);
            boolean entrada = tieneEntrada(g, vs[i], vs);
            if (!salida && !entrada) {
                res.addLast(vs[i]);
            }
            i = i + 1;
        }
        return res;
    }

    /** (2) Puentes entre o y d: p tal que existe o->p y p->d. */
    public static <E> LinkedList<E> puentesEntre(GrafoTDA<E> g, E o, E d) {
        E[] vs = g.vertices();
        LinkedList<E> res = new LinkedList<E>();

        int i = 0;
        while (i < vs.length) {
            if (g.existeArista(o, vs[i]) && g.existeArista(vs[i], d)) {
                res.addLast(vs[i]);
            }
            i = i + 1;
        }
        return res;
    }

    // Helpers

    private static <E> boolean tieneSalida(GrafoTDA<E> g, E v, E[] vs) {
        int i = 0;
        while (i < vs.length) {
            if (g.existeArista(v, vs[i])) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }

    private static <E> boolean tieneEntrada(GrafoTDA<E> g, E v, E[] vs) {
        int i = 0;
        while (i < vs.length) {
            if (g.existeArista(vs[i], v)) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }
}
