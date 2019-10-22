package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Panel_Perform_Maintenance extends VBox {

    private ComboBox<String> inputPropertyId = new ComboBox<>();
    // DatePicker performDate = new DatePicker();

    private Button performButton = new Button("Perform Maintenance");
    private Button clearButton = new Button("Clear");


    public Panel_Perform_Maintenance() {

        setup();
    }

    private void setup() {


        Label title1 = new Label("Input Available Property ID");



        this.getChildren().addAll(title1, inputPropertyId, performButton, clearButton);
        int width = 260;
        inputPropertyId.setMinWidth(width);
        inputPropertyId.setEditable(true);
        performButton.setMinWidth(width);
        clearButton.setMinWidth(width);
        clearButton.setOnAction(event -> clearInput());

        this.setPadding(new Insets(20, 20, 20, 20));

        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(300, 500);
    }

    public void clearInput() {
        inputPropertyId.cancelEdit();
        inputPropertyId.getEditor().clear();
    }

    public ComboBox<String> getInputPropertyId() {
        return inputPropertyId;
    }

    public void setInputPropertyId(ComboBox<String> inputPropertyId) {
        this.inputPropertyId = inputPropertyId;
    }

    public Button getPerformButton() {
        return performButton;
    }

    public void setPerformButton(Button performButton) {
        this.performButton = performButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    public void setInputPropertyIdDisable() {this.inputPropertyId.setVisible(false);
    this.inputPropertyId.setDisable(true);
    }
}
