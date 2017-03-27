package view;

import controller.CatalogController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.entries.general.MediaFile;

public class CatalogView {

    private static Stage primaryStage;

    private static Group primaryGroup;

    private static Font primaryFont;

    private static TreeView<MediaFile> itemList;

    private static Label typeLabel;
    private static Label nameLabel;
    private static Label pathLabel;
    private static Label yearLabel;

    private static RadioButton trackButton;
    private static RadioButton movieButton;
    private static ToggleGroup typeToggleGroup;

    private static TextField nameField;
    private static TextField pathField;
    private static TextField yearField;

    private static Button addButton;
    private static Button saveButton;
    private static Button loadButton;
    private static Button playButton;
    private static Button exitButton;



    private static void initPrimaryStage(Stage primaryStage) {
        CatalogView.primaryStage = primaryStage;
        CatalogView.primaryStage.setTitle("Catalog");
        CatalogView.primaryStage.setHeight(420.0);
        CatalogView.primaryStage.setWidth(615.0);
    }

    private static void initPrimaryFont() {
        primaryFont = new Font("Century Gothic", 14);
    }

    private static void initItemList() {
        itemList = new TreeView<>();
        itemList.setPrefSize(200,350);
        itemList.setLayoutX(15); itemList.setLayoutY(15);
    }
    private static void initItemList(TreeItem<MediaFile> root) {
        itemList = new TreeView<>(root);
        itemList.setPrefSize(200,350);
        itemList.setLayoutX(15); itemList.setLayoutY(15);
    }

    private static void initTypeLabel() {
        typeLabel = new Label("Type");
        typeLabel.setFont(primaryFont);
        typeLabel.setPrefSize(50,25);
        typeLabel.setLayoutX(230); typeLabel.setLayoutY(15);
    }
    private static void initNameLabel() {
        nameLabel = new Label("Name");
        nameLabel.setFont(primaryFont);
        nameLabel.setPrefSize(50,25);
        nameLabel.setLayoutX(230); nameLabel.setLayoutY(95);
    }
    private static void initPathLabel() {
        pathLabel = new Label("Path");
        pathLabel.setFont(primaryFont);
        pathLabel.setPrefSize(50,25);
        pathLabel.setLayoutX(230); pathLabel.setLayoutY(135);
    }
    private static void initYearLabel() {
        yearLabel = new Label("Year");
        yearLabel.setFont(primaryFont);
        yearLabel.setPrefSize(50,25);
        yearLabel.setLayoutX(230); yearLabel.setLayoutY(175);
    }

    private static void initTypeToggleGroup() {
        typeToggleGroup = new ToggleGroup();
    }

    private static void initTrackButton() {
        trackButton = new RadioButton();
        trackButton.setFont(primaryFont);
        trackButton.setText("Track");
        trackButton.setPrefSize(150,25);
        trackButton.setLayoutX(295); trackButton.setLayoutY(15);
        trackButton.setToggleGroup(typeToggleGroup);
    }
    private static void initMovieButton() {
        movieButton = new RadioButton();
        movieButton.setFont(primaryFont);
        movieButton.setText("Movie");
        movieButton.setPrefSize(150,25);
        movieButton.setLayoutX(295); movieButton.setLayoutY(50);
        movieButton.setToggleGroup(typeToggleGroup);
    }

    private static void initNameField() {
        nameField = new TextField();
        nameField.setPrefSize(150,25);
        nameField.setLayoutX(295); nameField.setLayoutY(95);
        nameField.setPromptText("Ex: Inception");
    }
    private static void initPathField() {
        pathField = new TextField();
        pathField.setPrefSize(290,25);
        pathField.setLayoutX(295); pathField.setLayoutY(135);
        pathField.setPromptText("Ex: d:/movies/favourites/Inception.mp4");
    }
    private static void initYearField() {
        yearField = new TextField();
        yearField.setPrefSize(60,25);
        yearField.setLayoutX(295); yearField.setLayoutY(175);
        yearField.setPromptText("Ex: 1978");
    }

    private static void initAddButton()  {
        addButton = new Button("Add");
        addButton.setFont(primaryFont);
        addButton.setPrefSize(50,25);
        addButton.setLayoutX(295); addButton .setLayoutY(215);
        addButton.setOnMouseClicked(event -> CatalogController.handleAdd());
    }
    private static void initSaveButton() {
        saveButton = new Button("Save");
        saveButton.setFont(primaryFont);
        saveButton.setPrefSize(60,25);
        saveButton.setLayoutX(230); saveButton.setLayoutY(340);
        saveButton.setOnMouseClicked(event -> CatalogController.handleSave());
    }
    private static void initLoadButton() {
        loadButton = new Button("Load");
        loadButton.setFont(primaryFont);
        loadButton.setPrefSize(60,25);
        loadButton.setLayoutX(305); loadButton.setLayoutY(340);
        loadButton.setOnMouseClicked(event -> CatalogController.handleLoad());
    }
    private static void initPlayButton() {
        playButton = new Button("Play");
        playButton.setFont(primaryFont);
        playButton.setPrefSize(50,25);
        playButton.setLayoutX(230); playButton.setLayoutY(300);
        playButton.setOnMouseClicked(event -> CatalogController.handlePlay());
        System.out.println("CatalogView.initPlayButton");
    }
    private static void initExitButton() {
        exitButton = new Button("Exit");
        exitButton.setFont(primaryFont);
        exitButton.setPrefSize(50,25);
        exitButton.setLayoutX(535); exitButton.setLayoutY(340);
        exitButton.setOnMouseClicked(event -> CatalogController.handleExit());
    }

    private static void initPrimaryGroup() {
        primaryGroup = new Group();
        primaryGroup.getChildren().addAll(itemList);
        primaryGroup.getChildren().addAll(typeLabel, nameLabel, pathLabel, yearLabel);
        primaryGroup.getChildren().addAll(trackButton, movieButton);
        primaryGroup.getChildren().addAll(nameField, pathField, yearField);
        primaryGroup.getChildren().addAll(addButton, saveButton, loadButton, playButton, exitButton);
    }

    private static void initSceneElements(Stage primaryStage) {
        initPrimaryStage(primaryStage);

        initPrimaryFont();

        initItemList();

        initTypeLabel();
        initNameLabel();
        initPathLabel();
        initYearLabel();

        initTypeToggleGroup();
        initTrackButton();
        initMovieButton();

        initNameField();
        initPathField();
        initYearField();

        initAddButton();
        initSaveButton();
        initLoadButton();
        initPlayButton();
        initExitButton();

        initPrimaryGroup();
    }

    public static void setPrimaryScene(Stage primaryStage) {
        initSceneElements(primaryStage);
        Scene primaryScene = new Scene(primaryGroup);
        primaryStage.setScene(primaryScene);
    }
    public static void loadTree(TreeItem<MediaFile> root) {
        for (Node n : primaryGroup.getChildren())
            if (n.equals(itemList)) {
                primaryGroup.getChildren().remove(n);
                break;
            }
        initItemList(root);
        primaryGroup.getChildren().add(itemList);
        primaryGroup = new Group(primaryGroup);
        Scene newPrimaryScene = new Scene(primaryGroup);
        primaryStage.setScene(newPrimaryScene);
    }



    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static TreeView<MediaFile> getItemList() {
        return itemList;
    }

    public static Label getTypeLabel() {
        return typeLabel;
    }
    public static Label getNameLabel() {
        return nameLabel;
    }
    public static Label getPathLabel() {
        return pathLabel;
    }

    public static RadioButton getTrackButton() {
        return trackButton;
    }
    public static RadioButton getMovieButton() {
        return movieButton;
    }
    public static ToggleGroup getTypeToggleGroup() {
        return typeToggleGroup;
    }

    public static TextField getNameField() {
        return nameField;
    }
    public static TextField getPathField() {
        return pathField;
    }

    public static Button getAddButton() {
        return addButton;
    }
    public static Button getSaveButton() {
        return saveButton;
    }
    public static Button getLoadButton() {
        return loadButton;
    }
    public static Button getPlayButton() {
        return playButton;
    }
    public static Button getExitButton() {
        return exitButton;
    }
}















