package visualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import visualizer.data_structure.*;

public class Visualizer extends Application {
    public static void main(String... args) {
        launch(args);
    }

    private Scene mainScene;
    private VBox mainVBox;
    private TextArea informationTextArea;
    private BorderPane centerBorderPane;
    private BorderPane bottomBorderPane;
    private BorderPane mainBorderPane;
    private BorderPane informationPane;
    private ScrollPane bottomScrollPane;
    private HBox topHBox;
    private Label title;
    private TabPane topTabPane;

    @Override
    public void start(Stage window) {
        window.setScene(createScene(window));
        window.setTitle("Data Structure Visualizer");

        window.show();
    }

    public Scene createScene(Stage scene) {
        mainVBox = new VBox();
        mainVBox.setId("main");

        bottomScrollPane = new ScrollPane();
//        bottomScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        bottomScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        mainScene = new Scene(mainVBox, 1000, 800);

        mainVBox.getChildren().addAll(
                createTopHBox(),
                createCenterBorderPane(),
                createBottomScrollPane(mainScene));

        mainScene.getStylesheets().add("C:\\Users\\arvin\\IdeaProjects\\DataStructure\\src\\main\\java\\visualizer\\css\\Design.css");

        return mainScene;
    }

    private ScrollPane createBottomScrollPane(Scene scene) {
        bottomScrollPane.setId("canvas");
        bottomScrollPane.setPrefHeight(scene.getHeight());
        bottomScrollPane.setPrefWidth(scene.getWidth());

        return bottomScrollPane;
    }

    private BorderPane createInformationPane() {
        informationPane = new BorderPane();
        informationPane.setId("information");

        informationTextArea = new TextArea();
        informationTextArea.setEditable(false);

        informationPane.setCenter(informationTextArea);

        return informationPane;
    }

    public TabPane createTopTabPane() {
        topTabPane = new TabPane();

        Array_Tab array = new Array_Tab(bottomScrollPane);

        Tab arrayTab = new Tab("Array", array.createMainPane(createInformationPane(),
                informationTextArea));
        arrayTab.setClosable(false);

        Stack_Tab stack = new Stack_Tab(bottomScrollPane);

        Tab stackTab = new Tab("Stack", stack.createMainPane(createInformationPane(),
                informationTextArea));
        stackTab.setClosable(false);

        Queue_Tab queue = new Queue_Tab(bottomScrollPane);

        Tab queueTab = new Tab("Queue", queue.createMainPane(createInformationPane(),
                informationTextArea));
        queueTab.setClosable(false);

        Linked_List_Tab linkedList = new Linked_List_Tab(bottomScrollPane);

        Tab linkedListTab = new Tab("Linked List", linkedList.createMainPane(createInformationPane(),
                informationTextArea));
        linkedListTab.setClosable(false);

        BinarySearchTree_Tab binarySearchTree = new BinarySearchTree_Tab(bottomScrollPane);

        Tab binarySearchTreeTab = new Tab("Binary Search Tree",
                binarySearchTree.createMainPane(createInformationPane(),
                        informationTextArea));

        topTabPane.getTabs().addAll(arrayTab, stackTab, queueTab, linkedListTab, binarySearchTreeTab);

        bottomScrollPane.setContent(array.containers);

        topTabPane.getSelectionModel().selectedItemProperty().addListener(e -> {
            bottomScrollPane.setContent(changingTabs(array, stack, queue, linkedList, binarySearchTree));
        });

        return topTabPane;
    }

    private BorderPane createCenterBorderPane() {
        centerBorderPane = new BorderPane();
        centerBorderPane.setId("tab-panes");

        centerBorderPane.setTop(createTopTabPane());

        return centerBorderPane;
    }

    private Pane changingTabs(Array_Tab array,
                              Stack_Tab stack,
                              Queue_Tab queue,
                              Linked_List_Tab linkedList,
                              BinarySearchTree_Tab binarySearchTree) {
        int index = topTabPane.getSelectionModel().getSelectedIndex();

        switch (index) {
            case 0:
                return array.containers;
            case 1:
                return stack.containers;
            case 2:
                return queue.containers;
            case 3:
                return linkedList.containers;
            case 4:
                return binarySearchTree.containers;
            default:
                return new Pane();
        }
    }

    private HBox createTopHBox() {
        topHBox = new HBox();
        topHBox.setId("title");

        title = new Label("Data Structure");

        topHBox.getChildren().add(title);

        return topHBox;
    }
}