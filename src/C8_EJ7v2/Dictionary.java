package C8_EJ7v2;

public interface Dictionary<K,V> { 
	public int size(); 
	public boolean isEmpty(); 
	public V[] get(K k); 
	public void put(K k, V v); 
	public V[] remove(K k); 
	public V remove(K k, V v); 
	public K[] keys(); 
	public Entry<K,V[]>[] entries(); 
}
