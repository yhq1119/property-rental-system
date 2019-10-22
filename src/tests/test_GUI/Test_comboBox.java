package tests.test_GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.RentalProperty;
import utilities.RandomPropertyGenerator;
import view.Alert_Information;

import java.util.ArrayList;

public class Test_comboBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();
        ArrayList<RentalProperty> rentalProperties = randomPropertyGenerator.generatePropertiesList();

        ComboBox<RentalProperty> comboBox = new ComboBox<>();
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setEditable(true);

        for (RentalProperty PPP : rentalProperties) {
            comboBox.getItems().add(PPP);
            comboBox1.getItems().add(PPP.getPropertyId());
        }

        HBox root = new HBox();
        root.getChildren().addAll(comboBox, comboBox1);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        new Alert_Information("Error","You are logged out");


    }
}
