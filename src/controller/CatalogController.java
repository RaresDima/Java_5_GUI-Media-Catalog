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

    public static void handleExit() {
        primaryStage.close();
    }
    public static void handleLoad() {
        CatalogView.loadTree(CatalogController.loadMediaItems());
        primaryStage.show();
    }
    public static void handlePlay() {
        System.out.println("CatalogController.handlePlay");
        catalog.play(CatalogView.getItemList().getSelectionModel().getSelectedItem().getValue().NAME.get());
    }


    private static TreeItem<MediaFile> loadMediaItems() {
        catalog.load("D:\\Intellij Projects\\Java_5\\src\\model\\catalog\\saveFile.txt");

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