package visualizer.data_structure;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import visualizer.data_structure.stack.Stack;

public class Stack_Tab {
    private Stack<Integer> stack;

    private GridPane gridPane;
    public Pane containers;
    private ScrollPane canvas;
    private HBox pushHBox;
    private HBox searchHBox;
    private VBox mainPane;
    private TextField valueTextField;
    private Button pushButton;
    private Button popButton;
    private Button indexOfButton;
    private TextField searchValueTextField;
    private TextField searchTextField;

    public Stack_Tab(ScrollPane canvas) {
        this.canvas = canvas;

        containers = new Pane();

        stack = new Stack<>(containers, this.canvas);
    }

    private BorderPane createInformationPane(BorderPane informationPane, TextArea informationTextArea) {
        String information = "In stack, elements are stored and accessed in Last In First Out manner. " +
                "That is, elements are added to the top of the stack and removed from the top of the stack.";

        informationTextArea.setText(information);

        return informationPane;
    }

    private HBox createIndexOfHBox() {
        searchHBox = new HBox();
        searchHBox.setId("stack-search");

        searchTextField = new TextField();
        searchTextField.setEditable(false);

        searchValueTextField = new TextField();
        searchValueTextField.setPromptText("Value");

        indexOfButton = new Button("Search");

        indexOfButton.setOnAction(e -> {
            search(Integer.parseInt(searchValueTextField.getText()));
        });

        searchHBox.getChildren().addAll(searchTextField, searchValueTextField, indexOfButton);

        return searchHBox;
    }

    private HBox createPushHBox() {
        pushHBox = new HBox();
        pushHBox.setId("together-nodes");

        valueTextField = new TextField();
        valueTextField.setPromptText("Value");

        pushButton = new Button("Push");

        pushButton.setOnAction(e -> {
            push(Integer.parseInt(valueTextField.getText()));
        });

        pushHBox.getChildren().addAll(valueTextField, pushButton);

        return pushHBox;
    }

    private GridPane createGridPane() {
        gridPane = new GridPane();
        gridPane.setId("grid-pane");

        popButton = new Button("Pop");

        popButton.setOnAction(e -> {
            pop();
        });

        gridPane.add(createPushHBox(), 0, 0);
        gridPane.add(popButton, 1, 0);
        gridPane.add(createIndexOfHBox(), 2, 0);

        return gridPane;
    }

    public VBox createMainPane(BorderPane informationPane, TextArea informationTextArea) {
        mainPane = new VBox();
        mainPane.setId("main-pane");

        mainPane.getChildren().addAll(createGridPane(), createInformationPane(informationPane, informationTextArea));

        return mainPane;
    }

    public void search(int value) {
        searchTextField.setText(String.valueOf(stack.search(value)));
    }

    public void push(int value) {
        stack.push(value);
    }

    public void pop() {
        stack.pop();
    }
}
