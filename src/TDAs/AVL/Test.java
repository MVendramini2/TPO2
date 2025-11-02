package TDAs.AVL;
import java.util.Comparator;

public class Test {

	public static void main(String[] args) {
        Comparator<Integer> d = new DefaultComparator<Integer>();
        AVL<Integer> tree = new AVL<Integer>(d);

        int[] values = {10, 20, 30, 40, 50, 25};
        System.out.println("Insertando valores:");
        for (int v : values) {
            System.out.println("Insert: " + v);
            tree.insert(v);
        }

        System.out.print("Recorrido in-order: "+tree.toString());

        System.out.println("\nEliminando valor 40:");
        tree.delete(40);
        System.out.print("Después de eliminar 40: "+tree.toString());
        
        System.out.println("\nInsertando valor 5:");
        tree.insert(5);
        System.out.print("Después de insertar 5: "+tree.toString());
    }

}
