package visualizer.data_structure.binary_search_tree;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class BinarySearchTree<T extends Comparable<T>> {
    public BinarySearchTree(Pane container, ScrollPane canvas) {
        this.container = container;
        this.canvas = canvas;
    }

    private Node<T> root;

    private final Pane container;
    private final ScrollPane canvas;

    public void add(T data) {
        root = add(root, data);
    }

    private Node<T> add(Node<T> node, T data) {
        if (root == null) {
            return new Node<>(container, data,  canvas.getWidth() / 2, 25);
        }

        else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                Node<T> temp = new Node<>(container, node, data, node.getX() - node.getWidth(), node.getY() + node.getHeight() + (node.getHeight() / 2), true);

                node.setLeft(temp);

                if (temp.getData().compareTo(root.getData()) > 0) {
                    System.out.println("Start");
                    updateRightLeft(temp);
                }
            }

            else {
                node.setLeft(add(node.getLeft(), data));
            }
        }

        else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                Node<T> temp = new Node<>(container, node, data, node.getX() + node.getWidth(), node.getY() + node.getHeight() + (node.getHeight() / 2), false);
                node.setRight(temp);

                if (temp.getData().compareTo(root.getData()) > 0) {

                }
            }

            else {
                node.setRight(add(node.getRight(), data));
            }
        }

        return node;
    }

    private void updateRightLeft(Node<T> main) {
        System.out.println("Main: " + main.getData());
        update(main, main);
    }

    private void update(Node<T> node, Node<T> main) {
        System.out.println("Current Update: " + node.getData());

        moveLeftRight(node, main);

        System.out.println(node.getData() + " has been updated");

        if (node.getParent() != null && node.getParent() != root) {
            System.out.println("Updating next: " + node.getParent().getData());

            update(node.getParent(), main);
        }
    }

    private void moveLeftRight(Node<T> node, Node<T> main) {
        if (node != null) {

            if (node.getData().compareTo(node.getParent().getData()) < 0) {

                System.out.println("Updating left: " + node.getData());

                node.update(true);
            }

            else if (node.getData().compareTo(node.getParent().getData()) > 0) {
                System.out.println("Updating not left: " + node.getData());

                node.update(false);
            }

            if (node.getRight() != null && !node.getRight().isAncestorOf(main)) {
                System.out.println("Updating Inside: " + node.getRight().getData());

                moveLeftRight(node.getRight(), main);
            }

            if (node.getLeft() != null && !node.getLeft().isAncestorOf(main)) {
                System.out.println("Updating Inside: " + node.getLeft().getData());

                moveLeftRight(node.getLeft(), main);
            }
        }
    }

//    private void updateRightLeft(Node<T> node) {
//
//    }

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
