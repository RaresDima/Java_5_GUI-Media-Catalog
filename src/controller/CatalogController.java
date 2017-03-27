package controller;

import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import model.catalog.Catalog;
import model.entries.concrete.Movie;
import model.entries.concrete.Track;
import model.entries.general.MediaFile;
import view.CatalogView;

public class CatalogController {

    private static Stage primaryStage;
    private static Catalog catalog = new Catalog();

    public static void initView(Stage primaryStage) {
        CatalogController.primaryStage = primaryStage;
        CatalogView.setPrimaryScene(CatalogController.primaryStage);
    }

    public static void handleAdd () {
        if (CatalogView.getTypeToggleGroup().getSelectedToggle() == CatalogView.getTrackButton()) {
            catalog.add(new Track(CatalogView.getNameField().getText(), CatalogView.getPathField().getText(), 2000));
            CatalogView.getItemList().getRoot().getChildren().get(0).getChildren().add(new TreeItem<>(catalog.getContents().get(catalog.getContents().size() - 1)));
        }
        if (CatalogView.getTypeToggleGroup().getSelectedToggle() == CatalogView.getMovieButton()) {
            catalog.add(new Movie(CatalogView.getNameField().getText(), CatalogView.getPathField().getText(), 2000));
            CatalogView.getItemList().getRoot().getChildren().get(1).getChildren().add(new TreeItem<>(catalog.getContents().get(catalog.getContents().size() - 1)));
        }
        CatalogView.loadTree(CatalogView.getItemList().getRoot());
        primaryStage.show();
    }
    public static void handleLoad() {
        CatalogView.loadTree(CatalogController.loadMediaItems());
        primaryStage.show();
    }
    public static void handleSave() {
        catalog.save(catalog.getSavePath());
    }
    public static void handlePlay() {
        System.out.println("CatalogController.handlePlay");
        catalog.play(CatalogView.getItemList().getSelectionModel().getSelectedItem().getValue().NAME.get());
    }
    public static void handleExit() {
        primaryStage.close();
    }




    private static TreeItem<MediaFile> loadMediaItems() {
        catalog.load(catalog.getSavePath());

        TreeItem<MediaFile> root = new TreeItem<>(new Movie("Items", "0", 0));
        TreeItem<MediaFile> tracks = new TreeItem<>(new Track("Tracks", "0", 0));
        TreeItem<MediaFile> movies = new TreeItem<>(new Movie("Movies", "0", 0));

        root.getChildren().add(tracks);
        root.getChildren().add(movies);

        for (MediaFile m : catalog.getContents()) {
            TreeItem<MediaFile> item = new TreeItem<>(m);
            if (m.TYPE.get().startsWith("Track"))
                tracks.getChildren().add(item);
            if (m.TYPE.get().startsWith("Movie"))
                movies.getChildren().add(item);
        }
        return root;
    }


}