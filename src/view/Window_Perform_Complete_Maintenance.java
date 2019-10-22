package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window_Perform_Complete_Maintenance extends Stage {

    private Panel_Perform_Maintenance panel_perform_maintenance = new Panel_Perform_Maintenance();
    private Panel_Complete_Maintenance panel_complete_maintenance = new Panel_Complete_Maintenance();
    private RadioButton selectPerform = new RadioButton("Perform Maintenance");
    private RadioButton selectComplete = new RadioButton("Complete Maintenance");
    private ToggleGroup toggleGroup = new ToggleGroup();

    public Window_Perform_Complete_Maintenance() {
        setup();
    }

    private void setup() {

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        HBox buttonContainer = new HBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        HBox panelContainer = new HBox(10);
        panelContainer.getChildren().addAll(panel_perform_maintenance, panel_complete_maintenance);
        root.getChildren().addAll(buttonContainer, panelContainer);

        this.setScene(new Scene(root));
        panel_perform_maintenance.setDisable(false);
        panel_complete_maintenance.setDisable(true);
        selectPerform.setSelected(true);
        selectComplete.setToggleGroup(toggleGroup);
        selectPerform.setToggleGroup(toggleGroup);
        selectPerform.setOnAction(event -> {
            panel_perform_maintenance.setDisable(false);
            panel_complete_maintenance.setDisable(true);
        });
        selectComplete.setOnAction(event -> {
            panel_perform_maintenance.setDisable(true);
            panel_complete_maintenance.setDisable(false);
        });
        buttonContainer.getChildren().addAll(selectPerform, selectComplete);
        this.setResizable(false);

    }
    public void Clear(){
        panel_complete_maintenance.clearInput();
        panel_perform_maintenance.clearInput();
    }

    public Panel_Perform_Maintenance getPanel_perform_maintenance() {
        return panel_perform_maintenance;
    }

    public void setPanel_perform_maintenance(Panel_Perform_Maintenance panel_perform_maintenance) {
        this.panel_perform_maintenance = panel_perform_maintenance;
    }

    public Panel_Complete_Maintenance getPanel_complete_maintenance() {
        return panel_complete_maintenance;
    }

    public void setPanel_complete_maintenance(Panel_Complete_Maintenance panel_complete_maintenance) {
        this.panel_complete_maintenance = panel_complete_maintenance;
    }

    public RadioButton getSelectPerform() {
        return selectPerform;
    }

    public void setSelectPerform(RadioButton selectPerform) {
        this.selectPerform = selectPerform;
    }

    public RadioButton getSelectComplete() {
        return selectComplete;
    }

    public void setSelectComplete(RadioButton selectComplete) {
        this.selectComplete = selectComplete;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}
