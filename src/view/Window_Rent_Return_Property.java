package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window_Rent_Return_Property extends Stage {

    private Panel_RentProperty panel_rentProperty = new Panel_RentProperty();
    private Panel_Return_Property panel_return_property = new Panel_Return_Property();
    private RadioButton selectRent = new RadioButton("Rent Property");
    private RadioButton selectReturn = new RadioButton("Return Property");
    private ToggleGroup toggleGroup = new ToggleGroup();

    public Window_Rent_Return_Property() {

        setup();
    }

    private void setup() {
        this.setResizable(false);
        VBox root1 = new VBox(5);
        root1.setAlignment(Pos.CENTER);
        root1.setPadding(new Insets(10, 10, 10, 10));

        HBox buttonContainer = new HBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        selectReturn.setToggleGroup(toggleGroup);
        selectRent.setToggleGroup(toggleGroup);
        buttonContainer.getChildren().addAll(selectRent, selectReturn);


        HBox panelContainer = new HBox(10);
        panelContainer.getChildren().addAll(panel_rentProperty, panel_return_property);
        panel_rentProperty.setDisable(false);
        panel_return_property.setDisable(true);
        root1.getChildren().addAll(buttonContainer, panelContainer);

        selectRent.setSelected(true);
        this.setScene(new Scene(root1));
        selectRent.setOnAction(event -> {
            panel_return_property.setDisable(true);
            panel_rentProperty.setDisable(false);
        });
        selectReturn.setOnAction(event -> {
            panel_return_property.setDisable(false);
            panel_rentProperty.setDisable(true);
        });

    }
    public void Clear(){
        panel_rentProperty.clearInput();
        panel_return_property.clearInput();
    }

    public Panel_RentProperty getPanel_rentProperty() {
        return panel_rentProperty;
    }

    public void setPanel_rentProperty(Panel_RentProperty panel_rentProperty) {
        this.panel_rentProperty = panel_rentProperty;
    }

    public Panel_Return_Property getPanel_return_property() {
        return panel_return_property;
    }

    public void setPanel_return_property(Panel_Return_Property panel_return_property) {
        this.panel_return_property = panel_return_property;
    }

    public RadioButton getSelectRent() {
        return selectRent;
    }

    public void setSelectRent(RadioButton selectRent) {
        this.selectRent = selectRent;
    }

    public RadioButton getSelectReturn() {
        return selectReturn;
    }

    public void setSelectReturn(RadioButton selectReturn) {
        this.selectReturn = selectReturn;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}
