package queue;

import java.util.*;

public class PriorityQueue<T extends Comparable<T>> {
    // Imported List, Map, HashMap, TreeSet, ArrayList

    // Pre Defined List
    private List<T> heap = null;

    // Pre Defined HashMap and Tree Set in the value
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    private int heapSize = 0;
    private int heapCapacity = 0;

    public void PriorityQueue(int initialCapacity) {
        heap = new ArrayList<>(initialCapacity);
    }

    public void PriorityQueue() {
        heap = new ArrayList<>(1);
    }

    public boolean isMinHeap(int i) {
        if (i >= heapSize) {
            return true;
        }

        int left = (i * 2) + 1;
        int right = (i * 2) + 2;

        if (left < heapSize && !less(i, left)) {
            return false;
        }

        if (right < heapSize && !less(i, right)) {
            return false;
        }

        return isMinHeap(left) && isMinHeap(right);
    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }

        Integer index = mapGet(value);
        if (index != null) {
            removeAt(index);
        }

        return index != null;
    }

    public boolean less(int i, int j) {
        T firstValue = heap.get(i);
        T secondValue = heap.get(j);

        return firstValue.compareTo(secondValue) <= 0;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        if (heapSize < heapCapacity) {
            heap.set(heapSize, value);
        }

        else {
            add(value);
            heapCapacity++;
        }

        mapAdd(value, heapSize);

        swim(heapSize);
        heapSize++;
    }

    public boolean contains(T value) {
        if (value == null) {
            return false;
        }

        return map.containsKey(value);
    }


    public T poll() {
        return removeAt(0);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public void clear() {
        for (int i = 0; i < heapSize; i++) {
            heap.set(i, null);
        }

        heapSize = 0;
        map.clear();
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        }

        else {
            set.add(index);
        }
    }

    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) {
            return set.last();
        }

        return null;
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    private void mapSwap(T firstValue, T secondValue, int i, int j) {
        Set<Integer> set1 = map.get(firstValue);
        Set<Integer> set2 = map.get(secondValue);

        set1.remove(i);
        set2.remove(j);

        set1.add(j);
        set2.add(i);
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        set.remove(index);

        if (set.size() == 0) {
            map.remove(value);
        }
    }

    private void swim(int i) {
        int parent = (i-1) / 2;

        while(i > 0 && less(i, parent)) {
            swap(parent, i);

            i = parent;
            parent = (i - 2) / 2;
        }
    }

    private T removeAt(int i) {
        if (isEmpty()) {
            return null;
        }

        heapSize--;
        T temp = heap.get(i);

        heap.set(heapSize, null);
        mapRemove(temp, heapSize);

        if (i == heapSize) {
            return temp;
        }

        T value = heap.get(i);

        sink(i);

        if (heap.get(i).equals(value)) {
            swim(i);
        }

        return temp;
    }

    private void sink(int i) {
        while (true) {
            int left = (i * 2) + 1;
            int right = (i * 2) + 2;
            int smallest = left;

            if (right < heapSize && less(right, left)) {
                smallest = right;
            }

            if (left >= heapSize || less(i, smallest)) {
                break;
            }

            swap(smallest, i);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        T firstElement = heap.get(i);
        T secondElement = heap.get(j);

        heap.set(i, firstElement);
        heap.set(j, secondElement);

        mapSwap(firstElement, secondElement, i, j);
    }
}
