package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.PropertyStatus;
import utilities.PropertyType;

public class Panel_Browse_Filter extends VBox {


    private Label title1 = new Label("Property Type");
    private Label title2 = new Label("Number OF Bedrooms");
    private Label title3 = new Label("Property Status");
    private Label title4 = new Label("Property Suburb");


    private ComboBox<PropertyType> selectType = new ComboBox<>();
    private ComboBox<Integer> selectNumOfRooms = new ComboBox<>();
    private ComboBox<PropertyStatus> selectStatus = new ComboBox<>();
    private TextField inputSuburb = new TextField();

    private Button search = new Button("Search");
    private Button clear = new Button("Clear");

    public Panel_Browse_Filter() {

        int width = 200;

        HBox buttonContainer = new HBox(5);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(search, clear);
        this.getChildren().addAll(title1, selectType, title2, selectNumOfRooms, title3, selectStatus, title4, inputSuburb, buttonContainer);
        clear.setOnAction(event -> {
            selectType.cancelEdit();
            selectNumOfRooms.cancelEdit();
            selectStatus.cancelEdit();
            inputSuburb.clear();
        });
        selectType.getItems().addAll(PropertyType.APARTMENT, PropertyType.PREMIUM_SUITE);
        selectNumOfRooms.getItems().addAll(1, 2, 3);
        selectStatus.getItems().addAll(PropertyStatus.AVAILABLE, PropertyStatus.RENTED, PropertyStatus.UNDER_MAINTENANCE);

        inputSuburb.setEditable(true);

        selectType.setMinWidth(width);
        selectNumOfRooms.setMinWidth(width);
        selectStatus.setMinWidth(width);
        inputSuburb.setMinWidth(width);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.setPadding(new Insets(10, 10, 10, 10));


    }


    public ComboBox<PropertyType> getSelectType() {
        return selectType;
    }

    public void setSelectType(ComboBox<PropertyType> selectType) {
        this.selectType = selectType;
    }

    public ComboBox<Integer> getSelectNumOfRooms() {
        return selectNumOfRooms;
    }

    public void setSelectNumOfRooms(ComboBox<Integer> selectNumOfRooms) {
        this.selectNumOfRooms = selectNumOfRooms;
    }

    public ComboBox<PropertyStatus> getSelectStatus() {
        return selectStatus;
    }

    public void setSelectStatus(ComboBox<PropertyStatus> selectStatus) {
        this.selectStatus = selectStatus;
    }

    public TextField getInputSuburb() {
        return inputSuburb;
    }



    public Button getSearch() {
        return search;
    }

    public void setSearch(Button search) {
        this.search = search;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }
}
