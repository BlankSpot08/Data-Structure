package array;

@SuppressWarnings("unchecked")
public class Array<T> {
    private T[] container;
    private int length;
    private int capacity;

    public Array() {
        capacity = 16;
        length = 0;

        container = (T[]) new Object[capacity];
    }

    public Array(int capacity) throws IllegalAccessException {
        if (capacity < 0) {
            throw new IllegalAccessException("Illegal Capacity: " + capacity);
        }

        container = (T[]) new Object[capacity];

    }

    public void add(T value) {
        if (length + 1>= capacity) {
            if (capacity == 0) {
                capacity = 1;
            }

            else {
                capacity *= 2;
            }

            T[] tempT = (T[]) new Object[capacity];

            for (int i = 0; i < length; i++) {
                tempT[i] = container[i];
            }

            container = tempT;
        }

        container[length++] = value;
    }

    public T get(int index) {
        return container[index];
    }

    public void set(int index, T value) {
        container[index] = value;
    }

    public T removeAt(int index) {
        if (index < 0 || index > capacity) {
            throw new IndexOutOfBoundsException();
        }

        T data = container[index];

        T[] tempT = (T[]) new Object[capacity];

        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i != index) {
                tempT[i] = container[j];
            }

            else {
                tempT[i] = container[++j];
            }
        }

        container = tempT;
        length--;

        return data;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        int i;
        for (i = 0; i < length; i++) {
            if (container[i] == value) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        container = (T[]) new Object[capacity];
        length = 0;
        capacity = 16;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public String toString() {
        if (length != 0) {
            StringBuilder temp = new StringBuilder("[ ");

            for (int i = 0; i < length - 1; i++) {
                temp.append(container[i]).append(", ");
            }

            return temp.toString() + container[length - 1] + " ]";
        }

        else {
            return "[]";
        }
    }
}
