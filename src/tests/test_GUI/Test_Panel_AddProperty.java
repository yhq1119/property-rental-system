package tests.test_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.PropertyType;
import view.Panel_AddProperty;

public class Test_Panel_AddProperty extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        Stage s1 = new Stage();

        Stage s2 = new Stage();

        Panel_AddProperty a1 =new Panel_AddProperty(PropertyType.APARTMENT);

        Panel_AddProperty a2 = new Panel_AddProperty(PropertyType.PREMIUM_SUITE);
        s1.setScene(new Scene(a1));
        s1.show();

        s2.setScene(new Scene(a2));
        s2.show();
    }
}
