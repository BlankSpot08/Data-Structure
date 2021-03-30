package visualizer.data_structure;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import visualizer.data_structure.binary_search_tree.BinarySearchTree;

public class BinarySearchTree_Tab {

    private BinarySearchTree<Integer> binarySearchTree;

    private VBox mainPane;
    private GridPane gridPane;
    private ScrollPane canvas;
    private Button newButton;
    private Button addButton;
    private TextField addValueTextField;
    private HBox addHBox;

    public Pane containers;

    public BinarySearchTree_Tab(ScrollPane canvas) {
        this.canvas = canvas;

        containers = new Pane();
        containers.setStyle("background-color: red");
    }

    private BorderPane createInformationPane(BorderPane informationPane, TextArea informationTextArea) {
        return informationPane;
    }

    private HBox createAddHBox() {
        addHBox = new HBox();
        addHBox.setId("together-nodes");

        addValueTextField = new TextField();
        addValueTextField.setPromptText("Value");

        addButton = new Button("Add");

        addButton.setOnAction(e -> {
            add(Integer.parseInt(addValueTextField.getText()));
        });

        addHBox.getChildren().addAll(addValueTextField, addButton);

        return addHBox;
    }

    private GridPane createGridPane() {
        gridPane = new GridPane();
        gridPane.setId("grid-pane");

        newButton = new Button("New");
        newButton.setOnAction(e -> {
            createBinarySearchTree();
        });

        gridPane.add(newButton, 0, 0);
        gridPane.add(createAddHBox(), 1, 0);

        return gridPane;
    }

    public VBox createMainPane(BorderPane informationPane, TextArea informationTextArea) {
        mainPane = new VBox();
        mainPane.setId("main-pane");

        mainPane.getChildren().addAll(createGridPane(), createInformationPane(informationPane, informationTextArea));

        return mainPane;
    }

    private void createBinarySearchTree() {
        binarySearchTree = new BinarySearchTree<>(containers, canvas);
        containers.getChildren().clear();
    }

    private void add(int value) {
        binarySearchTree.add(value);
    }
}
