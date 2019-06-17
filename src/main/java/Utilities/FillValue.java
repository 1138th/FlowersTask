package Utilities;

import java.util.Random;

public class FillValue {

    public static int fillQuantity(){
        Random rand = new Random();
        return (rand.nextInt(20) + 10); //10-30
    }

    public static int fillPrice(){
        Random rand = new Random();
        return (rand.nextInt(400) + 500); //500-900
    }
}
