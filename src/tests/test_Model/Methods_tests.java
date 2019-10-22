package tests.test_Model;

import exceptions.*;
import model.*;
import org.junit.jupiter.api.Test;
import utilities.DateTime;
import utilities.PropertyType;
import utilities.RandomPropertyGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@SuppressWarnings({"CatchMayIgnoreException", "StatementWithEmptyBody", "unchecked", "CanBeFinal"})
class Methods_tests {


    private FlexiRentalSystem flexiRentalSystem = new FlexiRentalSystem();
    private RandomPropertyGenerator randomPropertyGenerator = new RandomPropertyGenerator();

    private PremiumSuite s1 = new PremiumSuite("123", "Bourke Street", "Melbourne", new DateTime(11, 11, 2018));
    private PremiumSuite s2 = new PremiumSuite("123", "shoot Street", "cbd", new DateTime(11, 11, 2018));
    private PremiumSuite s3 = new PremiumSuite("1515", "Bourke Street", "Melbourne", new DateTime(11, 11, 2018));


    private Apartment a1 = new Apartment("234", "yojo", "sakura", 2);
    private Apartment a2 = new Apartment("32", "good street", "yess sub", 2);
    private Apartment a3 = new Apartment("1231", "great street", "yeah sub", 2);

    Methods_tests() throws NumberOfBedroomsException {
    }


    @Test
    void test01() {


        System.out.println(s1.getPropertyId());
        System.out.println(s2.getPropertyId());
        System.out.println(s3.getPropertyId());

        System.out.println(a1.getPropertyId());
        System.out.println(a2.getPropertyId());
        System.out.println(a3.getPropertyId());

    }


    @Test
    void test02() {
        File file = new File("data1.txt");


        try {


            FileWriter fileWriter = new FileWriter(file);


            fileWriter.close();


        } catch (Exception e) {

            e.printStackTrace();
        }


        try {


            FileReader fileReader = new FileReader(file);

            Scanner scanner = new Scanner(file);
            if (scanner.hasNext()) {


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    void test03() throws InsertDBException{


        try {
            flexiRentalSystem.setProperties(randomPropertyGenerator.generatePropertiesList());
        } catch (NumberOfBedroomsException e) {
            e.popWindow("error", "");
        }
        flexiRentalSystem.printAllPropertyAndRecords();
        System.out.println("--------------------------------start to insert into tables--------------------------------");

        Database database = new Database();

        database.deletePropertyAndRecordTables();
        database.createPropertyAndRecordTables();

        database.insertPropertyAndRecordsIntoTable(flexiRentalSystem);

        System.out.println("---------------------------------------finish insertion-------------------------------------");

        flexiRentalSystem.getProperties().clear();

        System.out.println(flexiRentalSystem.getProperties().size());
        database.readFromPropertyAndRecordTable(flexiRentalSystem);

        System.out.println("------------------------------clear system and read from database---------------------------");

        System.out.println(flexiRentalSystem.getProperties().size());

        System.out.println("-------------------------------------end test-----------------------------------------------");


        flexiRentalSystem.printAllPropertyAndRecords();

    }

    @Test
    void test04() {

        String s = ":213:ghgfftd:tytfyfyf:rdddrtdtr:";
        System.out.println(s.split(":").length);
        for (String k : s.split(":")) {
            System.out.println(k);

        }


    }

    @Test
    void test07() {

        PremiumSuite premiumSuite = new PremiumSuite("300", "Haha", "sub", new DateTime(11, 11, 2018));
        System.out.println(premiumSuite.toString());
        System.out.println(premiumSuite.getNextMaintenanceDate().getFormattedDate());
        System.out.println(DateTime.diffDays(premiumSuite.getNextMaintenanceDate(), new DateTime(22, 11, 2018)));
    }

    @Test
    void test05() {


        ArrayList<String> arrayList1 = new ArrayList<>();

        String s1 = "this is the first one";
        String s2 = "this is the second one";
        String s3 = "this is the third one";

        arrayList1.add(0, s1);

        for (String kk : arrayList1) {
            System.out.println();
        }

        arrayList1.add(0, s2);
        arrayList1.add(0, s3);

        for (String kkk : arrayList1) {
            System.out.println(kkk);
        }
    }


    @Test
    void test06() {

        double k = -1;
        System.out.println(k);

    }


    @Test
    void test09() {
        String s = "Hey:this,is:just,an example";
        for (String substring : s.split("( |,|:)")) {
            System.out.println(substring);
        }
        printStringArray(s.split(" "));

    }

    @Test
    void test11() {

        ArrayList s = new ArrayList();

        s.add("First Element");
        s.add("Second Element");
        s.add("Third Element");

        System.out.println("The set contains:");

        //return the iterator.
        Iterator i = s.iterator();

        while (i.hasNext())
            System.out.println(i.next());

        //remove the current one - the last one!
        i.remove();

        System.out.println();
        System.out.println("The set now contains:");

        i = s.iterator();
        while (i.hasNext())
            System.out.println(i.next());

        System.out.println("-End-");

    }

    @Test
    void test12() throws NumberOfBedroomsException {
        flexiRentalSystem.setProperties(randomPropertyGenerator.generatePropertiesList());

        ArrayList<RentalProperty> temp1 = (ArrayList<RentalProperty>) flexiRentalSystem.getProperties().clone();
        ArrayList<RentalProperty> temp = (ArrayList<RentalProperty>) temp1.clone();
        ArrayList<RentalProperty> temp3 = new ArrayList<>();
        System.out.println(temp.size());


        for (RentalProperty Pp : temp) {
            if (!Pp.getType().equals(PropertyType.APARTMENT)) {
                Pp.setSelected(false);
            }

            if (!Pp.getSuburb().contains("St Kild")) {
                Pp.setSelected(false);
            }
        }
        System.out.println("------------------------");

        for (RentalProperty P : temp) {
            if (P.isSelected()) {
                System.out.println(P.toString());
                temp3.add(P);
            }
        }
        System.out.println(temp3.size());

        System.out.println("------------------------");
        for (int j = 0; j < flexiRentalSystem.getProperties().size(); j++) {
            System.out.println(flexiRentalSystem.getProperties().get(j).toString());
        }

        for (RentalProperty ppp : flexiRentalSystem.getProperties()) {
            if (!ppp.isSelected()) {
                System.out.println("selected changed to false");
            }
        }
        for (RentalProperty pppp : flexiRentalSystem.getProperties()) {
            pppp.setSelected(true);
        }
        for (RentalProperty ppp : flexiRentalSystem.getProperties()) {
            if (ppp.isSelected()) {
                System.out.println("selected changed to true");
            }
        }


        for (RentalProperty Ps : flexiRentalSystem.getProperties()) {
            System.out.println(Ps.toString());
            for (RentalRecord RR : Ps.getRentalRecords()) {
                System.out.println(RR.toString());
            }
        }
    }
    @Test
    void test13(){
        String s = "/images/default.png";
        File f = new File("/images/default.png");
        System.out.println(f.getPath());
        System.out.println(f.getName());

    }


    @Test
    void test10() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream("myObject"));

            MyClass first = new MyClass(1, 2);
            MyClass second = new MyClass(3, 4);

            output.writeObject(first);
            output.writeObject(second);

            output.close();
            System.out.println("Objects sent to file.\n");
        } catch (IOException e) {
            System.out.println("Error in file handling");
        }

        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("myObject"));

            MyClass first = (MyClass) input.readObject();
            MyClass second = (MyClass) input.readObject();

            System.out.println("Reading the objects:");

            System.out.println(first.getOne() + " and " + first.getTwo());
            System.out.println(second.getOne() + " and " + second.getTwo());

            input.close();
        } catch (ClassNotFoundException | IOException e1) {

        }
    }


    void printStringArrayList(ArrayList<String> a) {

        for (String s : a) {
            System.out.println(s);
        }
    }

    private void printStringArray(String[] a) {
        for (String s : a) {
            System.out.println(s);
        }
    }


}
