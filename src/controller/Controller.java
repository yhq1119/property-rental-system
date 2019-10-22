package controller;

import exceptions.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import utilities.DateTime;
import utilities.PropertyStatus;
import utilities.PropertyType;
import view.Alert_Information;
import view.Panel_MainWindow1;
import view.Panel_SingleProperty_Item1;
import view.Window_Property_Details;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;

@SuppressWarnings({"unchecked", "CanBeFinal"})
public class Controller {
    private Database database = new Database();
    private Panel_MainWindow1 mainWindow = new Panel_MainWindow1();
    private FlexiRentalSystem flexiRentalSystem = new FlexiRentalSystem();

    //   private RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();


    public Controller() throws MalformedURLException {

//        initLoadFile();
        loadFromDB();
        displayPropertyItems(flexiRentalSystem.getProperties());


        mainWindow.getRent_return().setOnAction(event -> {

            setRentReturnComboBox();
            mainWindow.getWindow_rent_return_property().Clear();
            mainWindow.getWindow_rent_return_property().show();
        });

        mainWindow.getMaintenance().setOnAction(event -> {
            setMaintenanceComboBox();
            mainWindow.getWindow_perform_complete_maintenance().Clear();
            mainWindow.getWindow_perform_complete_maintenance().show();
        });

        mainWindow.getWindow_addProperty1().getAddApartment().getAddProperty().setOnAction(event -> addApartment());

        mainWindow.getWindow_addProperty1().getAddSuite().getAddProperty().setOnAction(event -> addSuite());

        mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getRentButton().setOnAction(event -> {
            rentProperty();
            setRentReturnComboBox();
        });

        mainWindow.getWindow_rent_return_property().getPanel_return_property().getReturnButton().setOnAction(event -> {
            returnProperty();
            setRentReturnComboBox();
        });

        mainWindow.getWindow_database_operation().getCreateTablesButton().setOnAction(event -> creatTablesInDB());

        mainWindow.getWindow_database_operation().getDeleteAlltableButton().setOnAction(event -> deleteTablesInDB());

        mainWindow.getWindow_database_operation().getLoadFromDatabase().setOnAction(event -> loadFromDB());

        mainWindow.getWindow_database_operation().getWriteToDatebase().setOnAction(event -> {
            try {
                saveToDB();
            } catch (InsertDBException e) {
              e.popWindow();
            }
        });

        mainWindow.getWindow_perform_complete_maintenance().getPanel_perform_maintenance().getPerformButton().setOnAction(event -> {
            performMaintenance();
            setMaintenanceComboBox();
        });

        mainWindow.getWindow_perform_complete_maintenance().getPanel_complete_maintenance().getCompleteButton().setOnAction(event -> {
            completeMaintenance();
            setMaintenanceComboBox();
        });

        mainWindow.getSaveFile().setOnAction(event -> saveFile());

        mainWindow.getLoadFile().setOnAction(event -> loadFile());

        mainWindow.getBrowse_filter().getSearch().setOnAction(event -> {
            ArrayList<RentalProperty> t1 = trimByCondition();
            displayPropertyItems(t1);
            new Alert_Information("Hint", "Search Completed.");
        });

        mainWindow.getBrowse_filter().getClear().setOnAction(event -> {
            cleanSelection();
            displayPropertyItems(flexiRentalSystem.getProperties());
        });


    }

    private void creatTablesInDB() {
        database.createPropertyAndRecordTables();

    }

    private void deleteTablesInDB() {
        database.deletePropertyAndRecordTables();
    }

    private void loadFromDB() {
        database.readFromPropertyAndRecordTable(flexiRentalSystem);
        displayPropertyItems(flexiRentalSystem.getProperties());
    }

    private void saveToDB()throws InsertDBException {
        database.insertPropertyAndRecordsIntoTable(flexiRentalSystem);
    }

    private void completeMaintenance() {
        String propertyId;
        DateTime completeDate;

        try {
            propertyId = mainWindow.getWindow_perform_complete_maintenance().getPanel_complete_maintenance().getInputPropertyId().getValue();
            completeDate = transDatePickerDate(mainWindow.getWindow_perform_complete_maintenance().getPanel_complete_maintenance().getCompleteDate());
            flexiRentalSystem.getPropertyByID(propertyId).completeMaintenance(completeDate);
            new Alert_Information("Success", "Maintenance Completed.");

        } catch (StatusInvalidToCompleteMaintenance statusInvalidToCompleteMaintenance) {
            new Alert_Information("Error", "Not under Maintenance Property.");
        } catch (DateInvalidToCompleteMaintenanceException e) {
            new Alert_Information("Error", "Invalid Date.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Complete Maintenance, Check Input.");
        }
    }

    private void setMaintenanceComboBox() {
        setComboBoxItem(mainWindow.getWindow_perform_complete_maintenance().getPanel_perform_maintenance().getInputPropertyId(), PropertyStatus.AVAILABLE);
        setComboBoxItem(mainWindow.getWindow_perform_complete_maintenance().getPanel_complete_maintenance().getInputPropertyId(), PropertyStatus.UNDER_MAINTENANCE);

    }

    private void performMaintenance() {
        String propertyId;

        try {
            propertyId = mainWindow.getWindow_perform_complete_maintenance().getPanel_perform_maintenance().getInputPropertyId().getValue();

            flexiRentalSystem.getPropertyByID(propertyId).performMaintenance();
            new Alert_Information("Success", "Property is Now Under Maintenance.");

        } catch (StatusInvalidToMaintenanceException e) {
            new Alert_Information("Error", "Not Available for Maintenance.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Perform Maintenance.");
        }

    }

    private void setRentReturnComboBox() {
        setComboBoxItem(mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getInputPropertyId(), PropertyStatus.AVAILABLE);
        setComboBoxItem(mainWindow.getWindow_rent_return_property().getPanel_return_property().getInputPropertyId(), PropertyStatus.RENTED);
    }


    private void saveFile() {
        FileChooser f1 = new FileChooser();
        f1.setTitle("Save File");


        File file = f1.showSaveDialog(new Stage());
        if (file != null) {
            try {
                flexiRentalSystem.getFileHandler().writeToFile(file, flexiRentalSystem.getProperties());
                new Alert_Information("Success", "File Saved.");

            } catch (Exception e) {
                new Alert_Information("Error", "Cannot do that.");
            }
        }

    }

// --Commented out by Inspection START (2018/10/22 19:52):
//    private void initLoadFile() {
//        File file = new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\testFile\\default_data.txt");
//        try {
//            flexiRentalSystem.setProperties((ArrayList<RentalProperty>) flexiRentalSystem.getFileHandler().readFromFile(file).clone());
//        } catch (Exception e) {
//
//            new Alert_Information("Error", "Failed to load default file.");
//
//        }
//
//
//    }
// --Commented out by Inspection STOP (2018/10/22 19:52)

    private void loadFile() {

        FileChooser f1 = new FileChooser();
        f1.setTitle("Load File");

        File file = f1.showOpenDialog(new Stage());
        ArrayList<RentalProperty> temp;

        String error = "Error";
        try {
            temp = flexiRentalSystem.getFileHandler().readFromFile(file);
            flexiRentalSystem.setProperties((ArrayList<RentalProperty>) temp.clone());
            new Alert_Information("Success", "File Loaded.");

        } catch (FileNotFoundException e) {
            new Alert_Information(error, "No Found File");
        } catch (NumberOfBedroomsException e) {
            e.popWindow(error, "Load exception: Invalid Number of Rooms.");
        } catch (ReadStatusException e) {
            e.popWindow(error, "Load exception: Read Invalid Status.");
        } catch (ReadLineIsNotPremiumSuiteException e) {
            e.popWindow(error, "Load exception: Found Damaged Data of Premium Suite.");
        } catch (ReadDateException e) {
            e.popWindow(error, "Found Invalid Date Data.");
        } catch (ReadRentFeeException e) {
            e.popWindow(error, "Found Invalid Fee Data.");
        } catch (ReadRentalRecordException e) {
            e.popWindow(error, "Load exception: Found Damaged Data of Rental Record.");
        } catch (Exception e) {
            new Alert_Information("Unknown Error", "Failed to load File.");
        }
        displayPropertyItems(flexiRentalSystem.getProperties());
    }

    private void returnProperty() {
        String propertyId;
        DateTime returnDate;


        try {
            propertyId = mainWindow.getWindow_rent_return_property().getPanel_return_property().getInputPropertyId().getValue();

            returnDate = transDatePickerDate(mainWindow.getWindow_rent_return_property().getPanel_return_property().getInputReturnDate());
            if (DateTime.diffDays(flexiRentalSystem.getPropertyByID(propertyId).getRentalRecords().get(0).getRentDate(), returnDate) > 0) {
                new Alert_Information("Error", "Not A Valid Return Date.");
                return;
            }

            flexiRentalSystem.getPropertyByID(propertyId).returnProperty(returnDate);
            new Alert_Information("Success", "Property Returned.");
            database.updateProperty(flexiRentalSystem.getPropertyByID(propertyId));//////////////////////////////////////update property status
            database.updateRecord(flexiRentalSystem.getPropertyByID(propertyId).getRentalRecords().get(0));//////////////update record
        } catch (StatusInvalidForReturnException e) {
            e.popWindow("Error", "Not Rented Property.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Return, Check Input.");
        }
    }

    private void rentProperty() {
        String propertyId;
        DateTime rentdate;
        String cusId;
        int rentDays;
        try {
            rentdate = transDatePickerDate(mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getInputRentdate());
            rentDays = Integer.parseInt(mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getInputRentDays().getText());
            cusId = mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getInputCustomerId().getText();
            propertyId = mainWindow.getWindow_rent_return_property().getPanel_rentProperty().getInputPropertyId().getValue();
            try {

                flexiRentalSystem.getPropertyByID(propertyId).rent(cusId, rentdate, rentDays);
                new Alert_Information("Success", "Property Rented.");
                database.updateProperty(flexiRentalSystem.getPropertyByID(propertyId));/////////////////////////////////////////////////// update property status
           database.insertRowToRecordsTable(flexiRentalSystem.getPropertyByID(propertyId));//////////////////////////// add new record to table
            } catch (RentDaysTooLongException e) {
                e.popWindow("Error", "Cannot rent longer than 28 days.");
            } catch (StatusInvalidForRentException e) {
                e.popWindow("Error", "The property is not available now.");
            } catch (RentDaysTooShortException e) {
                e.popWindow("Error", "Cannot rent shorter than that.");
            } catch (RentForDateExceedNextMaintenanceDateException e) {
                e.popWindow("Error", "Cannot rent later than next maintenance day");
            } catch (SetDateBeforeTodayException e) {
                e.popWindow("Error", "Cannot rent at that day.");
            }
        } catch (Exception e) {
            new Alert_Information("Unknown Error", "Cannot rent now.");
        }
    }


    private void addApartment() {
        String num;
        String name;
        String suburb;
        File file;
        int rooms;

        try {
            num = getAddApartInputStreetNum();
            name = getAddApartInputStreetName();
            suburb = getAddApartInputSuburb();
            rooms = Integer.parseInt(getAddApartInputNumOfRooms());
            file = getAddPropertyImageFile();
            flexiRentalSystem.addPropertyApartment(num, name, suburb, rooms, file);
            Apartment apartment = new Apartment(num, name, suburb, rooms);
            apartment.setImagePath(file);
            new Alert_Information("Success", "New Apartment added");
            database.insertRowToPropertyTable(apartment);////////////////////////////////////////////////////////////////// add new to database
        } catch (NumberOfBedroomsException e) {
            e.popWindow("Error", "Number of bedrooms must be 1,2 or 3.");
        } catch (SamePropertyHasExistedException e) {
            e.popWindow("Error", "Same Apartment Has Existed.");
        } catch (Exception e) {
            new Alert_Information("Error", "Invalid or empty input.");
        }
    }

    private void addSuite() {
        String num;
        String name;
        String suburb;
        DateTime lastMaintenance;
        File file;


        try {
            num = getAddSuiteInputStreetNum();
            name = getAddSuiteInputStreetName();
            suburb = getAddSuiteInputSuburb();
            lastMaintenance = getAddSuiteDate();
            file = getAddPropertyImageFile();
            flexiRentalSystem.addPropertySuite(num, name, suburb, lastMaintenance, file);
            PremiumSuite premiumSuite = new PremiumSuite(num, name, suburb, lastMaintenance);
            premiumSuite.setImagePath(file);
            new Alert_Information("Success", "New Premium Suite added.");
            database.insertRowToPropertyTable(premiumSuite);////////////////////////////////////////////////////////////////////////////////add new to database
        } catch (SamePropertyHasExistedException e) {
            e.popWindow("Error", "Same Premium Suite Has Existed.");
        } catch (Exception e) {
            new Alert_Information("Error", "Unable to Add Property, Check Input.");
        }

    }

    private DateTime getAddSuiteDate() {
        DateTime d1 = null;
        try {
            d1 = transDatePickerDate(mainWindow.getWindow_addProperty1().getAddSuite().getDatePicker());
        } catch (Exception e) {
            new Alert_Information("Error", "Invalid or empty input");
        }
        return d1;
    }

    private DateTime transDatePickerDate(DatePicker Dp) {
        String s = Dp.getValue().toString();
        DateTime dateTime;
        dateTime = transStringToDateTime(s);
        return dateTime;
    }


    private DateTime transStringToDateTime(String str) {

        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));
        return new DateTime(day, month, year);

    }

    private void cleanSelection() {
        mainWindow.getBrowse_filter().getSelectType().setValue(null);
        mainWindow.getBrowse_filter().getSelectNumOfRooms().setValue(null);
        mainWindow.getBrowse_filter().getSelectStatus().setValue(null);
        mainWindow.getBrowse_filter().getInputSuburb().clear();
    }

    private void displayPropertyItems(ArrayList<RentalProperty> arrayList) {

        mainWindow.getDisplayPanel().getChildren().clear();
        for (RentalProperty pp : flexiRentalSystem.getProperties()) {
            if (!pp.isSelected()) {
                System.out.println(pp);
            }
        }
        for (RentalProperty P : arrayList) {
            Panel_SingleProperty_Item1 ss = new Panel_SingleProperty_Item1(P);
            try {
                ss.setPic(new ImageView(new Image(P.getImagePath())));
            } catch (Exception e) {
                ss.setDefaultPic();
            }
            ss.getDetailButton().setOnAction(event -> {
                try {
                    Window_Property_Details w1 = new Window_Property_Details(P, mainWindow.getHeader());
                    w1.getSaveButton().setOnAction(event1 -> {
                        w1.saveAction(P);
                        database.updateProperty(P);
                        new Alert_Information("Success","Updated Description.");
                    });
                    w1.getRentPanelButton().setOnAction(event1 -> rentSemi(w1, P));
                    w1.getReturnPanelButton().setOnAction(event1 -> returnSemi(w1, P));
                    w1.getPanel_perform_maintenance().getPerformButton().setOnAction(event1 -> performSemi(w1, P));
                    w1.getPanel_complete_maintenance().getCompleteButton().setOnAction(event1 -> completeSemi(w1, P));
                } catch (MalformedURLException e) {

                    new Alert(Alert.AlertType.ERROR);
                }
            });
            mainWindow.getDisplayPanel().getChildren().add(ss);
        }
    }

    private void rentSemi(Window_Property_Details w1, RentalProperty P) {

        DateTime rentdate;
        String cusId;
        int rentDays;
        try {
            rentdate = transDatePickerDate(w1.getRentProperty().getInputRentdate());
            rentDays = Integer.parseInt(w1.getRentProperty().getInputRentDays().getText());
            cusId = w1.getRentProperty().getInputCustomerId().getText();

            try {

                P.rent(cusId, rentdate, rentDays);
                database.updateProperty(P);////////////////////////////////////////////////////////////////////////////// update Property status
                database.insertRowToRecordsTable(P);///////////////////////////////////////////////////////////////////// insert new record
                w1.setButtonDisability(P);
                new Alert_Information("Success", "Property Rented.");


            } catch (RentDaysTooLongException e) {
                e.popWindow("Error", "Cannot rent longer than 28 days.");
            } catch (StatusInvalidForRentException e) {
                e.popWindow("Error", "The property is not available now.");
            } catch (RentDaysTooShortException e) {
                e.popWindow("Error", "Cannot rent shorter than that.");
            } catch (RentForDateExceedNextMaintenanceDateException e) {
                e.popWindow("Error", "Cannot rent later than next maintenance day");
            } catch (SetDateBeforeTodayException e) {
                e.popWindow("Error", "Cannot rent at that day.");
            }
        } catch (Exception e) {
            new Alert_Information("Unknown Error", "Cannot rent now.");
        }
    }

    private void returnSemi(Window_Property_Details w1, RentalProperty P) {

        DateTime returnDate;

        try {
            returnDate = transDatePickerDate(w1.getReturn_property().getInputReturnDate());

            if (DateTime.diffDays(P.getRentalRecords().get(0).getRentDate(), returnDate) > 0) {
                new Alert_Information("Error", "Not A Valid Return Date.");
                return;
            }
            P.returnProperty(returnDate);
            w1.setButtonDisability(P);
            new Alert_Information("Success", "Property Returned.");
            database.updateProperty(P);////////////////////////////////////////////////////////////////////////////// update Property status
            database.updateRecord(P.getRentalRecords().get(0));///////////////////////////////////////////////////////// update record

        } catch (StatusInvalidForReturnException e) {
            e.popWindow("Error", "Not Rented Property.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Return, Check Input.");
        }
    }

    private void performSemi(Window_Property_Details w1, RentalProperty P) {
        try {
            P.performMaintenance();
            new Alert_Information("Success", "Property is Now Under Maintenance.");
            database.updateProperty(P);///////////////////////////////////////////////////////////////////////////update property status
            w1.setButtonDisability(P);
        } catch (StatusInvalidToMaintenanceException e) {
            e.popWindow("Error", "Not Available for Maintenance.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Perform Maintenance.");
        }

    }

    private void completeSemi(Window_Property_Details w1, RentalProperty P) {

        DateTime completeDate;

        try {

            completeDate = transDatePickerDate(w1.getPanel_complete_maintenance().getCompleteDate());
            P.completeMaintenance(completeDate);
            w1.setButtonDisability(P);
            new Alert_Information("Success", "Maintenance Completed.");
            database.updateProperty(P);////////////////////////////////////////////////////////////////////////////update property status
        } catch (StatusInvalidToCompleteMaintenance e) {
            e.popWindow("Error", "Not under Maintenance Property.");
        } catch (DateInvalidToCompleteMaintenanceException e) {
            e.popWindow("Error", "Invalid Date.");
        } catch (Exception e) {
            new Alert_Information("Error", "Failed to Complete Maintenance, Check Input.");
        }
    }

    private ArrayList<RentalProperty> trimByCondition() {

        ArrayList<RentalProperty> tempo = (ArrayList<RentalProperty>) flexiRentalSystem.getProperties().clone();
        ArrayList<RentalProperty> tempo2 = new ArrayList<>();
        PropertyType type = mainWindow.getBrowse_filter().getSelectType().getValue();
        int numOfRooms;
        try {
            numOfRooms = mainWindow.getBrowse_filter().getSelectNumOfRooms().getValue();
        } catch (Exception e) {
            numOfRooms = -1;
        }
        PropertyStatus status = mainWindow.getBrowse_filter().getSelectStatus().getValue();
        String suburb = mainWindow.getBrowse_filter().getInputSuburb().getText();
        for (RentalProperty P : tempo) {
            if (type != null && !P.getType().equals(type)) {
                P.setSelected(false);
            }
            if (numOfRooms != -1 && P.getNumOfRooms() != numOfRooms) {
                P.setSelected(false);
            }
            if (status != null && !P.getStatus().equals(status)) {
                P.setSelected(false);
            }
            if (suburb != null && !P.getSuburb().toUpperCase().contains(suburb.toUpperCase())) {
                P.setSelected(false);
            }
        }
        for (RentalProperty P1 : tempo) {
            if (P1.isSelected()) {
                tempo2.add(P1);
            }
        }
        flexiRentalSystem.resetAllProperySelected();
        return tempo2;

    }

    private ArrayList<RentalProperty> getPropertyByStatus(PropertyStatus status) {

        ArrayList<RentalProperty> tempo = (ArrayList<RentalProperty>) flexiRentalSystem.getProperties().clone();

        ArrayList<RentalProperty> tempo2 = new ArrayList<>();
        for (RentalProperty P : tempo) {
            if (!P.getStatus().equals(status)) {
                P.setSelected(false);
            }
        }
        for (RentalProperty P1 : tempo) {
            if (P1.isSelected()) {
                tempo2.add(P1);
            }
        }
        flexiRentalSystem.resetAllProperySelected();
        return tempo2;
    }

    private void setComboBoxItem(ComboBox<String> comboBox, PropertyStatus status) {
        ArrayList<RentalProperty> tempo1 = getPropertyByStatus(status);
        comboBox.getItems().clear();
        for (RentalProperty ppp : tempo1) {
            comboBox.getItems().add(ppp.getPropertyId());
        }
    }


    public Panel_MainWindow1 getMainWindow() {
        return mainWindow;
    }

    private File getAddPropertyImageFile() {
        return mainWindow.getWindow_addProperty1().getFile();
    }


    private String getAddApartInputStreetNum() {
        return mainWindow.getWindow_addProperty1().getAddApartment().getInputStreetNum().getText();
    }

    private String getAddApartInputStreetName() {
        return mainWindow.getWindow_addProperty1().getAddApartment().getInputStreetName().getText();
    }

    private String getAddApartInputSuburb() {
        return mainWindow.getWindow_addProperty1().getAddApartment().getInputSuburb().getText();
    }

    private String getAddApartInputNumOfRooms() {
        return String.valueOf(mainWindow.getWindow_addProperty1().getAddApartment().getInputNumofRooms().getValue());
    }

    private String getAddSuiteInputStreetNum() {
        return mainWindow.getWindow_addProperty1().getAddSuite().getInputStreetNum().getText();
    }

    private String getAddSuiteInputStreetName() {
        return mainWindow.getWindow_addProperty1().getAddSuite().getInputStreetName().getText();
    }

    private String getAddSuiteInputSuburb() {
        return mainWindow.getWindow_addProperty1().getAddSuite().getInputSuburb().getText();
    }

}
