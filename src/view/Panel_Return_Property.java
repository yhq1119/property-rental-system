package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Panel_Return_Property extends VBox {

    private ComboBox<String> inputPropertyId = new ComboBox<>();

  //  TextField inputCustomerId = new TextField();

  //  NumField inputRentDays = new NumField();
  private Label displayCustomerId = new Label();

    private SimpleStringProperty s1 ;

    private DatePicker inputReturnDate = new DatePicker();

    private Button returnButton = new Button("Return");

    private Button clearButton = new Button("Clear");

    public Panel_Return_Property() {
        setup();
    }

    private void setup() {




        Label title1 = new Label("Input Rented Property ID");

        Label title2 = new Label("Customer ID");

        Label title3 = new Label("Choose Return Date");

       // Label title4 = new Label("Input Rent Days");
        inputPropertyId.setEditable(true);
        this.getChildren().addAll(title1, inputPropertyId, title3, inputReturnDate,  returnButton, clearButton);

        int width = 260;

        inputPropertyId.setMinWidth(width);
        displayCustomerId.setMinWidth(width);
        displayCustomerId.setMinHeight(30);
        displayCustomerId.setStyle("-fx-border-color: dodgerblue;-fx-border-radius: 3;-fx-border-width: 2");
        inputReturnDate.setMinWidth(width);


        returnButton.setMinWidth(width);
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
        inputReturnDate.getEditor().clear();
    }

    public void setS1(String s){
        s1 = new SimpleStringProperty(s);
        displayCustomerId.textProperty().bind(s1);
    }

    public void addItemsToInputPropertyId(String s) {

        inputPropertyId.getItems().add(s);
    }

    public ComboBox<String> getInputPropertyId() {
        return inputPropertyId;
    }

    public void setInputPropertyIdDisable() {
        this.inputPropertyId.setVisible(false);this.inputPropertyId.setDisable(true);
    }



    public Button getClearButton() {
        return clearButton;
    }

    public Label getDisplayCustomerId() {
        return displayCustomerId;
    }

    public void setDisplayCustomerId(Label displayCustomerId) {
        this.displayCustomerId = displayCustomerId;
    }

    public DatePicker getInputReturnDate() {
        return inputReturnDate;
    }

    public Button getReturnButton() {
        return returnButton;
    }

}
