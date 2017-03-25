import javafx.application.Application;
import javafx.stage.Stage;
import view.*;


public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        CatalogView.initRootLayout(this.primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
