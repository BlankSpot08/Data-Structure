package visualizer.data_structure;

import com.sun.javafx.css.Size;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class Array_Tab {
    private Button createButton;
    private Button setValueButton;
    private TextField sizeTextField;
    private TextField indexTextField;
    private TextField newValueTextField;
    private GridPane gridPane;
    private HBox createNewArrayHBox;
    private HBox setIndexHBox;
    private VBox mainPane;
    private ScrollPane canvas;
    public Pane containers;

    private StringProperty[] intArray;

    public Array_Tab(ScrollPane canvas) {
        this.canvas = canvas;

        containers = new Pane();
    }

    private BorderPane createInformationPane(BorderPane informationPane, TextArea informationTextArea) {
        String information = "An array is a group of like-typed variables that are referred to by a " +
                "common name.Arrays in Java work differently than they do in C/C++. Following are some " +
                "important point about Java arrays.\n\nArray can contains primitives (int, char, etc) " +
                "as well as object (or non-primitives) references of a class depending on the definition " +
                "of array. In case of primitives data types, the actual values are stored in contiguous " +
                "memory locations. In case of objects of a class, the actual objects are stored in heap " +
                "segment.";

        informationTextArea.setText(information);

        return informationPane;
    }

    private HBox createSetAndValueHBox() {
        setIndexHBox = new HBox();
        setIndexHBox.setId("together-nodes");

        indexTextField = new TextField();
        indexTextField.setPromptText("Index");

        newValueTextField = new TextField();
        newValueTextField.setPromptText("Value");

        setValueButton = new Button("Set Value");

        setValueButton.setOnAction(e -> {
            setValue(Integer.parseInt(indexTextField.getText()), Integer.parseInt(newValueTextField.getText()));
        });

        setIndexHBox.getChildren().addAll(newValueTextField, indexTextField, setValueButton);

        return setIndexHBox;
    }

    private HBox createNewHBox() {
        createNewArrayHBox = new HBox();
        createNewArrayHBox.setId("together-nodes");

        sizeTextField = new TextField();
        sizeTextField.setPromptText("Size");

        createButton = new Button("New Array");

        createButton.setOnAction(e -> {
            createArray(Integer.parseInt(sizeTextField.getText()));
        });

        createNewArrayHBox.getChildren().addAll(sizeTextField, createButton);

        return createNewArrayHBox;
    }

    public GridPane createGridPane() {
        gridPane = new GridPane();
        gridPane.setId("grid-pane");

        gridPane.add(createNewHBox(), 0, 0);
        gridPane.add(createSetAndValueHBox(), 1, 0);

        return gridPane;
    }

    public VBox createMainPane(BorderPane informationPane, TextArea informationTextArea) {
        mainPane = new VBox();
        mainPane.setId("main-pane");

        mainPane.getChildren().addAll(createGridPane(),
                createInformationPane(informationPane, informationTextArea));

        return mainPane;
    }
    
    // Methods

    private void setValue(int index, int value) {
        intArray[index].set(String.valueOf(value));
    }

    private void createArray(int size) {
        containers.getChildren().clear();

        intArray = new StringProperty[size];

        double width = 55;
        double height = 55;
        double x = 10;
        double y = canvas.getHeight() / 2 - (width / 2);

        for (int i = 0; i < size; i++, x += width + 10) {
            createRectangle(x, y, width, height, i);
        }
    }

    private Rectangle createRectangle(double x, double y, double width, double height, int index) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setId("rectangle");

        Label indexLabel = new Label(String.valueOf(index));

        indexLabel.setLayoutX(x + width / 2 - 5);
        indexLabel.setLayoutY(y + width + 10);

        intArray[index] = new SimpleStringProperty("0");

        Label valueLabel = new Label();
        valueLabel.textProperty().bind(intArray[index]);

        valueLabel.setLayoutX(x + width / 2 - 5);
        valueLabel.setLayoutY(y + height / 2 - 7.5);

        containers.getChildren().addAll(rectangle, indexLabel, valueLabel);

        return rectangle;
    }
}
