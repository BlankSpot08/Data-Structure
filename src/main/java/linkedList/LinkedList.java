package linkedList;

@SuppressWarnings("unchecked")
public class LinkedList <T> {
    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    // This method Appends the specified element to the end of this list.
    public void add(T data) {
        addLast(data);
    }

    // This method Inserts the specified element at the beginning of this list.
    public void addFirst(T data) {
        Node<T> node = new Node<>();
        node.setData(data);

        if (isEmpty()) {
            head = node;
            tail = node;
        }

        else {
            node.setNext(head);
            head = node;
        }

        length++;
    }

    // This method Inserts the specified element at the specified position in this list.
    public void add(int index, T data) {
        Node<T> node = new Node<>();
        node.setData(data);
        node.setNext(null);

        if (index == 0) {
            addFirst(data);
        }

        else if (index == length) {
            addLast(data);
        }

        else {
            Node<T> n = head;

            while (index != 1) {
                n = n.getNext();
                index--;
            }

            node.setNext(n.getNext());
            n.setNext(node);

            length++;
        }
    }

    // This method Appends the specified element to the end of this list.
    public void addLast(T data) {
        Node<T> node = new Node<>();
        node.setData(data);

        if (isEmpty()) {
            addFirst(data);

            return;
        }

        else {
            tail.setNext(node);
            tail = node;
            tail.setNext(null);
        }

        length++;
    }

    /*
     This method Appends all of the elements in the specified collection to the end of this list,
     in the order that they are returned by the specified collection’s iterator.
    */
    public void addAll(LinkedList<T> linkedList) {
        Node<T> temp = linkedList.head;

        while (temp.getNext() != null) {
            this.add(temp.getData());
            temp = temp.getNext();
        }

        this.add(temp.getData());
    }

    // This method removes and returns the first element from this list.
    public T removeFirst() {
        T data = head.getData();
        head = head.getNext();

        length--;

        if (isEmpty()) {
            head = null;
        }

        return data;
    }

    // This method removes the element at the specified position in this list.
    public void remove(int index) {
        if (index == length - 1) {
            removeLast();
        }

        else if (index == 0 && length > 0) {
            removeFirst();
        }

        else {
            Node<T> temp = head;
            Node<T> nextTemp = null;

            while (index != 0) {
                temp = temp.getNext();
                index--;
            }

            nextTemp = temp.getNext();
            temp.setNext(nextTemp.getNext());

            length--;
        }
    }

    // This method removes and returns the last element from this list.
    public T removeLast() {
        Node<T> node = new Node<>();
        Node<T> temp = head;

        T data = head.getData();

        if (length != 1) {
            while (temp.getNext().getNext() != null) {
                temp = temp.getNext();
            }

            temp.setNext(node.getNext());
            length--;
        }

        else {
            removeFirst();
        }

        if (isEmpty()) {
            head = null;
        }

        return data;
    }

    // This method retrieves and removes the head (first element) of this list.
    public T remove() {
        return removeFirst();
    }

    // This method removes the first occurrence of the specified element from this list, if it is present.
    public void remove(T data) {
        remove(indexOf(data));
    }

    // This method removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
    public void removeFirstOccurrence(T data) {
        remove(indexOf(data));
    }

    // This method returns an array containing all of the elements in this list in proper sequence (from first to last element).
    public T[] toArray() {
        Node<T> temp = head;
        T[] tempArray = (T[]) new Object[length];

        for (int i = 0; i < length; i++) {
            tempArray[i] = temp.getData();
            temp = temp.getNext();
        }

        return tempArray;
    }

    // This method returns the number of elements in this list.
    public int size() {
        return length;
    }

    // This method replaces the element at the specified position in this list with the specified element.
    public void set(int index, T data) {
        Node<T> temp = head;

        while (index != 0) {

            temp = temp.getNext();
            index--;
        }

        temp.setData(data);
    }

    /*
     This method removes the last occurrence of the specified element in this list
     (when traversing the list from head to tail).
    */
    public void removeLastOccurrence(T data) {
        remove(lastIndexOf(data));
    }

    // This method Pushes an element onto the stack represented by this list.
    public void push(T element) {
        addFirst(element);
    }

    // This method Pops an element from the stack represented by this list.
    public T pop() {
        Node<T> temp = head;

        removeFirst();

        return temp.getData();
    }

    //  This method retrieves and removes the last element of this list, or returns null if this list is empty.
    public T pollLast() {
        if (isEmpty()) {
            return null;
        }

        return removeLast();
    }

    // This method retrieves and removes the first element of this list, or returns null if this list is empty.
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }

        return removeFirst();
    }

    // This method retrieves and removes the head (first element) of this list.
    public T poll() {
        return removeLast();
    }

    // This method retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
    public T peekLast() {
        return tail.getData();
    }

    // This method retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
    public T peekFirst () {
        return head.getData();
    }

    // This method retrieves, but does not remove, the head (first element) of this list.
    public T peek() {
        return head.getData();
    }

    // This method Inserts the specified element at the front of this list.
    public boolean offerFirst(T data) {
        addFirst(data);
        return true;
    }

    // This method Inserts the specified element at the end of this list.
    public boolean offerLast(T data) {
        addLast(data);
        return true;
    }

    // This method Adds the specified element as the tail (last element) of this list.
    public boolean offer(T data) {
        addLast(data);
        return true;
    }

    /*
     This method returns the index of the last occurrence of the specified element in this list,
     or -1 if this list does not contain the element.
    */
    public int lastIndexOf(T element) {
        Node<T> temp = head;

        int i = 0;
        int j = 0;

        while (temp.getNext() != null) {
            if (temp.getData() == element) {
                j = i;
            }

            i++;
            temp = temp.getNext();
        }

        return temp.getData() == element ? i : j;
    }

    /*
     This method returns the index of the first occurrence of the specified element in this list,
     or -1 if this list does not contain the element.
    */
    public int indexOf(T element) {
        Node<T> temp = head;

        int i = 0;
        while (temp.getNext() != null) {
            if (temp.getData() == element) {
                return i;
            }

            i++;
            temp = temp.getNext();
        }

        return temp.getData() == element ? i : -1;
    }

    // This method returns the element at the specified position in this list.
    public T get(int index) {
        Node<T> temp = head;

        while (index > 0) {
            temp = temp.getNext();
            index--;
        }

        return temp.getData();
    }

    // This method returns the first element in this list.
    public T getFirst() {
        return head.getData();
    }

    // This method returns the last element in this list.
    public T getLast() {
        return tail.getData();
    }

    //  This method retrieves, but does not remove, the head (first element) of this list.
    public T element() {
        return head.getData();
    }

    // This method returns true if this list contains the specified element.
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    // This method returns a shallow copy of this LinkedList.
    public LinkedList<T> clone() {
        return this;
    }

    // This method removes all of the elements from this list.
    public void clear() {
        Node<T> temp = head;

        while (temp != null) {
            Node<T> next = temp.getNext();
            temp.setData(null);
            temp.setNext(null);
            temp.setPrevious(null);
            temp = next;
        }

        head = tail = null;
        length = 0;
    }

    //  This method Appends the specified element to the end of this list.
    public boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        if (!isEmpty()) {
            Node<T> temp = head;

            StringBuilder tempString = new StringBuilder("[");

            while (temp.getNext() != null) {
                tempString.append(temp.getData()).append(", ");
                temp = temp.getNext();
            }

            return tempString.append(temp.getData()).append("]").toString();
        }

        return "[]";
    }
}
