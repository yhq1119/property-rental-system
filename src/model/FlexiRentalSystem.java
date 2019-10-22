package model;

import exceptions.NumberOfBedroomsException;
import exceptions.SamePropertyHasExistedException;
import exceptions.StatusInvalidForReturnException;
import exceptions.StatusInvalidToMaintenanceException;
import utilities.DateTime;
import utilities.PropertyStatus;

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings("CanBeFinal")
public class FlexiRentalSystem {


    private ArrayList<RentalProperty> properties = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();

    public void addPropertyApartment(String streetnum, String streetname, String suburb, int numOfRoom, File imageFile) throws NumberOfBedroomsException, SamePropertyHasExistedException {

        Apartment apartment = new Apartment(streetnum, streetname, suburb, numOfRoom);
        if (SamePropertyIdExists(apartment)){

            throw new SamePropertyHasExistedException();

        }

        apartment.setImagePath(imageFile);

        properties.add(apartment);

    }

    public void addPropertySuite(String streetnum, String streetname, String suburb, DateTime lastMday, File imageFile) throws SamePropertyHasExistedException {

        PremiumSuite premiumSuite = new PremiumSuite(streetnum, streetname, suburb, lastMday);

        if (SamePropertyIdExists(premiumSuite)){

            throw new SamePropertyHasExistedException();

        }
        premiumSuite.setImagePath(imageFile);
        properties.add(premiumSuite);
    }
    private boolean SamePropertyIdExists(RentalProperty Property){

        for (RentalProperty P:properties){
            if (P.getPropertyId().toUpperCase().equals(Property.getPropertyId().toUpperCase())){
                return true;
            }
        }
        return false;
    }


    public void resetAllProperySelected() {

        for (RentalProperty P : properties) {
            P.setSelected(true);
        }
    }


    public void printAllPropertyAndRecords() {

        for (RentalProperty P : properties) {
            System.out.println(P.toString());
            for (RentalRecord R : P.getRentalRecords()) {
                System.out.println(R.toString());
            }
        }

    }


    private int getPropertyIndexById(String propertyId) {

        int k = -1;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyId().equals(propertyId)) {
                k = i;
                break;
            }
        }
        return k;
    }





    public void returnProperty(String propertyId, DateTime returnDate) throws StatusInvalidForReturnException {
        int k = getPropertyIndexById(propertyId);
        if (k != -1) {
            properties.get(k).returnProperty(returnDate);
            properties.get(k).setStatus(PropertyStatus.AVAILABLE);
        }

    }

    public RentalProperty getPropertyByID(String propertyId) {
        for (RentalProperty p : properties) {
            if (p.getPropertyId().equals(propertyId)) {
                return p;
            }
        }
        return null;
    }

    public void performMaintenance(RentalProperty P) throws StatusInvalidToMaintenanceException {
        if (P.getStatus().equals(PropertyStatus.AVAILABLE)) {
            P.setStatus(PropertyStatus.UNDER_MAINTENANCE);
        }
        else {
            throw new StatusInvalidToMaintenanceException();
        }
    }

    public ArrayList<RentalProperty> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<RentalProperty> properties) {
        this.properties = properties;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

}
