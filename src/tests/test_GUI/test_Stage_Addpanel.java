package tests.test_GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Window_AddProperty1;

public class test_Stage_Addpanel extends Application {
    @Override
    public void start(Stage primaryStage) {
        Window_AddProperty1 addProperty1 = new Window_AddProperty1();
        addProperty1.show();
    }
}
