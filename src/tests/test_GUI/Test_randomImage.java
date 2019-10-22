package tests.test_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.RandomPropertyGenerator;
import view.Panel_SingleProperty_Item1;

public class Test_randomImage extends Application {

    private RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Panel_SingleProperty_Item1 p1 = new Panel_SingleProperty_Item1();
        p1.setPic(randomPropertyGenerator.randomImageView());

        primaryStage.setScene(new Scene(p1));
        primaryStage.show();

    }
}
