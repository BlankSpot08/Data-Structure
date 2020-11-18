package visualizer.data_structure;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;
import javafx.beans.value.ObservableSetValue;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import visualizer.data_structure.linked_list.LinkedList;

import java.util.Arrays;

public class Linked_List_Tab {
    public Linked_List_Tab(ScrollPane canvas) {
        this.canvas = canvas;

        this.canvas.setPrefHeight(250);

        containers = new Pane();
    }

    private final ScrollPane canvas;
    private GridPane gridPane;
    private TextField addTextField;
    private TextField addFirstLastTextField;
    private TextField addIndexTextField;
    private TextField removeIndexTextField;
    private TextField setValueTextField;
    private TextField setIndexTextField;
    private TextField searchValueTextField;
    private TextField searchTextField;
    private Button addFirstButton;
    private Button addLastButton;
    private Button addButton;
    private Button newButton;
    private Button removeAtButton;
    private Button removeFirstButton;
    private Button removeLastButton;
    private Button setButton;
    private Button searchButton;
    private HBox searchHBox;
    private HBox addHBox;
    private HBox addFirstLastHBox;
    private HBox newHBox;
    private HBox removeAtHBox;
    private HBox setHBox;
    private VBox mainPane;

    private HBox removeFirstLastHBox;
    public final Pane containers;

    private LinkedList<Integer> linkedList;

    private BorderPane createInformationPane(BorderPane informationPane, TextArea informationTextArea) {
        String information = "Linked List is a part of the Collection framework present in java.util " +
                "package. This class is an implementation of the LinkedList data structure which is a " +
                "linear data structure where the elements are not stored in contiguous locations and " +
                "every element is a separate object with a data part and address part. The elements are " +
                "linked using pointers and addresses. Each element is known as a node. Due to the " +
                "dynamicity and ease of insertions and deletions, they are preferred over the arrays. " +
                "It also has few disadvantages like the nodes cannot be accessed directly instead we need " +
                "to start from the head and follow through the link to reach to a node we wish to access.";

        informationTextArea.setText(information);

        return informationPane;
    }

    private HBox createRemoveFirstLastHBox() {
        removeFirstLastHBox = new HBox();
        removeFirstLastHBox.setId("together-nodes");

        removeFirstButton = new Button("Remove First");
        removeFirstButton.setOnAction(e -> {
            removeFirst();
        });

        removeLastButton = new Button("Remove Last");
        removeLastButton.setOnAction(e -> {
            removeLast();
        });

        removeFirstLastHBox.getChildren().addAll(removeFirstButton, removeLastButton);

        return removeFirstLastHBox;
    }

    private HBox createRemoveAtHBox() {
        removeAtHBox = new HBox();
        removeAtHBox.setId("together-nodes");

        removeIndexTextField = new TextField();
        removeIndexTextField.setPromptText("Index");

        removeAtButton = new Button("Remove");

        removeAtButton.setOnAction(e -> {
            removeAt(Integer.parseInt(removeIndexTextField.getText()));
        });

        removeAtHBox.getChildren().addAll(removeIndexTextField, removeAtButton);

        return removeAtHBox;
    }

    private HBox createSearchHBox() {
        searchHBox = new HBox();
        searchHBox.setId("together-nodes");

        searchTextField = new TextField();
        searchTextField.setEditable(false);

        searchValueTextField = new TextField();
        searchValueTextField.setPromptText("Value");

        searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            search(Integer.parseInt(searchValueTextField.getText()));
        });

        searchHBox.getChildren().addAll(searchTextField, searchValueTextField, searchButton);

        return searchHBox;
    }

    private HBox createSetHBox() {
        setHBox = new HBox();
        setHBox.setId("together-nodes");

        setValueTextField = new TextField();
        setValueTextField.setPromptText("Value");

        setIndexTextField = new TextField();
        setIndexTextField.setPromptText("Index");

        setButton = new Button("Set");
        setButton.setOnAction(e -> {
            set(Integer.parseInt(setValueTextField.getText()),
                    Integer.parseInt(setIndexTextField.getText()));
        });

        setHBox.getChildren().addAll(setValueTextField, setIndexTextField, setButton);

        return setHBox;
    }

    private HBox createAddFirstLastHBox() {
        addFirstLastHBox = new HBox();
        addFirstLastHBox.setId("together-nodes");

        addFirstLastTextField = new TextField();
        addFirstLastTextField.setPromptText("Value");

        addFirstButton = new Button("Add First");

        addFirstButton.setOnAction(e -> {
            addFirst(Integer.parseInt(addFirstLastTextField.getText()));
        });

        addLastButton = new Button("Add Last");

        addLastButton.setOnAction(e -> {
            addLast(Integer.parseInt(addFirstLastTextField.getText()));
        });

        addFirstLastHBox.getChildren().addAll(addFirstLastTextField, addFirstButton, addLastButton);

        return addFirstLastHBox;
    }

    private HBox createAddHBox() {
        addHBox = new HBox();
        addHBox.setId("together-nodes");

        addButton = new Button("Add");

        addButton.setOnAction(e -> {
            addAt(Integer.parseInt(addIndexTextField.getText()),
                    Integer.parseInt(addTextField.getText()));
        });

        addTextField = new TextField();
        addTextField.setPromptText("Value");

        addIndexTextField = new TextField();
        addIndexTextField.setPromptText("Index");

        addHBox.getChildren().addAll(addTextField, addIndexTextField, addButton);

        return addHBox;
    }

    private HBox createNewHBox() {
        newHBox = new HBox();
        newHBox.setId("together-nodes");

        newButton = new Button("New");

        newButton.setOnAction(e -> {
            createLinkedList();
        });

        newHBox.getChildren().addAll(newButton);

        return newHBox;
    }

    private GridPane createGridPane() {
        gridPane = new GridPane();
        gridPane.setId("grid-pane");

        gridPane.add(createNewHBox(), 0, 0);
        gridPane.add(createAddHBox(), 1, 0);
        gridPane.add(createAddFirstLastHBox(), 2, 0);
        gridPane.add(createSetHBox(), 1, 1);
        gridPane.add(createSearchHBox(), 2, 1);
        gridPane.add(createRemoveAtHBox(), 1,  2);
        gridPane.add(createRemoveFirstLastHBox(), 2, 2);

        return gridPane;
    }

    public VBox createMainPane(BorderPane informationPane, TextArea informationTextArea) {
        mainPane = new VBox();
        mainPane.setId("main-pane");

        mainPane.getChildren().addAll(createGridPane(), createInformationPane(informationPane, informationTextArea));

        return mainPane;
    }

    private void createLinkedList() {
        containers.getChildren().clear();
        linkedList = new LinkedList<>(containers, canvas);
    }

    private void addAt(int index, int value) {
        linkedList.add(index, value);

        System.out.println(Arrays.toString(linkedList.toArray()));;
    }

    private void addLast(int value) {
        linkedList.addLast(value);

        System.out.println(Arrays.toString(linkedList.toArray()));;
    }

    private void search(int value) {
        searchTextField.setText(String.valueOf(linkedList.indexOf(value)));
    }

    private void set(int value, int index) {
        linkedList.set(value, index);
    }

    private void addFirst(int value) {
        linkedList.addFirst(value);
    }

    private void removeAt(int index) {
        linkedList.remove(index);
    }

    private void removeLast() {
        linkedList.removeLast();
    }

    private void removeFirst() {
        linkedList.removeFirst();
    }
}
