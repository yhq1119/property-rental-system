package utilities;

import exceptions.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Apartment;
import model.PremiumSuite;
import model.RentalProperty;


import java.io.File;
import java.util.ArrayList;

public class RandomPropertyGenerator {


    private String[] streetnames = {"Bourke Street", "Collins Street", "Elizabeth Street", "Flinders Street", "King Street", "Queen Street", "Russell Street", "Spencer Street", "Spring Street", "Stephen Street", "Swanston Street", "William Street", "Lonsdale Street", "La Trobe Street"};
    private String[] suburbs = {"Melbourne", "Carlton", "South Yarra", "Fitzroy", "Prahran-Windsor", "St Kilda", "Collingwood", "St Kilda", "North Melbourne", "Flemington", "Southbank"};


    private RandomWords randomWords = new RandomWords();


    public Apartment generatedRandomApartment() throws NumberOfBedroomsException {

        String[] a = randomWords.RandomWordsGenerator(2);

        Apartment apartment = new Apartment(String.valueOf(randomWords.randomInt1toX(100)), streetnames[randomWords.randomInt1toX(streetnames.length - 1)], suburbs[randomWords.randomInt1toX(suburbs.length - 1)], randomWords.randomInt1to3());

//        for (int i = 0; i < (randomWords.randomInt1to3())*3; i++) {
//            apartment.rent(randomWords.RandomWordsGenerator(1)[0], new DateTime(randomWords.randomInt1toX(28), randomWords.randomInt1toX(11), 2018),randomWords.randomInt1toX(6)*3);
//        }

//if (randomBooleanAt50Percent()){
//
//}






        apartment.setImagePath(randomImageFile());
        return apartment;
    }

    public PremiumSuite generateRandomSuite() {

        String[] a = randomWords.RandomWordsGenerator(3);

        PremiumSuite premiumSuite = new PremiumSuite(String.valueOf(randomWords.randomInt1toX(100)), streetnames[randomWords.randomInt1toX(streetnames.length - 1)], suburbs[randomWords.randomInt1toX(suburbs.length - 1)], new DateTime(randomWords.randomInt1toX(28), randomWords.randomInt1toX(12), 2018));

//        for (int i = 0; i < (randomWords.randomInt1to3())*3; i++) {
//            premiumSuite.rent( randomWords.RandomWordsGenerator(3)[2], new DateTime(randomWords.randomInt1toX(28), randomWords.randomInt1toX(12), 2018),randomWords.randomInt1toX(8));
//        }
        premiumSuite.setImagePath(randomImageFile1());
        return premiumSuite;

    }

    public ArrayList<RentalProperty> generatePropertiesList() throws NumberOfBedroomsException {


        ArrayList<RentalProperty> arrayList1 = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            arrayList1.add(generatedRandomApartment());
        }

        for (int j = 0; j < 5; j++) {
            arrayList1.add(generateRandomSuite());
        }

        return arrayList1;

    }

    public ImageView randomImageView() {

        String[] a = {"a01.jpg", "a02.jpg", "a03.jpg", "a04.jpg", "a05.jpg", "a06.jpg", "a07.jpg", "a08.jpg", "a09.jpg", "a10.jpg"};
        return new ImageView(new Image("/images/Apartment/" + a[randomWords.randomInt1toX(9)]));
    }

    private File randomImageFile() {
        String[] a = {"a01.jpg", "a01.jpg", "a02.jpg", "a03.jpg", "a04.jpg", "a05.jpg", "a06.jpg", "a07.jpg", "a08.jpg", "a09.jpg", "a10.jpg"};
        return new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\pics\\Apartment\\" + a[randomWords.randomInt1toX(10)]);


    }

    private File randomImageFile1() {
        String[] a = {"s01.jpg", "s01.jpg", "s02.jpg", "s03.jpg", "s04.jpg", "s05.jpg"};
        return new File("C:\\Users\\Nathan\\IdeaProjects\\Rental_demo2\\src\\pics\\PremiumSuite\\" + a[randomWords.randomInt1toX(5)]);


    }
    private boolean randomBooleanAt50Percent(){
        return Math.random()>0.5;

    }


}
