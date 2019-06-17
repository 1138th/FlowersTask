package Bouquet;

import Flowers.*;
import Stock.Stock;
import Utilities.Usable;

import java.util.Map;

public class Bouquet implements Usable {

    public Map<Integer, Flower> create(){
        Map<Integer, Flower> bouquet = new Stock().create();
        for (Map.Entry<Integer, Flower> item: bouquet.entrySet()) {
            bouquet.get(item.getKey()).setQuantity(0);
        }
        return  bouquet;
    }

    public static boolean canCreate(String[] bouquetList){
        int isInteger;
        if ((bouquetList.length % 2) != 0) return false;
        for (int i = 0; i < bouquetList.length; i += 2){
            if (!(bouquetList[i].equals("violet") || bouquetList[i].equals("peony") ||
                    bouquetList[i].equals("red-rose") || bouquetList[i].equals("blue-rose")))  return false;
            try {
                isInteger = Integer.parseInt(bouquetList[i + 1]);
                if (isInteger < 0) return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public static Map<Integer, Flower> fill(Map<Integer, Flower> stock, Map<Integer, Flower> bouquet, String[] bouquetList) {
        Map<Integer, Flower> localBouquet = new Stock().create();
        for (Map.Entry<Integer, Flower> item: localBouquet.entrySet()) {
            localBouquet.get(item.getKey()).setQuantity(bouquet.get(item.getKey()).getQuantity());
        }
        for (int i = 0; i < bouquetList.length; i += 2){
            for (Map.Entry<Integer, Flower> item: localBouquet.entrySet()) {
                if (bouquetList[i].equals(item.getValue().getName())){
                    localBouquet.get(item.getKey()).setQuantity(localBouquet.get(item.getKey()).getQuantity() +
                            Integer.parseInt(bouquetList[i + 1]));
                    stock.get(item.getKey()).setQuantity(stock.get(item.getKey()).getQuantity() - Integer.parseInt(bouquetList[i + 1]));
                }
            }
        }
        return localBouquet;
    }

    public void print(Map<Integer, Flower> stock){
        System.out.println("Your current bouquet:");
        for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
            System.out.println("\t" + item.getValue().getName() + ": " + item.getValue().getQuantity());
        }
    }

    public static int sell(Map<Integer, Flower> bouquet){
        int cost = 0;
        for (Map.Entry<Integer, Flower> item: bouquet.entrySet()) {
            cost += item.getValue().getPrice() * item.getValue().getQuantity();
        }
        return cost;
    }

    public void clear(Map<Integer, Flower> bouquet){
        try {
            bouquet.clear();
        } catch (Exception e){
            System.out.println("You had none bouquets.");
        }
    }
}
