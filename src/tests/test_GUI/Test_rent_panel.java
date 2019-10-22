package tests.test_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

public class Test_rent_panel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new Panel_RentProperty()));
        primaryStage.show();

        Stage s2 = new Stage();
        s2.setScene(new Scene(new Panel_Return_Property()));
        s2.show();

        Stage s3 = new Stage();
        s3.setScene(new Scene(new Panel_Perform_Maintenance()));
        s3.show();


        Stage s4 = new Stage();
        s4.setScene(new Scene(new Panel_Complete_Maintenance()));
        s4.show();

        Window_Property_Details window_property_details = new Window_Property_Details();
        window_property_details.show();
    }
}
