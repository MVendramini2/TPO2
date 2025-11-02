package C8_EJ7;


public class LinkedDictionaryABB<K extends Comparable<K>, V extends Comparable<V>> implements Dictionary<K, V> {

    private final class KeyNode {
        K key; ABB<V> values; KeyNode prev, next;
        KeyNode(K k) { key = k; values = new ABB<>(); }
    }
    private final class DictEntry implements Entry<K, Iterable<V>> {
        private final K k; private final Iterable<V> it;
        DictEntry(K k, Iterable<V> it) { this.k = k; this.it = it; }
        public K getKey() { return k; }
        public Iterable<V> getValue() { return it; }
    }


    private KeyNode head, tail; int nKeys;


    public int size() { return nKeys; }
    public boolean isEmpty() { return nKeys == 0; }


    private KeyNode findNode(K k) {
        for (KeyNode p = head; p != null; p = p.next) if (k.compareTo(p.key) == 0) return p;
        return null;
    }


    public Iterable<V> get(K k) {
        KeyNode n = findNode(k);
        return (n == null) ? null : n.values.inOrder();
    }


    public void put(K k, V v) {
        if (k == null || v == null) throw new IllegalArgumentException("Ni clave ni valor pueden ser null");
        KeyNode n = findNode(k);
        if (n == null) {
            n = new KeyNode(k);
            if (head == null) head = tail = n; else { tail.next = n; n.prev = tail; tail = n; }
            nKeys++;
        }
        n.values.insertar(v); 
    }


    public Iterable<V> remove(K k) {
        KeyNode n = findNode(k);
        if (n == null) return null;
        SimpleList<V> vals = n.values.inOrder();
        unlink(n);
        return vals;
    }


    public V remove(K k, V v) {
        KeyNode n = findNode(k);
        if (n == null) return null;
        V eliminado = n.values.eliminar(v);
        if (eliminado != null && n.values.isEmpty()) unlink(n);
        return eliminado;
    }


    private void unlink(KeyNode n) {
        if (n.prev != null) n.prev.next = n.next; else head = n.next;
        if (n.next != null) n.next.prev = n.prev; else tail = n.prev;
        n.prev = n.next = null; n.values = null; nKeys--;
    }


    public SimpleList<K> keys() {
        SimpleList<K> out = new SimpleList<>();
        for (KeyNode p = head; p != null; p = p.next) out.addLast(p.key);
        return out;
    }


    public SimpleList<Entry<K, Iterable<V>>> entries() {
        SimpleList<Entry<K, Iterable<V>>> out = new SimpleList<>();
        for (KeyNode p = head; p != null; p = p.next) out.addLast(new DictEntry(p.key, p.values.inOrder()));
        return out;
    }
}