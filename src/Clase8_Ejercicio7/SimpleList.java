package Clase8_Ejercicio7;

public class SimpleList<E> implements Iterable<E> {
    private static final class Node<E> {
        E elem; Node<E> next;
        Node(E e) { this.elem = e; }
    }
    private Node<E> head, tail; int size;


    public void addLast(E e) {
        Node<E> n = new Node<>(e);
        if (head == null) { head = tail = n; }
        else { tail.next = n; tail = n; }
        size++;
    }
    public int size() { return size; }
    public boolean isEmpty(){ return size==0; }


    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            Node<E> cur = head;
            public boolean hasNext() { return cur != null; }
            public E next() { E r = cur.elem; cur = cur.next; return r; }
        };
    }
}