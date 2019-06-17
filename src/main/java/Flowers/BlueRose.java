package Flowers;

public class BlueRose extends Rose {

    public BlueRose(){
        this.name = "blue-rose";
        this.price = 800;
        this.quantity = 50;
    }

    @Override
    public String toString() {
        return ("blue-rose:\t" +
                "price: " + price + ",\t" +
                "quantity: " + quantity);
    }
}
