package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.entries.general.MediaFile;

public class CatalogViewService {

    private static Font        primaryFont     = new Font("Century Gothic", 14);
    private static ToggleGroup typeToggleGroup = new ToggleGroup();
    private static Stage primaryStageRef;



    private static void initPrimaryStage(Stage primaryStage) {
        primaryStageRef = primaryStage;
        primaryStage.setTitle("Catalog");
        primaryStage.setHeight(420.0);
        primaryStage.setWidth(615.0);
    }

    private static TreeView<MediaFile> initItemList() {
        TreeView<MediaFile> itemList = new TreeView<>();

        itemList.setPrefSize(200,350);

        itemList.setLayoutX(15); itemList.setLayoutY(15);

        return itemList;
    }

    private static Label initTypeLabel () {
        Label typeLabel = new Label("Type");
        typeLabel.setFont(primaryFont);
        typeLabel.setPrefSize(50,25);
        typeLabel.setLayoutX(230); typeLabel.setLayoutY(15);
        return typeLabel;
    }
    private static Label initNameLabel () {
        Label nameLabel = new Label("Name");
        nameLabel.setFont(primaryFont);
        nameLabel.setPrefSize(50,25);
        nameLabel.setLayoutX(230); nameLabel.setLayoutY(95);
        return  nameLabel;

    }
    private static Label initPathLabel () {
        Label pathLabel = new Label("Path");
        pathLabel.setFont(primaryFont);
        pathLabel.setPrefSize(50,25);
        pathLabel.setLayoutX(230); pathLabel.setLayoutY(135);
        return pathLabel;
    }

    private static RadioButton initTrackButton() {
        RadioButton trackButton = new RadioButton();
        trackButton.setFont(primaryFont);
        trackButton.setText("Track");
        trackButton.setPrefSize(150,25);
        trackButton.setLayoutX(295); trackButton.setLayoutY(15);
        trackButton.setToggleGroup(typeToggleGroup);
        return trackButton;
    }
    private static RadioButton initMovieButton() {
        RadioButton movieButton = new RadioButton();
        movieButton.setFont(primaryFont);
        movieButton.setText("Movie");
        movieButton.setPrefSize(150,25);
        movieButton.setLayoutX(295); movieButton.setLayoutY(50);
        movieButton.setToggleGroup(typeToggleGroup);
        return movieButton;
    }

    private static TextField initNameField() {
        TextField nameField = new TextField();
        nameField.setPrefSize(150,25);
        nameField.setLayoutX(295); nameField.setLayoutY(95);
        nameField.setPromptText("Ex: Inception");
        return nameField;
    }
    private static TextField initPathField() {
        TextField pathField = new TextField();
        pathField.setPrefSize(290,25);
        pathField.setLayoutX(295); pathField.setLayoutY(135);
        pathField.setPromptText("Ex: d:/movies/favourites/Inception.mp4");
        return pathField;
    }

    private static Button initAddButton()  {
        Button addButton  = new Button("Add");
        addButton .setFont(primaryFont);
        addButton .setPrefSize(50,25);
        addButton .setLayoutX(295); addButton .setLayoutY(175);
        return addButton;
    }
    private static Button initSaveButton() {
        Button saveButton = new Button("Save");
        saveButton.setFont(primaryFont);
        saveButton.setPrefSize(60,25);
        saveButton.setLayoutX(230); saveButton.setLayoutY(340);
        return saveButton;
    }
    private static Button initLoadButton() {
        Button loadButton = new Button("Load");
        loadButton.setFont(primaryFont);
        loadButton.setPrefSize(60,25);
        loadButton.setLayoutX(305); loadButton.setLayoutY(340);
        return loadButton;

    }
    private static Button initPlayButton() {
        Button playButton = new Button("Play");
        playButton.setFont(primaryFont);
        playButton.setPrefSize(50,25);
        playButton.setLayoutX(230); playButton.setLayoutY(300);
        return playButton;
    }
    private static Button initExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setFont(primaryFont);
        exitButton.setPrefSize(50,25);
        exitButton.setLayoutX(535); exitButton.setLayoutY(340);
        exitButton.setOnMouseClicked(event -> primaryStageRef.close());
        return exitButton;
    }
    
    public static void initRootLayout(Stage primaryStage) {
        // Setting initial window dimensions and title
        initPrimaryStage(primaryStage);
        // Creating the group that will store all the other elements.
        Group primaryGroup = new Group();

        // Adding the tree view for the media items
        primaryGroup.getChildren().addAll(initItemList());

        // Adding labels for type, name and path
        primaryGroup.getChildren().addAll(initTypeLabel(), initNameLabel(), initPathLabel());

        // Adding the radio buttons for media type selection and setting toggle group
        primaryGroup.getChildren().addAll(initTrackButton(), initMovieButton());

        // Adding the text boxes for data input necessary for "add" function
        primaryGroup.getChildren().addAll(initNameField(), initPathField());

        // Adding the load, save, add, play and exit buttons
        primaryGroup.getChildren().addAll(initAddButton(), initSaveButton(), initLoadButton(), initPlayButton(), initExitButton());

        // Putting the finalized group into a scene and passing it to the primary stage
        Scene primaryScene = new Scene(primaryGroup);
        primaryStage.setScene(primaryScene);

    }
}
