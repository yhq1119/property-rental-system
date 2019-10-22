package tests.test_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Panel_MainWindow1;
import view.Window_Perform_Complete_Maintenance;
import view.Window_Property_Details;
import view.Window_Rent_Return_Property;

public class Test_mainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new Panel_MainWindow1()));
        primaryStage.show();


        Window_Rent_Return_Property window_rent_return_property = new Window_Rent_Return_Property();
        window_rent_return_property.show();

        Window_Perform_Complete_Maintenance window_perform_complete_maintenance = new Window_Perform_Complete_Maintenance();
        window_perform_complete_maintenance.show();

        Window_Property_Details window_property_details = new Window_Property_Details();
        window_property_details.show();
    }
}
