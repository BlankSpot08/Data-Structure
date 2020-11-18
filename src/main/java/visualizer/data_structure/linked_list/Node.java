package visualizer.data_structure.linked_list;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Node<T> {
    public Node(ScrollPane canvas) {
        isRoot = getPrevious() == null;

        next = null;
        previous = null;
        data = null;

        height = 55;
        width = 55;
        y = (float) (canvas.getHeight() / 2) - width / 2;
        x = 10;
    }

    protected void dismiss(Pane container) {
        container.getChildren().removeAll(rectangle, valueLabel, pointer);

        rectangle = null;
        valueLabel = null;
        pointer = null;
    }

    protected void dismissPointer(Pane container) {
        container.getChildren().remove(pointer);

        pointer = null;
    }

    protected void update(double x) {
        rectangle.setX(x);

        valueLabel.setLayoutX(x + width / 2 - 5);

        if (pointer != null) {
            pointer.setStartX(x + width + 10);
            pointer.setEndX(x + width * 2 - 10);
        }
        this.x = x;
    }

    public void showArrow(Pane containers, double x, double y) {
        Pane test = new Pane();
        pointer = new Line(x + width + 10,
                y + height / 2,
                x + width * 2 - 10,
                y + height / 2);
        test.getChildren().add(pointer);

        containers.getChildren().add(pointer);
    }

    protected void show(Pane containers, double x, double y) {
        rectangle = new Rectangle(x, y, width, height);
        rectangle.setId("rectangle");

        this.x = x;
        this.y = y;

        valueLabel = new Label(String.valueOf(getData()));
        valueLabel.textProperty().bind(value);

        valueLabel.setLayoutX(x + width / 2 - 5);
        valueLabel.setLayoutY(y + height / 2 - 7.5);

        containers.getChildren().addAll(rectangle, valueLabel);
    }

    private Rectangle rectangle;
    private Label valueLabel;
    private Line pointer;

    private double x;
    private double y;
    private float width;
    private float height;

    private StringProperty value = new SimpleStringProperty("0");

    private boolean isRoot;

    private Node<T> next;
    private Node<T> previous;
    private T data;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        rectangle.setX(x);
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

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
        value.set(String.valueOf(data));
    }

    public T getData() {
        return data;
    }
}
