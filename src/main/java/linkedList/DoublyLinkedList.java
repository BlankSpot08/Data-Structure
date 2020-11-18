package linkedList;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void clear() {
        Node<T> temp = head;

        while (temp != null) {
            Node<T> next = temp.getNext();
            temp.setNext(null);
            temp.setData(null);
            temp.setPrevious(null);
            temp = null;
        }

        head = tail = null;
        length = 0;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(T data) {
        addLast(data);
    }

    public void addFirst(T data) {
        Node<T> node = new Node<>();
        node.setData(data);

        if (isEmpty()) {
            head = tail = node;
        }

        else {
            node.setNext(head);
            head.setPrevious(node);

            head = node;
        }

        length++;
    }

    public void addLast(T data) {
        Node<T> node = new Node<>();
        node.setData(data);

        if (isEmpty()) {
            head = tail = node;
        }

        else {
            tail.setNext(node);
            node.setPrevious(tail);

            tail = node;
        }

        length++;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        return head.getData();
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        return tail.getData();
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        T data = head.getData();

        head = head.getNext();
        length--;

        if (isEmpty()) {
            tail = null;
        }

        else {
            head.setPrevious(null);
        }

        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        T data = tail.getData();

        tail = tail.getPrevious();
        length--;

        if (isEmpty()) {
            head = null;
        }

        else {
            tail.setNext(null);
        }

        return data;
    }

    private T remove(Node<T> node) {
        if (node.getPrevious() == null) {
            return removeFirst();
        }

        if (node.getNext() == null) {
            return removeLast();
        }

        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());

        T data = node.getData();

        node.setData(null);
        node.setNext(null);
        node.setPrevious(null);

        length--;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException();
        }

        Node<T> temp;
        if (index < length / 2) {
            for (temp = head; index > 0; index--) {
                temp = temp.getNext();
            }
        }

        else {
            for (temp = tail; index > 0; index--) {
                temp = temp.getPrevious();
            }
        }

        return remove(temp);
    }

    public boolean remove(T value) {
        Node<T> temp = head;

        while (temp.getNext() != null) {
            if (temp.getData() == value) {
                remove(temp);
                return true;
            }

            temp = temp.getNext();
        }

        return temp.getData() == value && (remove(temp) == temp);
    }

    public int indexOf(T value) {
        Node<T> temp = head;
        int index = 0;

        while (temp.getNext() != null) {

            if (temp.getData() == value) {
                return index;
            }

            index++;
            temp = temp.getNext();
        }

        return temp.getData() == value ? index : -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder("[");

        Node<T> tempNode = head;
        while(tempNode.getNext() != null) {
            temp.append(tempNode.getData()).append(", ");
            tempNode = tempNode.getNext();
        }

        return temp.append(tempNode.getData()).append("]").toString();
    }
}