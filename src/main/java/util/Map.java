package util;

public interface Map<K, V> {
    int size();
    boolean isEmpty();
    boolean hasKey(K key);
    boolean containsKey(K key);
    void clear();
    V add(K key, V value);
    V put(K key, V value);
    V insert(K key, V value);
    V get(K key);
    V remove(K key);
}
