package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utilities.NumField;

public class Panel_RentProperty extends VBox {


    private ComboBox<String> inputPropertyId = new ComboBox<>();

    private TextField inputCustomerId = new TextField();

    private NumField inputRentDays = new NumField();

    private DatePicker inputRentdate = new DatePicker();

    private Button rentButton = new Button("Rent");

    private Button clearButton = new Button("Clear");


    public Panel_RentProperty() {
        setup();
    }

    private void setup() {


        Label title1 = new Label("Input Available Property ID");

        Label title2 = new Label("Input Customer ID");

        Label title3 = new Label("Choose Rent Date");

        Label title4 = new Label("Input Rent Days");
        inputPropertyId.setEditable(true);
        this.getChildren().addAll(title1, inputPropertyId, title2, inputCustomerId, title3, inputRentdate, title4, inputRentDays, rentButton, clearButton);

        int width = 260;

        inputPropertyId.setMinWidth(width);
        inputCustomerId.setMinWidth(width);
        inputRentdate.setMinWidth(width);
        inputRentDays.setMinWidth(width);
        rentButton.setMinWidth(width);
        clearButton.setMinWidth(width);
        clearButton.setOnAction(event -> clearInput());
        this.setMinWidth(300);
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setMinHeight(500);


    }

    public void clearInput() {

        inputPropertyId.getEditor().clear();
        inputRentDays.clear();
        inputRentdate.getEditor().clear();
        inputCustomerId.clear();

    }

    public void addItemsToInputPropertyId(String s) {

        inputPropertyId.getItems().add(s);
    }

    public ComboBox<String> getInputPropertyId() {
        return inputPropertyId;
    }

    public void setInputPropertyIdDisable() {this.inputPropertyId.setVisible(false);this.inputPropertyId.setDisable(true);}

    public TextField getInputCustomerId() {
        return inputCustomerId;
    }



    public NumField getInputRentDays() {
        return inputRentDays;
    }



    public DatePicker getInputRentdate() {
        return inputRentdate;
    }



    public Button getRentButton() {
        return rentButton;
    }



    public Button getClearButton() {
        return clearButton;
    }


}
