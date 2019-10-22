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
import javafx.stage.StageStyle;


public class Alert_Information extends Stage {


    private Button buttonOK = new Button("OK");
    private Label header = new Label();
    private Label content = new Label();

    public Alert_Information(String header1, String content1) {
        setup(header1, content1);
        this.show();

    }

    public Alert_Information(String header1, String content1,boolean b) {
        setup(header1, content1);
       if (b){
           this.show();
       }

    }



    private void setup(String header1, String content1) {
        header.setText(header1);
        content.setText(content1);
        header.setFont(Font.font(20));

        VBox root = new VBox(30);
        HBox buttonContainer = new HBox(20);
        root.getChildren().addAll(header, content, buttonContainer);
        int width = 100;
        buttonOK.setOnAction(event -> {
            this.close();
        });

        buttonOK.setPrefWidth(width);


        buttonContainer.getChildren().addAll(buttonOK);
        buttonContainer.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.setPrefHeight(180);
        root.setPrefWidth(300);
        root.setStyle("-fx-border-width: 2;-fx-border-color:skyblue");
        this.setScene(new Scene(root));

        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.initStyle(StageStyle.TRANSPARENT);
        this.setAlwaysOnTop(true);

    }


}


