package tree.binarysearchtree;

public class Node<T> {
    public Node(T data) {
        this.data = data;
        left = right = null;
    }

    private Node<T> left;
    private Node<T> right;
    private T data;

    protected void setLeft(Node<T> left) {
        this.left = left;
    }

    protected Node<T> getLeft() {
        return this.left;
    }

    protected void setRight(Node<T> right) {
        this.right = right;
    }

    protected Node<T> getRight() {
        return this.right;
    }

    protected void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
