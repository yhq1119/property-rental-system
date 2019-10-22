package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("CanBeFinal")
public class Window_Database_Operation extends Stage {


    private Button deleteAlltableButton = new Button("Delete All Tables In DB");
    private Button createTablesButton = new Button("Create Property Table & Record Table");
    private Button writeToDatebase = new Button("Write Current Data Into DB");
    private Button loadFromDatabase = new Button("Load Data From DB(Overwrite Current Data)");


    public Window_Database_Operation() {


        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setAlignment(Pos.CENTER);
        int pWidth = 150;
        int pHeight = 80;
        deleteAlltableButton.setPrefSize(pWidth, pHeight);
        deleteAlltableButton.setWrapText(true);
        createTablesButton.setPrefSize(pWidth, pHeight);
        createTablesButton.setWrapText(true);
        writeToDatebase.setPrefSize(pWidth, pHeight);
        writeToDatebase.setWrapText(true);
        loadFromDatabase.setPrefSize(pWidth, pHeight);
        loadFromDatabase.setWrapText(true);
        root.getChildren().addAll(deleteAlltableButton, createTablesButton, writeToDatebase, loadFromDatabase);
        this.setMinWidth(600);

        this.setMinWidth(500);
        this.setScene(new Scene(root));

        this.setResizable(false);

    }

    public Button getDeleteAlltableButton() {
        return deleteAlltableButton;
    }

    public Button getCreateTablesButton() {
        return createTablesButton;
    }

    public Button getWriteToDatebase() {
        return writeToDatebase;
    }

    public Button getLoadFromDatabase() {
        return loadFromDatabase;
    }
}
