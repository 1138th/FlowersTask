import Bouquet.Bouquet;
import Exceptions.FlowersNegativeIntegerException;
import Flowers.*;
import Stock.Stock;

import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlowersTask {

    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args){
        //------------------------- INITIALIZE DATA BLOCK -------------------------

        Stock stock = new Stock();
        Bouquet bouquet = new Bouquet();
        Map<Integer, Flower> flowerStock = stock.create();
        Map<Integer, Flower> bouquetStock = null;
        Scanner scan = new Scanner(System.in);
        String input;
        String[] array;
        System.setProperty("log4j.configurationFile", "src\\main\\resources\\log4j2.xml");
        logger.info("logger has been created. Default path to log-file: src\\main\\Log.log");
        logger.info("flowers' stock has been created");
        //------------------------- PRINTING STOCK BLOCK -------------------------

        System.out.println("Welcome to flower shop.\nYour stock:");
        stock.print(flowerStock);

        //------------------------- EDITING STOCK BLOCK -------------------------

        System.out.println("Prices and quantities are generated fixed.\n" +
                "Do you want to make changes? (y/n)");
        input = scan.nextLine();
        while (!(input.equals("y") || input.equals("n"))){
            logger.warn("enter correct command (y/n)");
            input = scan.nextLine();
        }

        if (input.equals("y")){
            logger.info("entered \"y\"\ninitiating editing stock procedure");
            System.out.println("Enter changes you want to be made with next syntax:\n" +
                    "\t{flower_name} {flower_price} {flower_quantity} [{flower_name} {flower_price} {flower_quantity} etc]\n" +
                    "Now you starting editing stock");

            while (!input.equals("exit")){
                System.out.println("Available commands:\n" +
                        "stock\t\tshow flowers' names, quantities and prices in actual stock\n" +
                        "exit\t\tend editing stock");
                input = scan.nextLine();
                array = input.split(" ");

                if (input.equals("stock")){
                    stock.print(flowerStock);
                }
                else if (!input.equals("exit")){
                    try {
                        if (!Stock.canEdit(array)){
                            logger.warn("Please enter information for editing stock correctly:\n" +
                                    "\t{flower_name} {flower_price} {flower_quantity} [{flower_name} {flower_price} {flower_quantity} etc]");
                        }
                        else {
                            Stock.edit(flowerStock, array);
                        }
                    } catch (FlowersNegativeIntegerException e){
                        logger.error("flowers' price or quantity is negative integer");
                    }
                }
            }
            logger.info("you finished making changes");
        } else {
            logger.info("entered \"n\"\n\"you did not made any changes\"");
        }

        //------------------------- STARTING WORK WITH FLOWER SHOP -------------------------

        input = "null";
        logger.info("initiating working with shop procedure");

        while (!input.equals("exit")){
            System.out.println("Available commands:\n" +
                    "stock\t\tshow flowers' names, quantities and prices in actual stock\n" +
                    "bouquet\t\tinitiate bouquet creating\n" +
                    "mybouquet\tshow collected bouquet\n" +
                    "update\t\tadd flowers to stock (by default adding random values)\n" +
                    "exit\t\tquit from shop");
            input = scan.nextLine();

            if (input.equals("stock")){
                stock.print(flowerStock);
            }
            else if (input.equals("bouquet")){
                bouquetStock = bouquet.create(flowerStock);
                if (Stock.isEmpty(flowerStock)){
                    System.out.println("Please use \"update\" to fill stock");
                } else{
                    System.out.println("Type \"stock\" to view current stock\n" +
                            "Type \"exit\" to finish making bouquet\n" +
                            "Now create bouquet by command:\n" +
                            "\t{flower_name} {flower_quantity} [{flower_name} {flower_quantity} etc]");
                    while (!input.equals("exit")){
                        input = scan.nextLine();
                        array = input.split(" ");

                        if (input.equals("stock")) {
                            stock.print(flowerStock);
                        } else if (!input.equals("exit")) {
                            try {
                                if (!Bouquet.canFill(array)){
                                    System.out.println("Please enter information for making bouquet correctly:\n" +
                                            "\t{flower_name} {flower_quantity} [{flower_name} {flower_quantity} etc]");
                                }
                                else{
                                    bouquetStock = Bouquet.fill(flowerStock, bouquetStock, array);
                                }
                            } catch (FlowersNegativeIntegerException e){
                                logger.error("flowers' quantity for bouquet is negative integer");
                            }
                        }
                    }
                    input = "null";
                }
            }
                else if (input.equals("mybouquet")) {
                    if (bouquetStock != null){
                        bouquet.print(bouquetStock);
                        System.out.println("Bouquet cost: " + Bouquet.sell(bouquetStock) + "\n");
                    } else{
                        System.out.println("You haven't any bouquets\n");
                    }
            }
            else if (input.equals("update")) {
                Stock.update(flowerStock);
            }
        }
        //------------------------- CLEARING ALL AFTER WORK -------------------------
        stock.clear(flowerStock);
        try {
            bouquet.clear(bouquetStock);
        } catch (NullPointerException e){
            logger.info("none bouquets was created");
        }
        scan.close();
        logger.info("console-input has been closed");

    }
}