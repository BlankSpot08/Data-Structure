package hashTable.seperateChaining;

import hashTable.Entry;
import linkedList.LinkedList;

import java.util.Arrays;

public class HashTableSeperateChaining<K, V> {
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>> []table;

    public HashTableSeperateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeperateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    private HashTableSeperateChaining(int capacity, double maxLoadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity");
        }

        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)) {
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        }

        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());

        return bucketSeekEntry(bucketIndex, key) !=  null;
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    public V insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }

        Entry<K, V> entry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(key.hashCode());

        return bucketInsertEntry(bucketIndex, entry);
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);

        if (entry != null) {
            return entry.value;
        }

        return null;
    }

    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int bucketIndex = normalizeIndex(key.hashCode());

        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);

        if (entry != null) {
            LinkedList<Entry<K, V>> linkedList = table[bucketIndex];
            linkedList.remove(entry);
            size--;

            return entry.value;
        }

        return null;
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> linkedList = table[bucketIndex];

        if (linkedList == null) {
            table[bucketIndex] = new LinkedList<>();
        }

        Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existingEntry == null) {
            table[bucketIndex].add(entry);
            size++;

            if (size > threshold) {
                resizeTable();
            }

            return null;
        }

        else {
            V oldValue = existingEntry.value;
            existingEntry.value = entry.value;

            return oldValue;
        }
    }

    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) {
            return null;
        }

        LinkedList<Entry<K, V>> linkedList = table[bucketIndex];
        if (linkedList == null) {
            return null;
        }

        for (Entry<K, V> entry : linkedList.toArray()) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }

        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>> []newTable = new LinkedList[capacity];

        for (int i = 0; i < newTable.length; i++) {
            if (newTable[i] != null) {
                for (Entry<K, V> entry : newTable[i].toArray()) {
                    int bucketIndex = normalizeIndex(entry.hash);

                    LinkedList<Entry<K, V>> linkedList = newTable[bucketIndex];

                    if (linkedList == null) {
                        newTable[bucketIndex] = new LinkedList<>();
                    }

                    linkedList.add(entry);
                }

                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }
}
