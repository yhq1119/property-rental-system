package model;

import exceptions.*;
import utilities.DateTime;
import utilities.PropertyStatus;
import utilities.PropertyType;

public class PremiumSuite extends RentalProperty {

    private DateTime lastMaintenanceDate;
    private DateTime nextMaintenanceDate;

    public PremiumSuite(String streetnum, String streetname, String suburb, DateTime lastMaintenanceDate) {
        super(streetnum, streetname, suburb);

        this.lastMaintenanceDate = lastMaintenanceDate;
        this.nextMaintenanceDate = new DateTime(lastMaintenanceDate, 10);
        super.setType(PropertyType.PREMIUM_SUITE);

        super.setNumOfRooms(3);
        super.setRentRate(554);
        super.setLateRate(662);


        super.setPropertyId("S_" + streetnum + fillup(streetname.replaceAll(" ", "")) + fillup(suburb.replaceAll(" ", "")));


    }


    public DateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    private void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public DateTime getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(DateTime nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    @Override
    public void completeMaintenance(DateTime completeDate) throws DateInvalidToCompleteMaintenanceException, StatusInvalidToCompleteMaintenance {
        if (!getStatus().equals(PropertyStatus.UNDER_MAINTENANCE)){
            System.out.println("Not under maintenance now");
            throw new StatusInvalidToCompleteMaintenance();
        }

        if (DateTime.diffDays(getNextMaintenanceDate(),completeDate)<0){
            System.out.println(getNextMaintenanceDate().getFormattedDate());
            System.out.println(completeDate.getFormattedDate());
            System.out.println(DateTime.diffDays(getNextMaintenanceDate(),completeDate));
            System.out.println("input date is later than nextMaintenance date");
            throw new DateInvalidToCompleteMaintenanceException();
        }
        else {


            setLastMaintenanceDate(completeDate);
            setStatus(PropertyStatus.AVAILABLE);
        }

    }


    @Override
    public boolean CanRent(DateTime rentDay, int numOfRentdays) throws RentDaysTooLongException, RentDaysTooShortException, RentForDateExceedNextMaintenanceDateException, SetDateBeforeTodayException {


        if (DateTime.diffDays(rentDay, new DateTime()) < 0) {
            throw new SetDateBeforeTodayException();
        }
        if (numOfRentdays > 28) {
            throw new RentDaysTooLongException();
        }
        if (numOfRentdays < 1) {
            throw new RentDaysTooShortException();
        }
        if (DateTime.diffDays(nextMaintenanceDate, rentDay) >= numOfRentdays) {
            return true;
        } else {
            throw new RentForDateExceedNextMaintenanceDateException();
        }

    }

    @Override
    public String toString() {
        String divider = ":";
        return getPropertyId() + divider +


                getStreetNum() + divider +
                getStreetName() + divider +
                getSuburb() + divider +
                getStringType() + divider +
                getNumOfRooms() + divider +
                getStringStatus() + divider +
                lastMaintenanceDate.getFormattedDate() + divider +
                getImageNameString() + divider +
                getDescription();


    }


}
