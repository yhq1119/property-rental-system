package view;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.NumField;
import utilities.PropertyType;


public class Panel_AddProperty extends VBox {

    private Label title1 = new Label("Generated ID");

    private Label displayId = new Label("Empty");


    private Label inputTitle1 = new Label("Street Number");
    private NumField inputStreetNum = new NumField();

    private Label inputTitle2 = new Label("Street Name");
    private TextField inputStreetName = new TextField();

    private Label inputTitle3 = new Label("Suburb");
    private TextField inputSuburb = new TextField();

    private Button addProperty = new Button("Add");

    private Button clear = new Button("Clear");

    private Label inputTitle5 = new Label("Select Last Maintenance Date");
    private DatePicker datePicker = new DatePicker();


    private VBox numOfroomsPart = new VBox(5);

    private Label inputTitle4 = new Label("Number of Bedrooms");
    private ComboBox<Integer> inputNumofRooms = new ComboBox<>();


    private StringBinding bind_displayId = new StringBinding() {
        @Override
        protected String computeValue() {
            return "??";
        }
    };
    private StringProperty readFromParameter = new SimpleStringProperty();


    public Panel_AddProperty(PropertyType P) {

        setup(P);
    }

    private void setup(PropertyType P) {

        int width = 260;
        displayId.setMinWidth(width);
        datePicker.setMinWidth(width);
        inputNumofRooms.setMinWidth(width);
//        super.setPropertyId("A_"+streetnum+fillup(streetname)+fillup(suburb));


        if (P.equals(PropertyType.APARTMENT)) {

            numOfroomsPart.getChildren().addAll(inputTitle4, inputNumofRooms);
        }
        if (P.equals(PropertyType.PREMIUM_SUITE)) {
            numOfroomsPart.getChildren().addAll(inputTitle5, datePicker);

        }
        inputNumofRooms.setEditable(true);
        inputNumofRooms.getItems().addAll(1, 2, 3);


        displayId.setStyle("-fx-border-color: dodgerblue;-fx-border-radius: 3;-fx-border-width: 2");


        HBox buttonContainer = new HBox(5);
        buttonContainer.getChildren().addAll(addProperty, clear);
        addProperty.setPrefWidth(90);
        clear.setPrefWidth(90);

        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20, 20, 20, 20));
        buttonContainer.setSpacing(20);
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setSpacing(10);

        this.getChildren().addAll(inputTitle1, inputStreetNum, inputTitle2, inputStreetName, inputTitle3, inputSuburb, numOfroomsPart, buttonContainer);

        this.setAlignment(Pos.CENTER_LEFT);

        this.setPrefSize(300, 400);
    }

    public void clearInput() {
        inputStreetName.clear();
        inputStreetNum.clear();
        inputSuburb.clear();
        inputNumofRooms.getEditor().clear();
        datePicker.getEditor().clear();

    }

    public Label getInputTitle5() {
        return inputTitle5;
    }

    public void setInputTitle5(Label inputTitle5) {
        this.inputTitle5 = inputTitle5;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public Label getTitle1() {
        return title1;
    }

    public void setTitle1(Label title1) {
        this.title1 = title1;
    }

    public Label getDisplayId() {
        return displayId;
    }

    public void setDisplayId(Label displayId) {
        this.displayId = displayId;
    }

    public Label getInputTitle1() {
        return inputTitle1;
    }

    public void setInputTitle1(Label inputTitle1) {
        this.inputTitle1 = inputTitle1;
    }

    public TextField getInputStreetNum() {
        return inputStreetNum;
    }

    public void setInputStreetNum(NumField inputStreetNum) {
        this.inputStreetNum = inputStreetNum;
    }

    public Label getInputTitle2() {
        return inputTitle2;
    }

    public void setInputTitle2(Label inputTitle2) {
        this.inputTitle2 = inputTitle2;
    }

    public TextField getInputStreetName() {
        return inputStreetName;
    }

    public void setInputStreetName(TextField inputStreetName) {
        this.inputStreetName = inputStreetName;
    }

    public Label getInputTitle3() {
        return inputTitle3;
    }

    public void setInputTitle3(Label inputTitle3) {
        this.inputTitle3 = inputTitle3;
    }

    public TextField getInputSuburb() {
        return inputSuburb;
    }

    public void setInputSuburb(TextField inputSuburb) {
        this.inputSuburb = inputSuburb;
    }

    public Button getAddProperty() {
        return addProperty;
    }

    public void setAddProperty(Button addProperty) {
        this.addProperty = addProperty;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }

    public VBox getNumOfroomsPart() {
        return numOfroomsPart;
    }

    public void setNumOfroomsPart(VBox numOfroomsPart) {
        this.numOfroomsPart = numOfroomsPart;
    }

    public Label getInputTitle4() {
        return inputTitle4;
    }

    public void setInputTitle4(Label inputTitle4) {
        this.inputTitle4 = inputTitle4;
    }

    public ComboBox<Integer> getInputNumofRooms() {
        return inputNumofRooms;
    }

    public void setInputNumofRooms(ComboBox<Integer> inputNumofRooms) {
        this.inputNumofRooms = inputNumofRooms;
    }

    public String getBind_displayId() {
        return bind_displayId.get();
    }

    public StringBinding bind_displayIdProperty() {
        return bind_displayId;
    }

    public String getReadFromParameter() {
        return readFromParameter.get();
    }

    public StringProperty readFromParameterProperty() {
        return readFromParameter;
    }

    public void setReadFromParameter(String readFromParameter) {
        this.readFromParameter.set(readFromParameter);
    }
}
