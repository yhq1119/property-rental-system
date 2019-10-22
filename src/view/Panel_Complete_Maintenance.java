package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Panel_Complete_Maintenance extends VBox {

    private ComboBox<String> inputPropertyId = new ComboBox<>();
    private DatePicker completeDate = new DatePicker();

    private Button completeButton = new Button("Complete Maintenance");
    private Button clearButton = new Button("Clear");


    public Panel_Complete_Maintenance(){

        setup();
    }

    private void setup(){


        Label title1 = new Label("Input Property ID Under Maintenance");
        Label title2 = new Label("Choose Complete Date");

        this.getChildren().addAll(title1,inputPropertyId,title2,completeDate,completeButton,clearButton);
        int width = 260;
        inputPropertyId.setMinWidth(width);
        inputPropertyId.setEditable(true);
        completeDate.setMinWidth(width);
        completeButton.setMinWidth(width);
        clearButton.setMinWidth(width);
        clearButton.setOnAction(event -> clearInput());

        this.setPadding(new Insets(20,20,20,20));

        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(300,500);


    }

    public void clearInput() {
        inputPropertyId.cancelEdit();
        inputPropertyId.getEditor().clear();
        completeDate.getEditor().clear();
    }


    public ComboBox<String> getInputPropertyId() {
        return inputPropertyId;
    }

    public void setInputPropertyId(ComboBox<String> inputPropertyId) {
        this.inputPropertyId = inputPropertyId;
    }

    public DatePicker getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(DatePicker completeDate) {
        this.completeDate = completeDate;
    }

    public Button getCompleteButton() {
        return completeButton;
    }

    public void setCompleteButton(Button completeButton) {
        this.completeButton = completeButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    public void setInputPropertyIdDisable() {this.inputPropertyId.setDisable(true);this.inputPropertyId.setVisible(false);
    }
}
