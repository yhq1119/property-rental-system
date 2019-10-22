package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Apartment;
import model.PremiumSuite;
import model.RentalProperty;
import model.RentalRecord;
import utilities.PropertyStatus;
import utilities.Records;

import java.net.MalformedURLException;
import java.util.ArrayList;


@SuppressWarnings("ALL")
public class Window_Property_Details extends Stage {
    private Panel_Return_Property return_property = new Panel_Return_Property();
    private Panel_RentProperty rentProperty = new Panel_RentProperty();
    private Panel_Perform_Maintenance panel_perform_maintenance = new Panel_Perform_Maintenance();
    private Panel_Complete_Maintenance panel_complete_maintenance = new Panel_Complete_Maintenance();

    private Stage showstage1 = new Stage();
    private Scene sceneRent = new Scene(rentProperty);
    private Stage showstage2 = new Stage();
    private Scene sceneReturn = new Scene(return_property);
    private Stage showstage3 = new Stage();
    private Scene scenePerform = new Scene(panel_perform_maintenance);
    private Stage showstage4 = new Stage();
    private Scene sceneComplete = new Scene(panel_complete_maintenance);

    private HBox root = new HBox(10);

    private TableView tableView = new TableView();
    private ObservableList<Records> data;
    private Alert_Information a1 = new Alert_Information("Hint", "This property has No rental records",false);


    private ImageView displayPic = new ImageView();
    private TableColumn index = new TableColumn("#");
    private TableColumn customerId = new TableColumn("CustomerId");
    private TableColumn rentDate = new TableColumn("Rent Date");
    private TableColumn estiDate = new TableColumn("Estimated Return Date");
    private TableColumn actlDate = new TableColumn("Actual Return Date");
    private TableColumn rentFee = new TableColumn("Rent Fee");
    private TableColumn lateFee = new TableColumn("Late Fee");
    private HBox picFrame = new HBox(10);
    private ListView<String> displayInfo = new ListView<>();

    private Button editButton = new Button("Edit");
    private Button saveButton = new Button("Save");
    private TextArea editDescription = new TextArea();
    private Button rentButton = new Button("Rent");

    private Button returnButton = new Button("Return");
    private Button performButton = new Button("Perform");
    private Button completeButton = new Button("Complete");


    public Window_Property_Details() {
        MenuBar M1 = new MenuBar();
        setup(M1);
        this.show();
    }

    public Window_Property_Details(RentalProperty P, MenuBar Menubar) throws MalformedURLException {
        setup(Menubar);
        setupWithPropertyInfo(P);
        this.show();
    }

    private void setup(MenuBar menuBar) {

        int buttonWidth = 160;
        editButton.setPrefWidth(buttonWidth);
        saveButton.setPrefWidth(buttonWidth);
        rentButton.setPrefWidth(buttonWidth);
        returnButton.setPrefWidth(buttonWidth);
        performButton.setPrefWidth(buttonWidth);
        completeButton.setPrefWidth(buttonWidth);
        displayPic = new ImageView(new Image("/images/default.png"));
        root.setPrefSize(1024, 600);
        root.setPadding(new Insets(10, 10, 10, 10));

        VBox left = new VBox(10);
        left.setPrefSize(744, 580);
        picFrame.setPrefSize(714, 358);
        picFrame.getChildren().add(displayPic);
        displayPic.setFitWidth(744);
        displayPic.setFitHeight(358);

        ScrollPane recordFrame = new ScrollPane();
        tableView.setMinWidth(744);
        tableView.getColumns().addAll(index, customerId, rentDate, estiDate, actlDate, rentFee, lateFee);
        index.setMaxWidth(30);
        estiDate.setMinWidth(160);
        actlDate.setMinWidth(160);
        recordFrame.setContent(tableView);
        left.getChildren().addAll(picFrame, recordFrame);

        VBox right = new VBox(5);
        right.setPrefSize(260, 580);

        Label title1 = new Label("Description");
        HBox buttonContainer1 = new HBox(10);
        buttonContainer1.getChildren().addAll(editButton, saveButton);
        saveButton.setDisable(true);

        rentProperty.setInputPropertyIdDisable();
        return_property.setInputPropertyIdDisable();
        panel_perform_maintenance.setInputPropertyIdDisable();
        panel_complete_maintenance.setInputPropertyIdDisable();


        Label title2 = new Label("Property Information");
        Label title3 = new Label("Property Action");
        HBox buttonContainer2 = new HBox(10);
        buttonContainer2.getChildren().addAll(rentButton, returnButton);


        Label title4 = new Label("Maintenance Action");
        HBox buttonContainer3 = new HBox(10);
        buttonContainer3.getChildren().addAll(performButton, completeButton);

        showstage1.initModality(Modality.APPLICATION_MODAL);
        showstage2.initModality(Modality.APPLICATION_MODAL);
        showstage3.initModality(Modality.APPLICATION_MODAL);
        showstage4.initModality(Modality.APPLICATION_MODAL);

        rentButton.setOnAction(event -> {
            showstage1.close();
            showstage1.setScene(sceneRent);
            showstage1.showAndWait();
        });
        returnButton.setOnAction(event -> {

            showstage2.close();
            showstage2.setScene(sceneReturn);

            showstage2.showAndWait();
        });
        performButton.setOnAction(event -> {

            showstage3.close();

            showstage3.setScene(scenePerform);
            showstage3.showAndWait();
        });
        completeButton.setOnAction(event -> {

            showstage4.close();
            showstage4.setScene(sceneComplete);
            showstage4.showAndWait();
        });

        ScrollPane scrollDescription = new ScrollPane();
        scrollDescription.setPrefHeight(240);
        scrollDescription.setContent(editDescription);
        editDescription.setPrefWidth(235);
        editDescription.setMinHeight(500);
        editDescription.setWrapText(true);
        editDescription.setEditable(false);
        editDescription.setDisable(true);
        displayInfo.setPrefHeight(160);
        right.getChildren().addAll(title1, buttonContainer1, scrollDescription, title2, displayInfo, title3, buttonContainer2, title4, buttonContainer3);
        root.getChildren().addAll(left, right);

        HBox root1 = new HBox();
        root1.getChildren().addAll(menuBar, root);
        this.setScene(new Scene(root1));
        this.setResizable(false);


    }

    private void setupWithPropertyInfo(RentalProperty P) throws MalformedURLException {
        if (P==null){
            throw new NullPointerException();
        }

        System.out.println(P.toString());
        for(RentalRecord R1:P.getRentalRecords()){
            System.out.println(R1.toString());
        }
        System.out.println("A1");
        if (P.getRentalRecords().size() == 0) {
            a1.showAndWait();
        }
        picFrame.getChildren().clear();
        if (P.getImagePath() != null || (P.getImagePath()).equals("")) {
            setDisplayPic(new ImageView(new Image(P.getImagePath())));
        } else {
            setDisplayPic(new ImageView(new Image("/images/default.png")));
        }
        picFrame.getChildren().add(displayPic);
        getEditDescription().setText(P.getDescription());
        if (P instanceof Apartment) {
            getDisplayInfo().getItems().addAll("Property ID: " + P.getPropertyId(), "Property Type: " + P.getStringType(), "Property Status: " + P.getStringStatus(), "Address: " + P.getStreetNum() + " " + P.getStreetName() + " " + P.getSuburb(), "Rent Rate: $" + String.format("%.2f", P.getRentRate()), "Late Rate: $" + String.format("%.2f", P.getLateRate()));
        }
        if (P instanceof PremiumSuite) {
            getDisplayInfo().getItems().addAll("Property ID: " + P.getPropertyId(), "Property Type: " + P.getStringType(), "Property Status: " + P.getStringStatus(), "Address: " + P.getStreetNum() + " " + P.getStreetName() + " " + P.getSuburb(), "Last Mainenance Date: " + ((PremiumSuite) P).getLastMaintenanceDate().getFormattedDate(), "Rent Rate: $" + String.format("%.2f", P.getRentRate()), "Late Rate: $" + String.format("%.2f", P.getLateRate()));

        }


        setButtonDisability(P);

        ArrayList<Records> records1 = new ArrayList<>();

        for (int i = 0; i < P.getRentalRecords().size(); i++) {
            records1.add(new Records(P.getRentalRecords().get(i), i + 1));
        }


        data = FXCollections.observableArrayList(records1);
        index.setCellValueFactory(new PropertyValueFactory<Records, Integer>("index"));
        customerId.setCellValueFactory(new PropertyValueFactory<Records, String>("customerId"));
        rentDate.setCellValueFactory(new PropertyValueFactory<Records, String>("rentDate"));
        estiDate.setCellValueFactory(new PropertyValueFactory<Records, String>("estiReturnDate"));
        actlDate.setCellValueFactory(new PropertyValueFactory<Records, String>("acutReturnDate"));
        rentFee.setCellValueFactory(new PropertyValueFactory<Records, String>("rentFee"));
        lateFee.setCellValueFactory(new PropertyValueFactory<Records, String>("lateFee"));

        tableView.setItems(data);


        editButton.setOnAction(event -> editAction());


    }

    private void editAction() {
        saveButton.setDisable(false);
        editDescription.setDisable(false);
        editDescription.setEditable(true);
    }

    public void saveAction(RentalProperty P) {
        saveButton.setDisable(true);
        editDescription.setEditable(false);
        editDescription.setDisable(true);
        P.setDescription(editDescription.getText().replaceAll("'", ""));
    }

    public void setButtonDisability(RentalProperty P) {
        if (P.getStatus().equals(PropertyStatus.AVAILABLE)) {
            rentButton.setDisable(false);
            returnButton.setDisable(true);
            performButton.setDisable(false);
            completeButton.setDisable(true);
        } else if (P.getStatus().equals(PropertyStatus.UNDER_MAINTENANCE)) {
            rentButton.setDisable(true);
            returnButton.setDisable(true);
            performButton.setDisable(true);
            completeButton.setDisable(false);

        } else if (P.getStatus().equals(PropertyStatus.RENTED)) {

            rentButton.setDisable(true);
            returnButton.setDisable(false);
            performButton.setDisable(true);
            completeButton.setDisable(true);

        }
    }

    public Panel_Return_Property getReturn_property() {
        return return_property;
    }

    public Panel_RentProperty getRentProperty() {
        return rentProperty;
    }

    public Panel_Perform_Maintenance getPanel_perform_maintenance() {
        return panel_perform_maintenance;
    }

    public Panel_Complete_Maintenance getPanel_complete_maintenance() {
        return panel_complete_maintenance;
    }

    public Button getRentPanelButton() {
        return rentProperty.getRentButton();
    }

    public Button getReturnPanelButton() {
        return return_property.getReturnButton();
    }

    public Button getPerformPanelButton() {
        return panel_perform_maintenance.getPerformButton();
    }

    public Button getCompletePanelButton() {
        return panel_complete_maintenance.getCompleteButton();
    }

    private void setDisplayPic(ImageView displayPic) {
        this.displayPic = displayPic;
        displayPic.setFitWidth(744);
        displayPic.setFitHeight(358);


    }

    public TableColumn getCustomerId() {
        return customerId;
    }

    public void setCustomerId(TableColumn customerId) {
        this.customerId = customerId;
    }

    public TableColumn getRentDate() {
        return rentDate;
    }

    public void setRentDate(TableColumn rentDate) {
        this.rentDate = rentDate;
    }

    public TableColumn getEstiDate() {
        return estiDate;
    }

    public void setEstiDate(TableColumn estiDate) {
        this.estiDate = estiDate;
    }

    public TableColumn getActlDate() {
        return actlDate;
    }

    public void setActlDate(TableColumn actlDate) {
        this.actlDate = actlDate;
    }

    public TableColumn getRentFee() {
        return rentFee;
    }

    public void setRentFee(TableColumn rentFee) {
        this.rentFee = rentFee;
    }

    public TableColumn getLateFee() {
        return lateFee;
    }

    public void setLateFee(TableColumn lateFee) {
        this.lateFee = lateFee;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    private TextArea getEditDescription() {
        return editDescription;
    }

    public void setEditDescription(TextArea editDescription) {
        this.editDescription = editDescription;
    }

    private ListView<String> getDisplayInfo() {
        return displayInfo;
    }

    public void setDisplayInfo(ListView<String> displayInfo) {
        this.displayInfo = displayInfo;
    }

    public Button getRentButton() {
        return rentButton;
    }

    public void setRentButton(Button rentButton) {
        this.rentButton = rentButton;
    }

    public Button getReturnButton() {
        return returnButton;
    }

    public void setReturnButton(Button returnButton) {
        this.returnButton = returnButton;
    }

    public Button getPerformButton() {
        return performButton;
    }

    public void setPerformButton(Button performButton) {
        this.performButton = performButton;
    }

    public Button getCompleteButton() {
        return completeButton;
    }

    public void setCompleteButton(Button completeButton) {
        this.completeButton = completeButton;
    }
}
