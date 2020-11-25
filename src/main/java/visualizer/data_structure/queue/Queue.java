package visualizer.data_structure.queue;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import visualizer.data_structure.linked_list.LinkedList;

// Implemented using linkedlist
public class Queue<T> {
    private final LinkedList<T> linkedList;

    public Queue(Pane containers, ScrollPane canvas) {
        linkedList = new LinkedList<>(containers, canvas);
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }

        return linkedList.removeFirst();
    }

    public void offer(T value) {
        linkedList.addLast(value);
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}