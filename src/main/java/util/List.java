package util;

import java.util.Collection;

public interface List<T> {
    void add(T data);
    void add(int index, T data);
    void addLast(T data);
    void addAll(Collection<? extends List<T>> list);
    void addFirst(T data);
    T removeFirst();
    void remove(int index);
    T removeLast();
    T remove();
    void remove(T data);
    void removeFirstOccurrence(T data);
    T[] toArray();
    int size();
    void set(int index, T data);
    void removeLastOccurrence(T data);
}
