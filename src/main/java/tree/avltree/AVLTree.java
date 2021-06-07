package tree.avltree;

public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;

    private int nodeCount = 0;

    public int height() {
        if (root == null) {
            return 0;
        }

        return root.getHeight();
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T data) {
        return contains(data, root);
    }

    private boolean contains(T data, Node<T> node) {
        if (node == null) {
            return false;
        }

        final int compareResult = node.getData().compareTo(data);

        if (compareResult > 0) {
            return contains(data, node.getRight());
        }

        if (compareResult < 0) {
            return contains(data, node.getLeft());
        }

        return true;
    }

    public boolean insert(T data) {
        if (data == null) {
            return false;
        }

        if (!contains(data)) {
            nodeCount++;
            return true;
        }

        return false;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }

        final int compareResult = node.getData().compareTo(data);

        if (compareResult > 0) {
            return insert(data, node.getLeft());
        }

        if (compareResult < 0) {
            return insert(data, node.getRight());
        }

        update(node);

        return balance(node);
    }

    private void update(Node<T> node) {
        int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
        int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();

        node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));

        node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
    }

    private Node<T> balance(Node<T> node) {
        if (node.getBalanceFactor() == -2) {
            if (node.getLeft().getBalanceFactor() <= 0) {
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if (node.getBalanceFactor() == 2) {
            if (node.getRight().getBalanceFactor() >= 0) {
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }

        return node;
    }

    private Node<T> leftLeftCase(Node<T> node) {
        return rightRotation(node);
    }

    private Node<T> leftRightCase(Node<T> node) {
        node.setLeft(leftRotation(node.getLeft()));

        return rightRotation(node);
    }

    private Node<T> rightRightCase(Node<T> node) {
        return leftRotation(node);
    }

    private Node<T> rightLeftCase(Node<T> node) {
        node.setRight(rightRotation(node.getRight()));

        return leftRotation(node);
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> newParent = node.getRight();

        node.setRight(newParent.getRight());
        newParent.setLeft(node);

        update(node);
        update(newParent);

        return newParent;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParent = node.getLeft();

        node.setLeft(newParent.getLeft());
        newParent.setRight(node);

        update(node);
        update(newParent);

        return newParent;
    }

    public boolean remove(T data) {
        if (data == null) {
            return false;
        }

        if (contains(data)) {
            root = remove(data, root);
            nodeCount--;

            return true;
        }

        return false;
    }

    private Node<T> remove(T data, Node<T> node) {
        if (node == null) {
            return null;
        }

        final int compareResult = node.getData().compareTo(data);

        if (compareResult > 0) {
            return remove(data, node.getLeft());
        }

        else if (compareResult < 0) {
            return remove(data, node.getRight());
        }

        else {
            if (node.getRight() == null) {
                return node.getLeft();
            }

            else if (node.getLeft() == null) {
                return node.getRight();
            }

            else if (node.getLeft() != null && node.getRight() != null) {
                if (node.getLeft().getHeight() > node.getRight().getHeight()) {
                    T successorValue = findMax(node.getLeft());
                    node.setData(successorValue);

                    node.setLeft(remove(successorValue, node));
                } else {
                    T successorValue = findMin(node.getRight());
                    node.setData(successorValue);

                    node.setRight(remove(successorValue, node));
                }
            }

            else if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            return null;
        }
    }

    private T findMin(Node<T> node) {
        if (node.getLeft() == null) {
            return node.getData();
        }

        return findMin(node.getLeft());
    }

    private T findMax(Node<T> node) {
        if (node.getRight() == null) {
            return node.getData();
        }

        return findMax(node.getRight());
    }
}
