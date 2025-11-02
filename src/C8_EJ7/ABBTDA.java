package C8_EJ7;

public interface ABBTDA<E extends Comparable<E>> {
    boolean pertenece(E e);
    void insertar(E e);
    E eliminar(E e); // retorna el eliminado o null si no estaba
    boolean isEmpty();
    int size();
    SimpleList<E> inOrder(); // recorrido in-order
}
