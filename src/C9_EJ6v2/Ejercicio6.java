package C9_EJ6v2;

import TDAs.AVL.AVL;
import TDAs.AVL.DefaultComparator;

import java.util.Comparator;

public class Ejercicio6 {
    public static void main(String[] args) {

        Comparator<Integer> d = new DefaultComparator<Integer>();
        AVL<Integer> elementos = new AVL<Integer>(d);

        // ingresar inputs numericos que se detenien con el simbolo ".". los elementos se agregan a un AVL
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Ingrese números para agregar al árbol AVL (ingrese '.' para terminar):");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();
                elementos.insert(numero);
            } else if (scanner.hasNext("\\.")) {
                break;
            } else {
                System.out.println("Entrada inválida. Por favor ingrese un número o '.' para terminar.");
                scanner.next(); // descartar entrada inválida
            }
        }

        // se genera un arreglo con los elementos del AVL ordenados de menor a mayor que tambien se muestra por pantalla
        System.out.println("Elementos del árbol AVL en orden ascendente:");
        elementos.inOrderTraversal();

    }
}
