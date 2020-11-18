package tree.binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public void add(T data) {
        root = add(root, data);
    }

    private Node<T> add(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }

        else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        }

        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(node.getRight(), data));
        }

        return node;
    }

    public void remove(T data) {
        root = remove(root, data);
    }

    private Node<T> remove(Node<T> node, T data) {
        if (node != null) {
            if (node.getData().compareTo(data) > 0) {
                node.setLeft(remove(node.getLeft(), data));
            }

            else if (node.getData().compareTo(data) < 0) {
                node.setRight(remove(node.getRight(), data));
            }

            else if (node.getData().compareTo(data) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    return null;
                }

                else if (node.getLeft() == null) {
                    return node.getRight();
                }

                else if (node.getRight() == null) {
                    return node.getLeft();
                }

                else if (node.getLeft() != null && node.getRight() != null) {
                    return minimum();
                }
            }
        }

        return node;
    }

    public Node<T> maximum() {
        return maximum(root.getLeft());
    }

    private Node<T> maximum(Node<T> node) {
        if (node != null) {
            if (node.getRight() != null) {
                return maximum(node.getRight());
            }
        }

        return node;
    }

    public Node<T> minimum() {
        return minimum(root.getRight());
    }

    private Node<T> minimum(Node<T> node) {
        if (node != null) {
            if (node.getLeft() != null) {
                return minimum(node.getLeft());
            }
        }

        return node;
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.getLeft());
            System.out.print(node.getData() + " ");
            inorder(node.getRight());
        }
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }

    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(Node<T> node) {
        if (node != null) {
            postorder(node.getLeft());
            postorder(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public boolean search(T data) {
        return search(root, data);
    }

    private boolean search(Node<T> node, T data) {
        if (node == null) {
            return false;
        }

        if (node.getData().compareTo(data) < 0) {
            return search(node.getRight(), data);
        }

        else if (node.getData().compareTo(data) > 0) {
            return search(node.getLeft(), data);
        }

        return node.getData() == data;
    }
}
