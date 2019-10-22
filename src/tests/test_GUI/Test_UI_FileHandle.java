package tests.test_GUI;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.FileHandler;
import model.RentalProperty;
import model.RentalRecord;

import java.io.File;
import java.util.ArrayList;

public class Test_UI_FileHandle extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileHandler fileHandler = new FileHandler();

        File file = new FileChooser().showOpenDialog(new Stage());


        ArrayList<RentalProperty> properties = fileHandler.readFromFile(file);

        for (RentalProperty ppty:properties){
            System.out.println(ppty.toString());
            for (RentalRecord rr:ppty.getRentalRecords()){
                System.out.println(rr.toString());
            }
        }

    }
}
