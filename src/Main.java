import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Dialog_Confirm;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(new Controller().getMainWindow()));

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            new Dialog_Confirm();
        });
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
