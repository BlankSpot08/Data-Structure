package queue;

import array.Array;

// Implemented using dynamic array
public class Queue<T> {
    private final Array<T> array;
    private int front;
    private int back;

    public Queue() {
        array = new Array<>();
        back = 0;
        front = 0;
    }

    public T enqueue(T value) {
        array.add(value);
        front =+ 1;
        return value;
    }

    public T dequeue() {
        return array.removeAt(back++);
    }

    public T peek() {
        return array.get(front);
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }
}