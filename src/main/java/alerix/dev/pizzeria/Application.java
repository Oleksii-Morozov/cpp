package alerix.dev.pizzeria;

import alerix.dev.util.io.terminal.TerminalOutput;

import java.util.Scanner;

import static alerix.dev.util.Menu.showMenu;

public class Application {
    private static final String DINNER_PATH = "dinners";
    private static final String MENU_PATH = "pizza";

    public static void run() {
        TerminalOutput.print("Welcome to the Pizzeria App!");
        TerminalOutput.print("Load from Ser/Json/Yaml? (1|2|3): ");
        Scanner scanner = new Scanner(System.in);
        Processor processor;
        while (true) {
            int type = scanner.nextInt();
            if (type < 1 || type > 3) {
                System.out.println("Invalid type. Try again: ");
                continue;
            }
            scanner.nextLine();
            processor = new Processor(DINNER_PATH, MENU_PATH, type);
            break;
        }
        while (true) {

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
                case 9 -> {
                    System.out.print("Enter dinner name: ");
                    String dinnerName = scanner.nextLine();
                    if (processor.getLighterPizzaByDinner(dinnerName) == null) {
                        System.out.println("There is no dinner with such name");
                        break;
                    }
                    TerminalOutput.print(processor.getLighterPizzaByDinner(dinnerName), dinnerName);
                }
                case 11 -> TerminalOutput.print(processor.getDinnersWithVeganPizza(), "Dinners with vegan pizza: ");
                case 12 -> {
                    TerminalOutput.print("Serializing pizzas collection: ");
                    TerminalOutput.print("Serialize to from Ser/Json/Yaml? (1|2|3): ");
                    while (true) {
                        int type = scanner.nextInt();
                        if (type < 1 || type > 3) {
                            System.out.println("Invalid type. Try again: ");
                            continue;
                        }
                        processor.serializeCollection(type);
                        break;
                    }
                }
                case 0 -> {
                    System.out.println("Exiting the Pizzeria Menu. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again: ");
            }
        }
    }
}
