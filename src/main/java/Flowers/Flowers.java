package Flowers;

public class Flowers extends Flower{

    public Flower violet(){
        Flower violet = new Flowers();
        violet.setName("violet");
        violet.setQuantity(20);
        violet.setPrice(500);
        return violet;
    }

    public Flower peony(){
        Flower peony = new Flowers();
        peony.setName("peony");
        peony.setQuantity(30);
        peony.setPrice(600);
        return peony;
    }

    public Flower redRose(){
        Flower redRose = new Flowers();
        redRose.setName("red-rose");
        redRose.setQuantity(40);
        redRose.setPrice(700);
        return redRose;
    }

    public Flower blueRose(){
        Flower blueRose = new Flowers();
        blueRose.setName("blue-rose");
        blueRose.setQuantity(50);
        blueRose.setPrice(800);
        return blueRose;
    }

    @Override
    public String toString() {
        return (this.getName() + "\t" +
                "price: " + this.getPrice() +
                "quantity" + this.getQuantity());
    }
}
