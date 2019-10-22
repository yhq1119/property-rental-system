package tests.test_Model;

import exceptions.*;
import model.RentalProperty;
import model.RentalRecord;
import org.junit.jupiter.api.Test;
import utilities.RandomWords;
import utilities.RandomPropertyGenerator;

import java.util.ArrayList;

@SuppressWarnings({"StringConcatenationInLoop", "ConstantConditions", "MismatchedQueryAndUpdateOfCollection", "CanBeFinal"})
class TestOutput {

    private utilities.RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();
    RandomWords randomWords = new RandomWords();

    @Test
    void test3() throws NumberOfBedroomsException {


        ArrayList<RentalProperty> arrayList1 = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            arrayList1.add(randomPropertyGenerator.generatedRandomApartment());
        }

        for (int j = 0; j < 5; j++) {
            arrayList1.add(randomPropertyGenerator.generateRandomSuite());
        }


        System.out.println(getPropertyAndRecords(arrayList1));


    }

    private String getPropertyAndRecords(ArrayList<RentalProperty> arrayList) {
        String s = "";
        for (RentalProperty R : arrayList) {
            s = s + R.toString() + "\n";
            for (RentalRecord record : R.getRentalRecords()) {
                s = s + record.toString() + "\n";
            }
        }
        return s;
    }


    @Test
    void test1() throws NumberOfBedroomsException {

        int i = 10;
        RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();

        ArrayList<RentalProperty> rentalProperties1 = new ArrayList<>();

        while (i > 0) {

            rentalProperties1.add(randomPropertyGenerator.generatedRandomApartment());
            rentalProperties1.add(randomPropertyGenerator.generateRandomSuite());
            i--;
        }


        String ss = "";

        for (RentalProperty P : rentalProperties1) {
            ss = ss + P.toString() + "\n";
        }

        System.out.println(ss);

        ArrayList<RentalRecord> rentalRecords = new ArrayList<>();

        RentalRecord r1 = new RentalRecord();
        RentalRecord r2 = new RentalRecord();
        RentalRecord r3 = new RentalRecord();


        rentalRecords.add(0, r1);
        rentalRecords.add(0, r2);
        rentalRecords.add(0, r3);
        ArrayList<RentalProperty> rentalProperties2 = new ArrayList<>();


        String s = "";
        for (RentalProperty R : rentalProperties2) {
            s = s + R.toString() + "\n";
            for (RentalRecord record : R.getRentalRecords()) {
                s = s + record.toString() + "\n";
            }
        }

        System.out.println(s);

    }
}
