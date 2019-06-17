import Bouquet.Bouquet;
import Flowers.*;
import Stock.Stock;

import java.util.Map;
import java.util.Scanner;

public class FlowersTask {

    public static void main(String[] args) {
        //------------------------- INITIALIZE DATA BLOCK -------------------------

        Map<Integer, Flower> stock = new Stock().create();
        Map<Integer, Flower> bouquet = null;
        Scanner scan = new Scanner(System.in);
        String input;
        String[] array;

        //------------------------- PRINTING STOCK BLOCK -------------------------

        System.out.println("Welcome to flower shop.\nYour stock:");
        new Stock().print(stock);

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
                    new Stock().print(stock);
                }
                else if (!input.equals("exit")){
                    if (!Stock.canEdit(array)){
                        System.out.println("Please enter information for editing stock correctly:\n" +
                                "\t{flower_name} {flower_price} {flower_quantity} [{flower_name} {flower_price} {flower_quantity} etc]");
                    }
                    else {
                        Stock.edit(stock, array);
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
                new Stock().print(stock);
            }
            else if (input.equals("bouquet")){
                if (Stock.isEmpty(stock)){
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
                            new Stock().print(stock);
                        } else if (!input.equals("exit")) {
                            bouquet = new Bouquet().create();
                            if (!Bouquet.canCreate(array)){
                                System.out.println("Please enter information for making bouquet correctly:\n" +
                                        "\t{flower_name} {flower_quantity} [{flower_name} {flower_quantity} etc]");
                            }
                            else{
                                bouquet = Bouquet.fill(stock, bouquet, array);
                            }
                        }
                    }
                    input = "null";
                }
            }
                else if (input.equals("mybouquet")) {
                    if (bouquet != null){
                        new Bouquet().print(bouquet);
                        System.out.println("Bouquet cost: " + Bouquet.sell(bouquet) + "\n");
                    } else{
                        System.out.println("You haven't any bouquets\n");
                    }
            }
            else if (input.equals("update")) {
                Stock.update(stock);
            }
        }
        //------------------------- CLEARING ALL AFTER WORK -------------------------
        new Stock().clear(stock);
        new Bouquet().clear(bouquet);
        scan.close();

    }
}