package C8_EJ7;

public interface Dictionary<K extends Comparable<K>, V extends Comparable<V>> {
    int size(); 
    boolean isEmpty();
    Iterable<V> get(K k); 
    void put(K k, V v); 
    Iterable<V> remove(K k); 
    V remove(K k, V v); 
    SimpleList<K> keys();
    SimpleList<Entry<K, Iterable<V>>> entries();
}