package visualizer.data_structure.binary_search_tree;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private T data;

    private Circle circle;
    private Label valueLabel;
    private Line leftPointer;
    private Line rightPointer;
    private Pane design;
    private StringProperty value;

    private DoubleProperty x;
    private DoubleProperty y;

    private double width = 25;
    private double height = 25;

    public Node(Pane container, T data, double x, double y) {
        this.data = data;

        value = new SimpleStringProperty(String.valueOf(data));
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);

        container.getChildren().add(show());
    }

    public Node (Pane container, Node<T>  parent, T data, double x, double y, boolean left) {
        this.data = data;
        this.parent = parent;

        value = new SimpleStringProperty(String.valueOf(data));
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);

        container.getChildren().addAll(show(), showArrow(left));
    }

    protected void update(boolean left) {
        x.set(design.getLayoutX() + width);

        if (left) {
            System.out.println("Its LEFT ARROW");

            parent.leftPointer.setStartX(parent.x.getValue() + width);
            parent.leftPointer.setEndX(parent.leftPointer.getEndX() + width);
        }

        else {
            System.out.println("Its RIGHT ARROW");

            parent.rightPointer.setStartX(parent.x.getValue() + width);
            parent.rightPointer.setEndX(parent.rightPointer.getEndX() + width);
        }
    }

    protected Line showArrow(boolean left) {
        double adjustment = 7;

        if (left) {
            return parent.leftPointer = new Line(parent.x.getValue(), parent.y.getValue() + height, (x.getValue() + width / 2) + adjustment, y.getValue() - adjustment);
        }

        else {
            return parent.rightPointer = new Line(parent.x.getValue() + width, parent.y.getValue() + height, (x.getValue() + width / 2) - adjustment, y.getValue() - adjustment);
        }
    }
    
    protected Pane show() {
        design = new Pane();

        design.layoutXProperty().bind(this.x);
        design.layoutYProperty().bind(this.y);

        design.setStyle("-fx-background-color: green");

        design.setPrefHeight(height * 2);
        design.setPrefWidth(width * 2);

        circle = new Circle(design.getPrefWidth() / 2, design.getPrefHeight() / 2, width);
        circle.setId("circle");

        height = design.getPrefHeight();
        width = design.getPrefWidth();

        valueLabel = new Label(String.valueOf(getData()));

        valueLabel.setLayoutX(circle.getCenterX() - (valueLabel.getText().length() + 1));
        valueLabel.setLayoutY(circle.getCenterY() - 7.5);

        design.getChildren().addAll(circle, valueLabel);

        return design;
    }

    public double getX()  {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

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

    public T getData() {
        return this.data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Pane getDesign() {
        return design;
    }

    public void setDesign(Pane design) {
        this.design = design;
    }

    protected boolean isAncestorOf(Node<T> descendant) {
        return isAncestorOf(descendant, this);
    }

    private boolean isAncestorOf(Node<T> descendant, Node<T> ancestor) {
        if (descendant.parent != null) {
            if (descendant == ancestor) {
                return true;
            }

            return isAncestorOf(descendant.parent, ancestor);
        }

        return false;
    }
}
