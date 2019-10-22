package model;

import exceptions.*;
import utilities.DateTime;
import utilities.PropertyStatus;
import utilities.PropertyType;

public class Apartment extends RentalProperty {

    public Apartment(
            String streetnum,
            String streetname,
            String suburb,
            int numOfRooms)
            throws NumberOfBedroomsException {

        super(streetnum, streetname, suburb);
        super.setType(PropertyType.APARTMENT);
        switch (numOfRooms) {
            case 1:
                super.setRentRate(143);

                break;
            case 2:
                super.setRentRate(210);
                break;

            case 3:
                super.setRentRate(319);
                break;
            default:
                throw new NumberOfBedroomsException();
        }
        super.setLateRate((super.getRentRate() * 115 / 100));

        super.setNumOfRooms(numOfRooms);

        super.setPropertyId(
                "A_" + streetnum + fillup(streetname.replaceAll(" ", "")) + fillup(suburb.replaceAll(" ", "")));
    }


    @Override
    public void completeMaintenance(DateTime completeDate)
            throws StatusInvalidToCompleteMaintenance, DateInvalidToCompleteMaintenanceException {
        if (!getStatus().equals(PropertyStatus.UNDER_MAINTENANCE)) {
            throw new StatusInvalidToCompleteMaintenance();
        } else {
            System.out.println(DateTime.diffDays(completeDate,new DateTime()));
            System.out.println("complete date"+completeDate.getFormattedDate());
            System.out.println("today "+new DateTime().getFormattedDate());
            if (DateTime.diffDays(completeDate,new DateTime())>=-1){
                super.setStatus(PropertyStatus.AVAILABLE);

            }else {
                throw new DateInvalidToCompleteMaintenanceException();
            }
        }
    }

    @Override
    public boolean CanRent(DateTime rentDay, int numOfRentdays)
            throws RentDaysTooLongException, RentDaysTooShortException, SetDateBeforeTodayException {

        System.out.println(new DateTime().getFormattedDate());
        System.out.println(rentDay.getFormattedDate());

        System.out.println(DateTime.diffDays(rentDay,new DateTime()));
        if (DateTime.diffDays(rentDay, new DateTime()) < -1) {
            throw new SetDateBeforeTodayException();
        } else {
            if (numOfRentdays > 28) {
                throw new RentDaysTooLongException();
            } else {
                System.out.println("shorter than 28 days");
                if (rentDay.getWEEK_OF_DAY() >= 1 && rentDay.getWEEK_OF_DAY() <= 5) {
                    System.out.println("It is Monday to Friday");
                    if (numOfRentdays >= 2) {
                        System.out.println(">= 2 days");

                        System.out.println("true");
                        return true;
                    } else {
                        throw new RentDaysTooShortException();
                    }
                } else {
                    System.out.println("Not monday,tuesday,wednesday,thursday, friday");
                    if (numOfRentdays >= 3) {
                        System.out.println("true");
                        return  true;
                    } else {
                        throw new RentDaysTooShortException();
                    }
                }
            }
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
                getImageName() + divider +
                getDescription();

    }


}