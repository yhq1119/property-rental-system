package model;

import exceptions.*;
import utilities.DateTime;
import utilities.PropertyStatus;
import utilities.PropertyType;

import java.io.File;
import java.util.ArrayList;

public abstract class RentalProperty {

    private String propertyId;
    private String streetNum;       //integer to string
    private String streetName;
    private String suburb;
    private PropertyType type;            //use enum to set
    private PropertyStatus status;          //use enum to set
    private int numOfRooms;         //integer to string
    private double rentRate;        //normal price to rent
    private double lateRate;        //penalty price when late
    private ArrayList<RentalRecord> rentalRecords;

    private File imagePath;
    private String description;

    private boolean isSelected = true;


    RentalProperty(String streetNum, String streetName, String suburb) {

        setAllAttributesToNone();
        setStreetNum(streetNum);
        setStreetName(streetName);
        setSuburb(suburb);

    }


    private void setAllAttributesToNone() {

        rentalRecords = new ArrayList<>();

        String s = "none";

        setPropertyId(s);
        setStreetNum(s);
        setStreetName(s);
        setSuburb(s);

        setStatus(PropertyStatus.AVAILABLE);
//        setImagePath(new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\pics\\default.png"));
        setDescription("This is a description for property!");
        numOfRooms = -1;

    }

    //getters:


    public File getImageFile() {
        return imagePath;
    }

    public String getImagePath() {
        String s = "/images/default.png";

        try {
            return imagePath.toURI().toURL().toExternalForm();
        } catch (Exception e) {
            return s;
        }
    }


    public String getDescription() {
        return description;
    }

    public double getRentRate() {
        return rentRate;
    }

    public String getImageName() {
        try {
            return imagePath.getName();
        } catch (Exception e) {
            return "Not Set";
        }
    }

    String getImageNameString() {
        if (imagePath != null) {
            return imagePath.getName();
        } else {
            return "none";
        }
    }

    public double getLateRate() {
        return lateRate;
    }

    public ArrayList<RentalRecord> getRentalRecords() {
        return rentalRecords;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public PropertyType getType() {
        return type;
    }

    public String getStringType() {
        if (type.equals(PropertyType.PREMIUM_SUITE)) {
            return "Premium Suite";
        }

        if (type.equals(PropertyType.APARTMENT)) {
            return "Apartment";
        }

        return "none";

    }

    public String getStringStatus() {
        if (status.equals(PropertyStatus.UNDER_MAINTENANCE)) {
            return "Under Maintenance";
        }
        if (status.equals(PropertyStatus.RENTED)) {
            return "Rented";
        }
        if (status.equals(PropertyStatus.AVAILABLE)) {
            return "Available";
        }
        return "none";
    }


    public PropertyStatus getStatus() {
        return status;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    //setters:


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void rent(String customerId, DateTime dateTime, int Days) throws StatusInvalidForRentException, SetDateBeforeTodayException, RentForDateExceedNextMaintenanceDateException, RentDaysTooLongException, RentDaysTooShortException, RentDateOrDaysInvalidException {
        if (!this.getStatus().equals(PropertyStatus.AVAILABLE)) {
            throw new StatusInvalidForRentException();
        }
        if (CanRent(dateTime, Days)) {
            String Id1 = customerId.replaceAll("'","");
            String recordId = propertyId + "_" + Id1 + "_" + dateTime.getEightDigitDate();
            rentalRecords.add(0, new RentalRecord(recordId, dateTime, new DateTime(dateTime, Days)));
            this.setStatus(PropertyStatus.RENTED);
        } else {
            throw new RentDateOrDaysInvalidException();
        }
    }

    public void returnProperty(DateTime returnDate) throws StatusInvalidForReturnException {
        if (!this.getStatus().equals(PropertyStatus.RENTED)) {
            throw new StatusInvalidForReturnException();
        }
        try {
            rentalRecords.get(0).finishWithReturnDetail(rentRate, lateRate, returnDate);
        } catch (SetDateBeforeTodayException e) {
            e.popWindow("Error", "Cannot Return Before RentDate");
        }
        this.setStatus(PropertyStatus.AVAILABLE);
    }

    public void performMaintenance() throws StatusInvalidToMaintenanceException {
        if (!this.getStatus().equals(PropertyStatus.AVAILABLE)) {
            throw new StatusInvalidToMaintenanceException();
        }
        this.setStatus(PropertyStatus.UNDER_MAINTENANCE);
    }

    public abstract void completeMaintenance(DateTime completeDate) throws StatusInvalidToCompleteMaintenance, DateInvalidToCompleteMaintenanceException;

    @SuppressWarnings("SameReturnValue")
    protected abstract boolean CanRent(DateTime rentDay, int numOfRentdays) throws RentDaysTooLongException, RentDaysTooShortException, RentForDateExceedNextMaintenanceDateException, SetDateBeforeTodayException;


    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void setRentRate(double rentRate) {
        this.rentRate = rentRate;
    }

    void setLateRate(double lateRate) {
        this.lateRate = lateRate;
    }

    public void setRentalRecords(ArrayList<RentalRecord> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    private void setStreetNum(String streetNum) {
        this.streetNum = String.valueOf(streetNum);
    }

    private void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    private void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    void setType(PropertyType type) {
        this.type = type;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @Override
    public abstract String toString();

    String fillup(String str) {
        String s = str.toUpperCase();
        int length = s.length();

        if (length < 4) {

            String s1 = "0000";
            return s1.substring(0, 4 - length) + s;
        } else {
            s = s.substring(0, 4);
        }
        return s;
    }

}
