package Flowers;

public class RedRose extends Rose {

    public RedRose(){
        this.name = "red-rose";
        this.price = 700;
        this.quantity = 40;
    }

    @Override
    public String toString() {
        return ("red-rose:\t" +
                "price: " + price + ",\t" +
                "quantity: " + quantity);
    }
}
