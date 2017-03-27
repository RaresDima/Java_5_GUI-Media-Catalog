import controller.CatalogController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        this.primaryStage = primaryStage;

        CatalogController.initView(this.primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
