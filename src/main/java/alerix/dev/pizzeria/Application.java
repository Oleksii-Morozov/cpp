package alerix.dev.pizzeria;

import alerix.dev.util.io.terminal.TerminalOutput;

import java.util.Scanner;

import static alerix.dev.util.Menu.showMenu;

public class Application {
    private static final String DINNER_PATH = "dinners";
    private static final String MENU_PATH = "pizza";

    public static void run(Boolean isJson) {
        Processor processor = new Processor(DINNER_PATH, MENU_PATH, isJson);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int choise = showMenu(scanner);
            switch (choise) {
                case 1 -> {
                    TerminalOutput.print(processor.getMenu(), "Menu:\n");
                    TerminalOutput.print(processor.getDinnerList(), "\nDinners:\n");
                }
                case 2 -> TerminalOutput.print(processor.getSortedByTimeDinnerList(), "Dinners sorted by delivery time:\n");
                case 3 -> TerminalOutput.print(processor.getSortedByPriceDinnerList(), "Dinners sorted by price:\n");
                case 4 -> {
                    System.out.print("Enter the number of pizzas: ");
                    int numPizzas = scanner.nextInt();
                    while (numPizzas < 1) {
                        System.out.println("Number of pizzas cannot be less than 1");
                        System.out.print("Enter the number of pizzas: ");
                        numPizzas = scanner.nextInt();
                    }
                    TerminalOutput.print(processor.getDinnersTharHavingMorePizzaThen(numPizzas), "Dinners with more than " + numPizzas + " pizzas:\n");
                }
                case 5 -> TerminalOutput.print(processor.getDinnerWithBiggestOrder(), "Dinner with the biggest order:\n");
                case 6 -> TerminalOutput.printPizzaDinnerMap(processor.getGroupByPizzaMap(), "Dinners grouped by pizza:\n");
                case 7 -> TerminalOutput.printDinnerDurationMap(processor.getExpiresDinnersMap(), "Expired dinners:\n");
                case 8 -> {
                    System.out.print("Enter the name of pizza: ");
                    String pizzaName = scanner.nextLine();
                    if (processor.getDinnersByPizzaName(pizzaName).isEmpty()) {
                        System.out.println("There is no pizza with such name");
                        break;
                    }
                    TerminalOutput.print(processor.getDinnersByPizzaName(pizzaName), pizzaName);
                }
                case 9 -> TerminalOutput.print(processor.getDinnersWithVeganPizza(), "Dinners with vegan pizza: ");
                case 11 -> processor.serializeCollection();
                case 0 -> {
                    System.out.println("Exiting the Pizzeria Menu. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again: ");
            }
        }
    }
}
