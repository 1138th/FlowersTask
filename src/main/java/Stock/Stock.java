package Stock;

import Exceptions.FlowersNegativeIntegerException;
import Flowers.*;
import Utilities.FillValue;
import Utilities.Usable;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Stock implements Usable {

    private static Logger logger = LogManager.getRootLogger();

    public Map<Integer, Flower> create(){
        Flower violet = new Flowers().violet();
        Flower peony = new Flowers().peony();
        Flower redRose = new Flowers().redRose();
        Flower blueRose = new Flowers().blueRose();
        Map<Integer, Flower> stock = new HashMap<Integer, Flower>();
        stock.put(0, violet);
        stock.put(1, peony);
        stock.put(2, redRose);
        stock.put(3, blueRose);
        return  stock;
    }

    public static boolean canEdit(String[] editingParameters) throws FlowersNegativeIntegerException{
        int isInteger;
        if ((editingParameters.length % 3) != 0) {
            logger.error("number of parameters must be multiple of three");
            return false;
        }
        for (int i = 0; i < editingParameters.length; i += 3){

            //------------------------- CHECKING 1ST PARAMETER FOR FLOWER-NAME EQUALITY -------------------------

            if (!(editingParameters[i].equals("violet") || editingParameters[i].equals("peony") ||
                    editingParameters[i].equals("red-rose") || editingParameters[i].equals("blue-rose")))  {
                logger.error("check stock to watch how to write flower name correctly");
                return false;
            }

            //------------------------- CHECKING 2ND PARAMETER FOR -------------------------
            //------------------------- CORRECTNESS (POSITIVE INTEGER) -------------------------

            try {
                isInteger = Integer.parseInt(editingParameters[i + 1]);
            } catch (NumberFormatException e) {
                logger.error("flowers' price is not integer");
                return false;
            }
            if (isInteger < 0) {
                throw new FlowersNegativeIntegerException();
            }

            //------------------------- CHECKING 3RD PARAMETER FOR -------------------------
            //------------------------- CORRECTNESS (POSITIVE INTEGER) -------------------------

            try {
                isInteger = Integer.parseInt(editingParameters[i + 2]);
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
                    logger.debug("item in stock has been edited: " + item.getValue());
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
        for (Map.Entry<Integer, Flower> item: stock.entrySet()) {
            addingQuantity = FillValue.fillQuantity();
            stock.get(item.getKey()).setQuantity(item.getValue().getQuantity() + addingQuantity);
            logger.info(item.getValue().getQuantity() + " item(s) \"" + item.getValue().getName() +
                    "\" has been added to stock");
        }
    }

    public void clear(Map<Integer, Flower> stock){
        stock.clear();
        logger.info("stock map has been cleared");
    }
}
