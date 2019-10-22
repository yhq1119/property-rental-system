package model;

import exceptions.*;
import utilities.DateTime;
import utilities.PropertyStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    String APARTMENT_IMAGE_PATH ="C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\images\\Apartment\\";
    String SUITE_IMAGE_PATH ="C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\images\\PremiumSuite\\";
    String TEMP_IMAGE_URL = "C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\images\\default.png";

    public FileHandler() {

    }

    private Apartment readApartment(String str)
            throws NumberOfBedroomsException, ReadStatusException {

        String[] k = str.split(":");

        System.out.println("--------------------------reads apartment------------------------");
        for (String s : k) {
            System.out.println(s);////////////////////////////////////////////
        }

        String propertyId = k[0];
        String streetNum = k[1];
        String streetName = k[2];
        String suburb = k[3];
        String numOfRoom = k[5];
        String status = k[6];
        String fileName = k[7];
        String description = k[8];
        int sss = -1;
        File tempo;
        try {
            sss = Integer.parseInt(numOfRoom);
        } catch (Exception e) {
            //noinspection ConstantConditions
            if (sss != 1 || sss != 2 || sss != 3) {
                throw new NumberOfBedroomsException();
            }
        }
        try {
            tempo = new File(APARTMENT_IMAGE_PATH + fileName);
        } catch (Exception e) {
            tempo = new File(TEMP_IMAGE_URL);
        }


        Apartment tempo1 = new Apartment(streetNum, streetName, suburb, sss);
        tempo1.setPropertyId(propertyId);
        tempo1.setImagePath(tempo);
        tempo1.setDescription(description);

        try {
            tempo1.setStatus(readStatus(status));
        } catch (Exception e) {
            throw new ReadStatusException();
        }
        return tempo1;


    }

    private PropertyStatus readStatus(String status) {

        if (status.toUpperCase().equals("Available".toUpperCase())) {
            return PropertyStatus.AVAILABLE;
        }
        if (status.toUpperCase().equals("Rented".toUpperCase())) {
            return PropertyStatus.RENTED;
        }
        if (status.toUpperCase().equals("Under Maintenance".toUpperCase())) {
            return PropertyStatus.UNDER_MAINTENANCE;
        }

        return null;
    }

    private PremiumSuite readPremiumSuite(String str)
            throws ReadLineIsNotPremiumSuiteException, ReadDateException, ReadStatusException {

        String[] k = str.split(":");
        System.out.println("--------------------Reads Premium Suite--------------");
        for (String s : k) {
            System.out.println(s);////////////////////////////////////////////////////////
        }

        String propertyId = k[0];
        String streetNum = k[1];
        String streetName = k[2];
        String suburb = k[3];
        String status = k[6];
        String lastmainteinenceDate = k[7];
        String fileName = k[8];
        String description = k[9];

        File tempo;
        DateTime lastdateTime;
        PremiumSuite tempo1;


        try {
            tempo = new File(SUITE_IMAGE_PATH + fileName);
        } catch (Exception e) {
            tempo = new File(TEMP_IMAGE_URL);
        }

        try {
            lastdateTime = readDateTime(lastmainteinenceDate);
        } catch (Exception e) {
            throw new ReadDateException();
        }

        try {
            tempo1 = new PremiumSuite(streetNum, streetName, suburb, lastdateTime);
        } catch (Exception e) {
            throw new ReadLineIsNotPremiumSuiteException();
        }
        try {
            tempo1.setStatus(readStatus(status));
        } catch (Exception e) {
            throw new ReadStatusException();
        }
        tempo1.setPropertyId(propertyId);
        tempo1.setImagePath(tempo);
        tempo1.setDescription(description);
        return tempo1;


    }

    private RentalRecord readRecord(String str)
            throws ReadRentalRecordException, ReadDateException, ReadRentFeeException {

        String[] k = str.split(":");
        String recordId = k[0];
        String rentDates = k[1];
        String estiDates = k[2];
        String actuDates = k[3];
        String rentFees = k[4];
        String lateFees = k[5];
        DateTime rentDate;
        DateTime estiDate;
        DateTime actuDate;
        double rentFee;
        double lateFee;
        RentalRecord temp;

        try {
            rentDate = readDateTime(rentDates);
            estiDate = readDateTime(estiDates);
            if (actuDates.toUpperCase().equals("none".toUpperCase())) {
                actuDate = null;
            } else {
                actuDate = readDateTime(actuDates);
            }
        } catch (Exception e) {
            throw new ReadDateException();
        }
        try {
            rentFee = Double.parseDouble(rentFees);
            lateFee = Double.parseDouble(lateFees);
        } catch (Exception e) {
            throw new ReadRentFeeException();
        }
        try {
            temp = new RentalRecord(recordId, rentDate, estiDate, actuDate, rentFee, lateFee);
        } catch (Exception e) {
            throw new ReadRentalRecordException();
        }
        return temp;
    }


    private boolean matchPropertyAndRecord(RentalProperty P, RentalRecord R) {
        return R.getRecordId().toUpperCase().contains(P.getPropertyId().toUpperCase());

    }

    private DateTime readDateTime(String str) throws ReadDateException {
        DateTime temp;
        try {
            String[] date = str.split("/");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);

            temp = new DateTime(day, month, year);
        } catch (Exception e) {
            throw new ReadDateException();
        }
        return temp;
    }

    public ArrayList<RentalProperty> readFromFile(File file)
            throws
            FileNotFoundException,
            NumberOfBedroomsException,
            ReadStatusException,
            ReadLineIsNotPremiumSuiteException,
            ReadDateException,
            ReadRentFeeException,
            ReadRentalRecordException {
        Scanner input;
        String temp;

        ArrayList<RentalProperty> properties1 = new ArrayList<>();
        ArrayList<RentalRecord> records1 = new ArrayList<>();
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        while (input.hasNextLine()) {
            temp = input.nextLine();
            if (temp.toUpperCase().contains(":Apartment:".toUpperCase())) {
                properties1.add(readApartment(temp));
            }
            if (temp.toUpperCase().contains("Premium Suite".toUpperCase())) {
                properties1.add(readPremiumSuite(temp));
            }
            if (temp.split(":").length == 6) {
                records1.add(readRecord(temp));
            }
        }
        input.close();

        System.out.println("\n---------------------records size is-" + records1.size() + "-----------------------------\n");

        for (RentalProperty PPP1 : properties1) {
            for (RentalRecord rrr : records1) {
                if (matchPropertyAndRecord(PPP1, rrr)) {
                    PPP1.getRentalRecords().add(rrr);
                }
            }
            System.out.println(PPP1.getRentalRecords().size());
        }
        return properties1;
    }


    private String getPropertyAndRecords(ArrayList<RentalProperty> arrayList) {
        StringBuilder s = new StringBuilder();
        for (RentalProperty R : arrayList) {
            s.append(R.toString()).append("\r\n");
            for (RentalRecord record : R.getRentalRecords()) {
                s.append(record.toString()).append("\r\n");
            }
        }
        return s.toString();
    }

    public void writeToFile(File file, ArrayList<RentalProperty> arrayList)
            throws WriteFileException {
        String s = getPropertyAndRecords(arrayList);
        try {

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.close();

        } catch (Exception e) {
        throw new WriteFileException();
        }
    }

}