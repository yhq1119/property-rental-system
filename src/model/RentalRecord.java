package model;

import exceptions.SetDateBeforeTodayException;
import utilities.DateTime;

public class RentalRecord {


    private String recordId;
    private DateTime rentDate;        //datetime to string
    private DateTime estimatedReturnDate; //datetime to string
    private DateTime actulReturnDate; //datetime to string
    private double rentalFee;       //double to string//remember to transfer type
    private double lateFee;         //doubel to string




    public RentalRecord() {

        setAllAttributesToNone();
    }
    public RentalRecord(String recordId, DateTime rentdate, DateTime estimatedReturnDate){

        setRecordId(recordId);
        setRentDate(rentdate);
        setEstimatedReturnDate(estimatedReturnDate);

    }

    public RentalRecord(String recordId, DateTime rentDate, DateTime estimatedReturnDate, DateTime actulReturnDate, double rentalFee, double lateFee) {

        setRecordId(recordId);
        setRentDate(rentDate);
        setEstimatedReturnDate(estimatedReturnDate);
        setActulReturnDate(actulReturnDate);
        setRentalFee(rentalFee);
        setLateFee(lateFee);
    }

    public void finishWithReturnDetail(double RentRate, double LateRate, DateTime actulReturnDate1) throws SetDateBeforeTodayException {

        setActulReturnDate(actulReturnDate1);
        int days;
        if (DateTime.diffDays(getActulReturnDate(),getRentDate())<0){
                throw new SetDateBeforeTodayException();
        }
        if (DateTime.diffDays(this.estimatedReturnDate, this.actulReturnDate) <= 0) {
            days = DateTime.diffDays(estimatedReturnDate, rentDate);
        } else {

            days = DateTime.diffDays(actulReturnDate, rentDate);
        }
        if (days >0) {
            setRentalFee((RentRate * days));
        } else {
            setRentalFee(0);
        }

        int days1 = DateTime.diffDays(this.actulReturnDate, this.estimatedReturnDate);
        if (days1 >0) {
            setLateFee((LateRate * days1));
        } else {
            setLateFee(0);
        }

    }




    private void setAllAttributesToNone() {
        String s = "none";

        setRecordId(s);

        setRentDate(new DateTime());
        setEstimatedReturnDate(new DateTime());
        //   setActulReturnDate(new DateTime());


    }


    @Override
    public String toString() {
        String divider = ":";
        String a = "";
        try {
            a = getRecordId() + divider +
                    RentDateToString() + divider +
                    EstimatedReturnDateToString() + divider +
                    ActulReturnDateToString() + divider +
                    getRentalFeeString() + divider +
                    getLateFeeString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public String getStringActualDate() {
        if (actulReturnDate == null) {
            return "Not Set";
        } else {
            return actulReturnDate.getFormattedDate();
        }
    }

    public String getStringActualDate2(){
        String s = "Not Set";
        try{
            s = actulReturnDate.getFormattedDate();
        }catch (Exception e){
            System.out.println("Error occurs when trans ActualDate.");
        }

        return s;
    }


    //getters

    public String getRecordId() {
        return recordId;
    }

    private String RentDateToString() {
        try {
            return rentDate.getFormattedDate();
        } catch (Exception e) {
            return "none";
        }
    }

    private String EstimatedReturnDateToString() {
        try {
            return estimatedReturnDate.getFormattedDate();
        } catch (Exception e) {
            return "none";
        }
    }

    private String ActulReturnDateToString() {
        try {
            return actulReturnDate.getFormattedDate();

        } catch (NullPointerException e) {
            return "none";
        }
    }

    public String getDetails() {

        if (this.actulReturnDate==null) {
            return "\nRecord ID:\t\t\t\t" + this.recordId +
                    "\nRent Date:\t\t\t\t" + this.rentDate.getFormattedDate() +
                    "\nEstimated Return Date:\t" + this.estimatedReturnDate.getFormattedDate() +
                    "\nActual Return Date:\t\t\t\t" + this.actulReturnDate.getFormattedDate() +
                    "\nRental Fee:\t\t\t\t" + String.format("%.2f", this.rentalFee) +
                    "\nLate Fee:\t\t\t\t" + String.format("%.2f", this.lateFee);
        }
        else {
            return "\nRecord ID:\t\t\t\t" + this.recordId +
                    "\nRent Date:\t\t\t\t" + this.rentDate.getFormattedDate() +
                    "\nEstimated Return Date:\t" + this.estimatedReturnDate.getFormattedDate() +
                    "\nCurrently In Use\t\t\t\t" +
                    "\nRental Fee:\t\t\t\t" + String.format("%.2f", this.rentalFee) +
                    "\nLate Fee:\t\t\t\t" + String.format("%.2f", this.lateFee);
        }

    }


    public DateTime getRentDate() {
        return rentDate;
    }
    public DateTime getEstimatedReturnDate() {
        return estimatedReturnDate;
    }
    public DateTime getActulReturnDate() {
        return actulReturnDate;
    }


    public String getRentalFeeString() {
        try{return String.format("%.2f", rentalFee);}
        catch (Exception e){
            return "none";
        }
    }

    public double getRentalFee() { return rentalFee; }
    public double getLateFee() { return lateFee; }
    public String getLateFeeString() {

        try {
            return String.format("%.2f", lateFee);
        }catch (Exception e){
            return "none";
        }
    }


    //setters
    public void setRentDate(DateTime rentDate) {
        this.rentDate = rentDate;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setEstimatedReturnDate(DateTime estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    public void setActulReturnDate(DateTime actulReturnDate) {
        this.actulReturnDate = actulReturnDate;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }
}
