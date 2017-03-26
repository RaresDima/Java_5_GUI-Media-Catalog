import controller.CatalogController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.CatalogView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        this.primaryStage = primaryStage;

        CatalogController.initView(this.primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
