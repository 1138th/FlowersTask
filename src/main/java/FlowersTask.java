import Bouquet.Bouquet;
import Exceptions.FlowersNegativeIntegerException;
import Flowers.*;
import Stock.Stock;

import java.util.Map;
import java.util.Scanner;

public class FlowersTask {

    public static void main(String[] args) {
        //------------------------- INITIALIZE DATA BLOCK -------------------------
        Stock stock = new Stock();
        Bouquet bouquet = new Bouquet();
        Map<Integer, Flower> flowerStock = stock.create();
        Map<Integer, Flower> bouquetStock = null;
        Scanner scan = new Scanner(System.in);
        String input;
        String[] array;

        //------------------------- PRINTING STOCK BLOCK -------------------------

        System.out.println("Welcome to flower shop.\nYour stock:");
        stock.print(flowerStock);

        //------------------------- EDITING STOCK BLOCK -------------------------

        System.out.println("Prices and quantities are generated fixed.\n" +
                "Do you want to make changes? (y/n)");

        input = scan.nextLine();
        while (!(input.equals("y") || input.equals("n"))){
            System.out.println("Please enter correct command (y/n)");
            input = scan.nextLine();
        }

        if (input.equals("y")){
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
                            System.out.println("Please enter information for editing stock correctly:\n" +
                                    "\t{flower_name} {flower_price} {flower_quantity} [{flower_name} {flower_price} {flower_quantity} etc]");
                        }
                        else {
                            Stock.edit(flowerStock, array);
                        }
                    } catch (FlowersNegativeIntegerException e){
                        System.out.println("Error: flowers' price or quantity is negative integer");
                    }
                }
            }
            System.out.println("You finished making changes");
        } else System.out.println("You did not make any changes");

        //------------------------- STARTING WORK WITH FLOWER SHOP -------------------------

        input = "null";
        System.out.println("Now you starting work with shop");

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
                                System.out.println("Flowers' quantity for bouquet is negative integer");
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
            System.out.println("You had none bouquets.");
        }
        scan.close();

    }
}