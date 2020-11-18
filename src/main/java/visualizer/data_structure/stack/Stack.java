package visualizer.data_structure.stack;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import visualizer.data_structure.linked_list.LinkedList;

import java.util.Arrays;
import java.util.EmptyStackException;

// Implemented using linkedlist
public class Stack<T> {
    LinkedList<T> linkedList;

//    public Stack(T initialValue) {
//        linkedList.addFirst(initialValue);
//    }

    Pane containers;

    public Stack(Pane containers, ScrollPane canvas) {
        linkedList = new LinkedList<>(containers, canvas);
        this.containers = containers;
    }

    public T push(T data) {
        linkedList.addLast(data);
        return data;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return linkedList.removeLast();
    }

    public int search(T data) {
        return linkedList.indexOf(data);
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return linkedList.peekLast();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size();
    }
}
