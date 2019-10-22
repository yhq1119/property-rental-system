package utilities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.RentalRecord;

public class Records {
    private final SimpleStringProperty customerId;
    private final SimpleStringProperty rentDate;
    private final SimpleStringProperty estiReturnDate;
    private final SimpleStringProperty acutReturnDate;
    private final SimpleStringProperty rentFee;
    private final SimpleStringProperty lateFee;
    private final SimpleIntegerProperty index;

    public Records(RentalRecord R, int i) {
        this.index = new SimpleIntegerProperty(i);
        this.customerId = new SimpleStringProperty(R.getRecordId().split("_")[2]);
        this.rentDate = new SimpleStringProperty(R.getRentDate().getFormattedDate());
        this.estiReturnDate = new SimpleStringProperty(R.getEstimatedReturnDate().getFormattedDate());
        this.acutReturnDate = new SimpleStringProperty(R.getStringActualDate2());
        this.rentFee = new SimpleStringProperty(R.getRentalFeeString());
        this.lateFee = new SimpleStringProperty(R.getLateFeeString());

    }

    public String getCustomerId() {
        return customerId.get();
    }

    public SimpleStringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getRentDate() {
        return rentDate.get();
    }

    public SimpleStringProperty rentDateProperty() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate.set(rentDate);
    }

    public String getEstiReturnDate() {
        return estiReturnDate.get();
    }

    public SimpleStringProperty estiReturnDateProperty() {
        return estiReturnDate;
    }

    public void setEstiReturnDate(String estiReturnDate) {
        this.estiReturnDate.set(estiReturnDate);
    }

    public String getAcutReturnDate() {
        return acutReturnDate.get();
    }

    public SimpleStringProperty acutReturnDateProperty() {
        return acutReturnDate;
    }

    public void setAcutReturnDate(String acutReturnDate) {
        this.acutReturnDate.set(acutReturnDate);
    }

    public String getRentFee() {
        return rentFee.get();
    }

    public SimpleStringProperty rentFeeProperty() {
        return rentFee;
    }

    public void setRentFee(String rentFee) {
        this.rentFee.set(rentFee);
    }

    public String getLateFee() {
        return lateFee.get();
    }

    public SimpleStringProperty lateFeeProperty() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee.set(lateFee);
    }

    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }
}
