package hashTable;

public class Entry<K, V> {
    public int hash;
    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> entry) {
        if (hash != entry.hash) {
            return false;
        }

        return key == entry.key;
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}
