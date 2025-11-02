package Clase8_Ejercicio7;
public class ABB<E extends Comparable<E>> implements ABBTDA<E> {

    private static final class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;
        Node(T k) { this.key = k; }
    }

    private Node<E> root;
    private int n;

    @Override
    public boolean pertenece(E e) {
        return find(root, e) != null;
    }
    private Node<E> find(Node<E> p, E e) {
        if (p == null) return null;
        int c = e.compareTo(p.key);
        if (c == 0) return p;
        return (c < 0) ? find(p.left, e) : find(p.right, e);
    }

    @Override
    public void insertar(E e) {
        root = insertRec(root, e);
    }
    private Node<E> insertRec(Node<E> p, E e) {
        if (p == null) { n++; return new Node<>(e); }
        int c = e.compareTo(p.key);
        if (c < 0) {
            p.left = insertRec(p.left, e);
        } else if (c > 0) {
            p.right = insertRec(p.right, e);
        } 
        return p;
    }

    @Override
    public E eliminar(E e) {
        Holder<E> h = new Holder<>();
        root = deleteRec(root, e, h);
        if (h.set) { n--; return h.val; }
        return null;
    }
    private static final class Holder<T> { T val; boolean set; }

    private Node<E> deleteRec(Node<E> p, E e, Holder<E> h) {
        if (p == null) return null;
        int c = e.compareTo(p.key);
        if (c < 0) {
            p.left = deleteRec(p.left, e, h);
        } else if (c > 0) {
            p.right = deleteRec(p.right, e, h);
        } else {
            // encontrado
            h.val = p.key; h.set = true;
            if (p.left == null) return p.right;
            if (p.right == null) return p.left;
            Node<E> s = p.right;
            while (s.left != null) s = s.left;
            p.key = s.key;               
            p.right = deleteMin(p.right);
        }
        return p;
    }

    private Node<E> deleteMin(Node<E> p) {
        if (p.left == null) return p.right;
        p.left = deleteMin(p.left);
        return p;
    }

    @Override
    public boolean isEmpty() { return n == 0; }

    @Override
    public int size() { return n; }

    @Override
    public SimpleList<E> inOrder() {
        SimpleList<E> out = new SimpleList<>();
        inOrderRec(root, out);
        return out;
    }
    private void inOrderRec(Node<E> p, SimpleList<E> out) {
        if (p == null) return;
        inOrderRec(p.left, out);
        out.addLast(p.key);
        inOrderRec(p.right, out);
    }
}