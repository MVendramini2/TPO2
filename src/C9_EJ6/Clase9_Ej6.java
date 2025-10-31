package C9_EJ6;
import src.AVL.AVL;
import src.AVL.DefaultComparator;


import java.util.Scanner;

public class Clase9_Ej6 {
    public static void main(String[] args) {
    /*Entrada:  50 30 70 20 40 60 80 .
        Salida (resumen):
        Ordenado: [20, 30, 40, 50, 60, 70, 80]
*/
        Scanner sc = new Scanner(System.in);

        AVL<Integer> avl = new AVL<Integer>(new DefaultComparator<>() {
            public int compare(Integer a, Integer b) { return a.compareTo(b); }
        });

        System.out.println("Ingrese enteros; termine con un punto '.'");
        String tok = leerToken(sc, "> ");
        while (!".".equals(tok)) {
            if (esEntero(tok)) {
                avl.insert(Integer.parseInt(tok));
            } else {
                System.out.println("  (ignorado) no es entero: " + tok);
            }
            tok = leerToken(sc, "> ");
        }


        // “Arreglo” ordenado ascendente
        Integer[] ordenado = extraerEnterosDeToString(avl.toString());

        System.out.print("Ordenado (menor→mayor): [");
        int i = 0;
        while (i < ordenado.length) {
            System.out.print(ordenado[i]);
            if (i < ordenado.length - 1) System.out.print(", ");
            i = i + 1;
        }
        System.out.println("]");

        sc.close();

        System.out.println("\nÁrbol por niveles:");
        imprimirPorNivelesDesdeToString(avl.toString());
    }

    // Helpers

    private static void imprimirPorNivelesDesdeToString(String s) {
        // 1) Contar cuántos números hay para dimensionar arreglos
        int n = contarNumeros(s);

        // 2) Extraer valores y su nivel (nivel = profundidadDeParéntesis - 1)
        Integer[] vals = new Integer[n];
        int[] niveles = new int[n];
        int maxNivel = llenarValoresYNiveles(s, vals, niveles);

        // 3) Imprimir agrupado por nivel, de 0 a maxNivel
        int lvl = 0;
        while (lvl <= maxNivel) {
            System.out.print("Nivel " + lvl + ": ");
            boolean primero = true;
            int i = 0;
            while (i < n) {
                if (niveles[i] == lvl) {
                    if (!primero) System.out.print(" ");
                    System.out.print(vals[i]);
                    primero = false;
                }
                i = i + 1;
            }
            System.out.println();
            lvl = lvl + 1;
        }
    }

    private static int contarNumeros(String s) {
        int i = 0, n = s.length(), count = 0;
        while (i < n) {
            int start = i;
            boolean neg = false;
            if (i < n && s.charAt(i) == '-') { neg = true; i = i + 1; }
            boolean any = false;
            while (i < n && Character.isDigit(s.charAt(i))) { any = true; i = i + 1; }
            if (any) count = count + 1;
            else i = start + 1;
        }
        return count;
    }

    private static int llenarValoresYNiveles(String s, Integer[] vals, int[] niveles) {
        int i = 0, n = s.length(), k = 0, profundidad = 0, maxNivel = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                profundidad = profundidad + 1;
                i = i + 1;
            } else if (c == ')') {
                profundidad = profundidad - 1;
                i = i + 1;
            } else {
                int start = i;
                boolean neg = false;
                if (c == '-') { neg = true; i = i + 1; }
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) { j = j + 1; }
                if (j > i) {
                    String num = neg ? "-" + s.substring(i, j) : s.substring(i, j);
                    int nivel = profundidad - 1;      // raíz queda en 0
                    if (nivel < 0) nivel = 0;
                    vals[k] = Integer.valueOf(num);
                    niveles[k] = nivel;
                    if (nivel > maxNivel) maxNivel = nivel;
                    k = k + 1;
                    i = j;
                } else {
                    i = start + 1;
                }
            }
        }
        return maxNivel;
    }

    private static String leerToken(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.next();
        sc.nextLine();
        return s.trim();
    }

    private static boolean esEntero(String s) {
        int n = s.length();
        if (n == 0) return false;
        int idx = 0;
        char c0 = s.charAt(0);
        if (c0 == '-') {
            if (n == 1) return false;
            idx = 1;
        }
        boolean ok = true;
        while (ok && idx < n) {
            char c = s.charAt(idx);
            if (!Character.isDigit(c)) ok = false;
            idx = idx + 1;
        }
        return ok;
    }

    private static Integer[] extraerEnterosDeToString(String s) {
        // Primera pasada: contar cuántos números hay
        int count = 0;
        int i = 0;
        int n = s.length();
        while (i < n) {
            int start = i;
            boolean neg = false;
            if (i < n && s.charAt(i) == '-') { neg = true; i = i + 1; }
            boolean anyDigit = false;
            while (i < n && Character.isDigit(s.charAt(i))) {
                anyDigit = true;
                i = i + 1;
            }
            if (anyDigit) {
                count = count + 1;
            } else {
                i = start + 1;
            }
        }

        Integer[] out = new Integer[count];

        // Segunda pasada: Se guardan los números
        int k = 0;
        i = 0;
        while (i < n) {
            int start = i;
            boolean neg = false;
            if (i < n && s.charAt(i) == '-') { neg = true; i = i + 1; }
            int j = i;
            while (j < n && Character.isDigit(s.charAt(j))) { j = j + 1; }
            if (j > i) {
                String num = s.substring(neg ? i - 1 : i, j);
                // Si tenía '-', el substring arranca un char antes
                if (neg) num = "-" + s.substring(i, j);
                out[k] = Integer.valueOf(num);
                k = k + 1;
                i = j;
            } else {
                i = start + 1;
            }
        }
        return out;
    }
}
