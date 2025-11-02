package TDAs.AVL;

public class NodoAVL<E> {
    E data;
    NodoAVL<E> left, right;
    int height;

    public NodoAVL(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
