package linkedList;

public class Node<T> {
    public Node() {
        next = null;
        previous = null;
        data = null;
    }

    private Node<T> next;
    private Node<T> previous;
    private T data;

    protected void setNext(Node<T> next) {
        this.next = next;
    }

    protected Node<T> getNext() {
        return next;
    }

    protected void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    protected Node<T> getPrevious() {
        return previous;
    }

    protected void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
