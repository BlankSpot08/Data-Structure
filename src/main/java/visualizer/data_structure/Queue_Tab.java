package visualizer.data_structure;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import visualizer.data_structure.queue.Queue;

public class Queue_Tab {
    Queue<Integer> queue;
    public Queue_Tab(ScrollPane canvas) {
        this.canvas = canvas;

        containers = new Pane();
        queue = new Queue<>(containers, canvas);
    }

    public Pane containers;

    private ScrollPane canvas;
    private GridPane gridPane;
    private HBox offerHBox;
    private VBox mainPane;
    private TextField valueTextField;
    private Button offerButton;
    private Button pollButton;

    private BorderPane createInformationPane(BorderPane informationPane, TextArea informationTextArea) {
        String information = "The Queue interface present in the java.util package and extends the Collection" +
                " interface is used to hold the elements about to be processed in FIFO(First In First Out) " +
                "order. It is an ordered list of objects with its use limited to insert elements at the end " +
                "of the list and deleting elements from the start of the list, (i.e.), it follows the FIFO " +
                "or the First-In-First-Out principle.";

        informationTextArea.setText(information);

        return informationPane;
    }

    private HBox createOffer() {
        offerHBox = new HBox();
        offerHBox.setId("together-nodes");

        valueTextField = new TextField();
        valueTextField.setPromptText("Value");

        offerButton = new Button("Offer");

        offerButton.setOnAction(e -> {
            offer(Integer.parseInt(valueTextField.getText()));
        });

        offerHBox.getChildren().addAll(valueTextField, offerButton);

        return offerHBox;
    }

    private GridPane createGridPane() {
        gridPane = new GridPane();
        gridPane.setId("grid-pane");

        pollButton = new Button("Poll");

        pollButton.setOnAction(e -> {
            poll();
        });

        gridPane.add(createOffer(), 0, 0);
        gridPane.add(pollButton, 1, 0);

        return gridPane;
    }

    public VBox createMainPane(BorderPane informationPane, TextArea informationTextArea) {
        mainPane = new VBox();
        mainPane.setId("main-pane");

        mainPane.getChildren().addAll(createGridPane(), createInformationPane(informationPane, informationTextArea));

        return mainPane;
    }

    private void offer(int value) {
        queue.offer(value);
    }

    private void poll() {
        queue.poll();
    }
}
