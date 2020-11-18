package stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Stack<T> {
    public T[] array;
    public int size;
    private final int defaultSize = 10;

    private int top = 0;

    public Stack() {
        size = defaultSize;
        array = (T[]) Array.newInstance(Object.class, this.size);
    }

    public T pop() {
        if (top != 0) {
            T value = array[top];

            array[top] = null;
            top--;

            return value;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    public T push(T value) {
        if (top == size) {
            int tempSize = this.size;

            T[] tempArray = array;

            this.size += 10;
            array = (T[]) Array.newInstance(Object.class, this.size);

            IntStream.range(0, tempSize).forEach(i -> array[i] = tempArray[i]);
        }

        array[top++] = value;

        return value;
    }

    public T peek() {
        return array[top - 1];
    }

    public int getSize() {
        return top;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
