package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@SuppressWarnings("ALL")
public class Panel_MainWindow1 extends VBox {

    private Window_AddProperty1 window_addProperty1 = new Window_AddProperty1();
    private Window_Rent_Return_Property window_rent_return_property = new Window_Rent_Return_Property();
    private Window_Perform_Complete_Maintenance window_perform_complete_maintenance = new Window_Perform_Complete_Maintenance();
    private Window_Database_Operation window_database_operation = new Window_Database_Operation();
    private Panel_Browse_Filter browse_filter = new Panel_Browse_Filter();
    private SplitPane vSplit = new SplitPane();

    private SplitPane hSplit = new SplitPane();
     private MenuBar header = new MenuBar();
    private MenuItem loadFile = new MenuItem("Load File");
    private MenuItem saveFile = new MenuItem("Save File");
//    private MenuItem expotAllData = new MenuItem("Export All Data");

    private MenuItem quit = new MenuItem("Quit System");


    private Button addButton = new Button("Add New \nProperty");
    private Button rent_return = new Button("Rent/Return \nProperty");
    private Button maintenance = new Button("Perform/Complete \nMaintenance");
    private Button database = new Button("Connect\nDatabase");

    private VBox left_bottomPanel = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane displayPanel = new FlowPane();


    public Panel_MainWindow1() {
        setup();
    }

    private void setup() {

        setupBrowsePanel();

        //   addTestitems();
        left_bottomPanel.setMinWidth(220);
        left_bottomPanel.setMaxWidth(220);
        left_bottomPanel.setMinHeight(600);
        left_bottomPanel.setFillWidth(true);

        displayPanel.setPadding(new Insets(5, 5, 5, 5));
        displayPanel.setVgap(7);
        displayPanel.setHgap(5);
        displayPanel.setMaxWidth(920);
        displayPanel.setMinWidth(920);
        scrollPane.setMinWidth(935);
        scrollPane.setMaxWidth(935);


        HBox buttonContainer = new HBox(10);

        int width = 130;
        int height = 60;
        addButton.setPrefSize(width, height);
        rent_return.setPrefSize(width, height);
        maintenance.setPrefSize(width, height);
        database.setPrefSize(width, height);

        buttonContainer.getChildren().addAll(addButton, rent_return, maintenance, database);
        buttonContainer.setMaxHeight(80);
        buttonContainer.setMinHeight(80);
        buttonContainer.setAlignment(Pos.CENTER_LEFT);
        buttonContainer.setPadding(new Insets(10, 10, 10, 10));

        HBox lowerPart = new HBox();
        lowerPart.getChildren().addAll(hSplit);


        scrollPane.setContent(displayPanel);


        hSplit.getItems().addAll(left_bottomPanel, scrollPane);
        vSplit.getItems().addAll(buttonContainer, hSplit);
        hSplit.setOrientation(Orientation.HORIZONTAL);
        vSplit.setOrientation(Orientation.VERTICAL);

        Menu sysMenu = new Menu("System");
        quit.setOnAction(event -> {
            new Dialog_Confirm();
        });
        sysMenu.getItems().addAll(loadFile, saveFile, quit);

        MenuBar header = new MenuBar(sysMenu);

        this.getChildren().addAll(header, vSplit);
        this.setMinHeight(700);
        this.setMaxHeight(700);
        this.setMinWidth(1000);
        this.maxWidth(1000);

        // browseButton.setOnAction(event -> setupBrowsePanel());


        addButton.setOnAction(event -> {
            window_addProperty1.clearAllinput();
            window_addProperty1.show();
        });
        rent_return.setOnAction(event -> {
            window_rent_return_property.Clear();
            window_rent_return_property.show();
        });
        database.setOnAction(event -> window_database_operation.show());


    }


    private void setupBrowsePanel() {
        left_bottomPanel.getChildren().clear();
        left_bottomPanel.getChildren().add(browse_filter);

    }

    public Window_AddProperty1 getWindow_addProperty1() {
        return window_addProperty1;
    }

    public Window_Rent_Return_Property getWindow_rent_return_property() {
        return window_rent_return_property;
    }

    public Window_Perform_Complete_Maintenance getWindow_perform_complete_maintenance() {
        return window_perform_complete_maintenance;
    }

    public Panel_Browse_Filter getBrowse_filter() {
        return browse_filter;
    }

    public MenuItem getLoadFile() {
        return loadFile;
    }

    public MenuItem getSaveFile() { return saveFile;
    }

    public MenuItem getQuit() {   return quit;
    }

    public MenuBar getHeader() {
        return header;
    }



    public Button getAddButton() {
        return addButton;
    }

    public Button getRent_return() {
        return rent_return;
    }

    public Button getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Button maintenance) {
        this.maintenance = maintenance;
    }

    public FlowPane getDisplayPanel() {
        return displayPanel;
    }

    public Window_Database_Operation getWindow_database_operation() {
        return window_database_operation;
    }
}
