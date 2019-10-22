package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog_Confirm extends Stage {


    private Button buttonOK = new Button("Yes");

    private Button buttonNo = new Button("No");

    private Label header = new Label("Sure To Quit?");

    public Dialog_Confirm() {
        header.setFont(Font.font(20));

        VBox root = new VBox(30);
        HBox buttonContainer = new HBox(20);
        root.getChildren().addAll(header, buttonContainer);
        int width = 100;
        buttonNo.setOnAction(event -> {
            this.close();
        });
        buttonNo.setPrefWidth(width);
        buttonOK.setPrefWidth(width);
        buttonOK.setOnAction(event -> {
            System.exit(0);
        });

        buttonContainer.getChildren().addAll(buttonOK, buttonNo);
        buttonContainer.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.setPrefHeight(180);
        root.setPrefWidth(300);
        this.setScene(new Scene(root));

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.show();
    }

    public Button getButtonOK() {
        return buttonOK;
    }

    public void setButtonOK(Button buttonOK) {
        this.buttonOK = buttonOK;
    }

    public Button getButtonNo() {
        return buttonNo;
    }

    public void setButtonNo(Button buttonNo) {
        this.buttonNo = buttonNo;
    }

    public Label getHeader() {
        return header;
    }

    public void setHeader(Label header) {
        this.header = header;
    }
}

