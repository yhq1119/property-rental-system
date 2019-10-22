package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.PropertyType;

import java.io.File;

public class Window_AddProperty1 extends Stage {

    private VBox root1 = new VBox(0);
    private HBox root = new HBox(10);
    private Button selectpic = new Button();
    private File file;


    private RadioButton apartment_toggle = new RadioButton("Apartment");
    private RadioButton premiumSuite_toggle = new RadioButton("Premium Suite");
    private ToggleGroup toggleGroup = new ToggleGroup();
    private Panel_AddProperty addApartment = new Panel_AddProperty(PropertyType.APARTMENT);
    private Panel_AddProperty addSuite = new Panel_AddProperty(PropertyType.PREMIUM_SUITE);


    private VBox panel = new VBox();

    public Window_AddProperty1() {
        setup();
    }

    @SuppressWarnings("ConstantConditions")
    private void setup() {
        this.setResizable(false);
        file = new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\pics");


        addApartment.getClear().setOnAction(e -> {

            clearAllinput();
        });

        addSuite.getClear().setOnAction(e -> {

            selectpic.setGraphic(new ImageView(new Image("/images/default.png")));
            file = null;
            addSuite.clearInput();

        });


        root.getChildren().addAll(root1, selectpic);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 20, 10, 0));
        selectpic.setMinSize(400, 240);
        selectpic.setMaxSize(400, 240);
        selectpic.setGraphic(new ImageView(new Image("/images/default.png")));

        selectpic.setOnAction(event -> {
            FileChooser f1 = new FileChooser();
            this.file = f1.showOpenDialog(this);
            if (file != null) {
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getPath());
            }
            try {
                Image tempo = new Image(file.toURI().toURL().toExternalForm());
                if (tempo == null) {
                    new Alert_Information("Error", "Not an Image File");
                }

                ImageView s1 = new ImageView(new Image(file.toURI().toURL().toExternalForm()));
                s1.setFitWidth(400);
                s1.setFitHeight(240);
                selectpic.setGraphic(s1);
            } catch (Exception e) {
                file = null;
                selectpic.setGraphic(new ImageView(new Image("/images/default.png")));
            }
        });


        HBox togglesContainer = new HBox(5);
        togglesContainer.getChildren().addAll(apartment_toggle, premiumSuite_toggle);
        togglesContainer.setAlignment(Pos.CENTER);
        togglesContainer.setSpacing(20);

        root1.getChildren().addAll(togglesContainer, panel);
        root1.setAlignment(Pos.CENTER);
        root1.setMinHeight(500);
        root1.setMinWidth(300);

        this.setScene(new Scene(root));


        this.panel.getChildren().clear();
        panel.getChildren().add(addApartment);
        apartment_toggle.setSelected(true);


        apartment_toggle.setToggleGroup(toggleGroup);
        premiumSuite_toggle.setToggleGroup(toggleGroup);


        apartment_toggle.setOnAction(event -> {

            panel.getChildren().clear();
            panel.getChildren().add(addApartment);

        });


        premiumSuite_toggle.setOnAction(event -> {
            panel.getChildren().clear();
            panel.getChildren().add(addSuite);
        });


    }

    public void clearAllinput() {
        selectpic.setGraphic(new ImageView(new Image("/images/default.png")));
        file = null;
        addApartment.clearInput();
        addSuite.clearInput();

    }

    public File getFile() {
        return file;
    }

    public Panel_AddProperty getAddApartment() {
        return addApartment;
    }

    public Panel_AddProperty getAddSuite() {
        return addSuite;
    }

}
