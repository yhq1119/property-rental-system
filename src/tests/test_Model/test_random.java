package tests.test_Model;

import org.junit.jupiter.api.Test;
import utilities.RandomWords;

class test_random {

    @Test
    void test1(){

        RandomWords rw = new RandomWords();


        for (int i =0;i<50;i++){

            System.out.println(rw.randomInt1toX(3));

        }


    }
}
