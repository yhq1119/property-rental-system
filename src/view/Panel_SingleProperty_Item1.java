package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.RentalProperty;

@SuppressWarnings("ALL")
public class Panel_SingleProperty_Item1 extends VBox {

    private ImageView Pic;
    private Button detailButton = new Button("Get Details");
    //  Button rentButton = new Button("Rent");
    private Label header = new Label();
    private Label content = new Label();
    private HBox picFrame = new HBox();

    private Label price = new Label();

    public Panel_SingleProperty_Item1(RentalProperty P) {


        setup();

        header.setText(P.getStringType() + " ID: " + P.getPropertyId());

        content.setText(P.getStreetNum() + " " + P.getStreetName().toUpperCase() + " " + P.getSuburb().toUpperCase());
        content.setWrapText(true);
        price.setText("Price: $" + String.format("%.2f", P.getRentRate()) + "/Day");
        try {
            if (P.getImagePath() == null || P.getImagePath().equals("")) {
                setDefaultPic();
            } else {
                setPic(new ImageView(new Image(P.getImagePath())));
            }
        } catch (Exception e) {

            setDefaultPic();
        }


    }

    public Panel_SingleProperty_Item1() {

        setup();

    }


    private void setup() {

        detailButton.setPrefWidth(80);
        //   rentButton.setPrefWidth(55);
        Pic = new ImageView(new Image("/images/default.png"));
        header.setText("Property ID Here");
        header.setPrefWidth(170);
        content.setText("Address Here");
        content.setPrefWidth(180);
        price.setPrefWidth(110);
        price.setText("Price Here");


        picFrame.getChildren().add(Pic);
        Pic.setFitWidth(300);
        Pic.setFitHeight(180);
        picFrame.setPrefWidth(300);
        picFrame.setPrefHeight(180);

        HBox h1 = new HBox(40);
        h1.getChildren().addAll(header, detailButton);
        h1.setAlignment(Pos.CENTER_LEFT);
        h1.setPrefWidth(300);
        h1.setPrefHeight(30);
        h1.setPadding(new Insets(5, 5, 5, 5));

        HBox h2 = new HBox();
        h2.getChildren().addAll(content, price);
        h2.setPrefWidth(300);
        h2.setPrefHeight(30);
        h2.setPadding(new Insets(0, 5, 5, 5));

        this.getChildren().addAll(picFrame, h1, h2);
        this.setPrefHeight(300);
        this.setMaxSize(300, 240);
        this.setStyle("-fx-background-color: lightblue");


    }

    //getters
    public ImageView getPic() {
        return Pic;
    }

    public Button getDetailButton() {
        return detailButton;
    }

    //   public Button getRentButton() {        return rentButton;    }

    public Label getHeader() {
        return header;
    }

    public Label getContent() {
        return content;
    }

    public Label getPrice() {
        return price;
    }

    //setters

    public void setPic(ImageView pic) {
        picFrame.getChildren().clear();
        pic.setFitHeight(180);
        pic.setFitWidth(300);
        picFrame.getChildren().add(pic);
    }

    public void setDefaultPic() {
        ImageView pic = new ImageView(new Image("/images/default.png"));

        picFrame.getChildren().clear();
        pic.setFitHeight(180);
        pic.setFitWidth(300);
        picFrame.getChildren().add(pic);

    }

    public void setDetailButton(Button detailButton) {
        this.detailButton = detailButton;
    }


    public void setHeader(Label header) {
        this.header = header;
    }

    public void setContent(Label content) {
        this.content = content;
    }

    public void setPrice(Label price) {
        this.price = price;
    }
}
