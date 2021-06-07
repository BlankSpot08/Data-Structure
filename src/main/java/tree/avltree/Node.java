package tree.avltree;

public class Node<T> {
    public Node(T data) {
        this.data = data;
        left = right = null;
    }

    private Node<T> left;
    private Node<T> right;
    private T data;

    private int height;
    private int balanceFactor;

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }
}
