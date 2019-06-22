package Bouquet;

import Exceptions.FlowersNegativeIntegerException;
import Flowers.*;
import Stock.Stock;
import Utilities.Usable;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bouquet implements Usable {

    private static Logger logger = LogManager.getRootLogger();

    public Map<Integer, Flower> create(Map<Integer, Flower> stock){
        Map<Integer, Flower> bouquet = new Stock().create();
        for (Map.Entry<Integer, Flower> item: bouquet.entrySet()) {
            bouquet.get(item.getKey()).setPrice(stock.get(item.getKey()).getPrice());
            bouquet.get(item.getKey()).setQuantity(0);
        }
        logger.info("new bouquet creating initiated");
        return  bouquet;
    }

    public static boolean canFill(String[] bouquetList){
        int isInteger;
        if ((bouquetList.length % 2) != 0) return false;
        for (int i = 0; i < bouquetList.length; i += 2){
            if (!(bouquetList[i].equals("violet") || bouquetList[i].equals("peony") ||
                    bouquetList[i].equals("red-rose") || bouquetList[i].equals("blue-rose")))  {
                logger.error("check stock to watch how to write flower name correctly");
                return false;
            }
            try {
                isInteger = Integer.parseInt(bouquetList[i + 1]);
            } catch (NumberFormatException e){
                logger.error("flowers' quantity is not integer");
                return false;
            }
            if (isInteger < 0) {
                throw new FlowersNegativeIntegerException();
            }
        }
        return true;
    }

    public static Map<Integer, Flower> fill(Map<Integer, Flower> stock, Map<Integer, Flower> bouquet, String[] bouquetList) {
        Map<Integer, Flower> localBouquet = new Stock().create();
        localBouquet.putAll(bouquet);
        for (int i = 0; i < bouquetList.length; i += 2){
            for (Map.Entry<Integer, Flower> item: localBouquet.entrySet()) {
                if (bouquetList[i].equals(item.getValue().getName())){
                    localBouquet.get(item.getKey()).setQuantity(localBouquet.get(item.getKey()).getQuantity() +
                            Integer.parseInt(bouquetList[i + 1]));
                    stock.get(item.getKey()).setQuantity(stock.get(item.getKey()).getQuantity() - Integer.parseInt(bouquetList[i + 1]));
                    logger.info(Integer.parseInt(bouquetList[i + 1]) + " item(s) \"" + item.getValue().getName() +
                            "\" has been added to bouquet");
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
        if (bouquet.isEmpty()) {
            throw new NullPointerException();
        } else {
            bouquet.clear();
            logger.info("bouquet map has been cleared");
        }

    }
}
