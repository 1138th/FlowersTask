package Stock;

import Flowers.*;
import Utilities.FillValue;
import Utilities.Usable;

import java.util.HashMap;
import java.util.Map;

public class Stock implements Usable {

    public Map<Integer, Flower> create(){
        Flower violet = new Violet();
        Flower peony = new Peony();
        Flower redRose = new RedRose();
        Flower blueRose = new BlueRose();
        Map<Integer, Flower> stock = new HashMap<Integer, Flower>();
        stock.put(0, violet);
        stock.put(1, peony);
        stock.put(2, redRose);
        stock.put(3, blueRose);
        return  stock;
    }

    public static boolean canEdit(String[] editingParameters){
        int isInteger;
        if ((editingParameters.length % 3) != 0) return false;
        for (int i = 0; i < editingParameters.length; i += 3){
            if (!(editingParameters[i].equals("violet") || editingParameters[i].equals("peony") ||
                    editingParameters[i].equals("red-rose") || editingParameters[i].equals("blue-rose")))  return false;
            try {
                isInteger = Integer.parseInt(editingParameters[i + 1]);
                if (isInteger < 0) return false;
            } catch (NumberFormatException e){
                return false;
            }
            try {
                isInteger = Integer.parseInt(editingParameters[i + 2]);
                if (isInteger < 0) return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Map<Integer, Flower> stock){
        for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
            if (item.getValue().getQuantity() == 0) return true;
        }
        return false;
    }

    public static void edit(Map<Integer, Flower> stock, String[] editingParameters){
        for (int i = 0; i < editingParameters.length; i += 3){
            for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
                if (editingParameters[i].equals(item.getValue().getName())){
                    stock.get(item.getKey()).setPrice(Integer.parseInt(editingParameters[i + 1]));
                    stock.get(item.getKey()).setQuantity(Integer.parseInt(editingParameters[i + 2]));
                    System.out.println(item.getValue());
                }
            }
        }
    }

    public void print(Map<Integer, Flower> stock){
        for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
            System.out.println("\t" + item.getValue());
        }
        System.out.println();
    }

    public static void update(Map<Integer, Flower> stock){
        int addingQuantity;
        System.out.println("added:");
        for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
            addingQuantity = FillValue.fillQuantity();
            stock.get(item.getKey()).setQuantity(item.getValue().getQuantity() + addingQuantity);
            System.out.println("\t" + item.getValue().getName() + ": " + addingQuantity);
        }
    }

    public void clear(Map<Integer, Flower> stock){
        stock.clear();
    }
}
