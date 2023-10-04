import CreativityGoodType.CreativityGood;
import CreativityGoodType.GoodType;

import java.util.*;
import java.util.stream.*;

public class CreativityGoodsUtility {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Creativity Goods Utility!");
        int numberOfGoods = 0;
        while (numberOfGoods <= 0) {
            System.out.print("Enter number of goods (must be positive integer): ");
            if(scanner.hasNextInt()) {
                numberOfGoods = scanner.nextInt();
                if(numberOfGoods <= 0) {
                    System.out.println("Please, enter positive integer!");
                }
            } else {
                System.out.println("Invalid input. Please, enter positive integer!");
                scanner.next(); // Consume invalid input
            }
        }
        scanner.nextLine(); // Consume newline left-over
        CreativityGoodsManager manager = new CreativityGoodsManager(numberOfGoods);

        chooseOperation(scanner, manager);
    }

    private static void chooseOperation(Scanner scanner, CreativityGoodsManager manager) {
        while(true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Show all goods");
            System.out.println("2. Sort goods");
            System.out.println("3. Find goods by type");
            System.out.println("4. Exit");
            System.out.print("Enter operation number (1|2|3|4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1 -> showAllGoods(manager.getGoods());
                case 2 -> sortGoods(scanner, manager);
                case 3 -> findGoodsByType(scanner, manager);
                case 4 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid operation number!");
            }
        }
    }

    private static void showAllGoods(CreativityGood[] goods) {
        System.out.println("\nAll goods:\n"
                        + String.format("%-5s | %-30s | %-30s | %-15s | %-5s"
                            , "ID", "Name", "Type", "Usage Area", "Price")
                        + "\n"
                        + "-".repeat(97));
        Arrays.stream(goods).forEach(System.out::println);
    }

    private static void sortGoods(Scanner scanner, CreativityGoodsManager manager) {
        System.out.println("\nChoose sorting options:");
        System.out.println("1. By name");
        System.out.println("2. By price");
        System.out.println("Enter sorting options ('1 2' for name and price): ");
        String sortingOptions = scanner.nextLine();

        System.out.print("Do you want to reverse the sorting order? (y/n): ");
        boolean reverse = scanner.nextLine().equalsIgnoreCase("y");

        String[] sortingChoiceArray = sortingOptions.split(" ");
        Arrays.sort(sortingChoiceArray);
        int[] chosenSortingChoices = new int[sortingChoiceArray.length];
        for (int i = 0; i < sortingChoiceArray.length; i++) {
            chosenSortingChoices[i] = Integer.parseInt(sortingChoiceArray[i]);
        }
        manager.sortGoods(chosenSortingChoices,reverse);
        System.out.println("\nSorted Creativity goods:");
        showAllGoods(manager.getGoods());
    }

    private static void findGoodsByType(Scanner scanner, CreativityGoodsManager manager) {
        System.out.println("\nChoose types of goods:");
        var availableTypes = Arrays.stream(manager.getGoods())
                .map(CreativityGood::type)
                .map(GoodType::typeName)
                .distinct()
                .toArray(String[]::new);

        IntStream.range(0, availableTypes.length)
                .mapToObj(i -> (i+1) + ". " + availableTypes[i])
                .forEach(System.out::println);

        System.out.print("Enter type numbers (separated by space): ");
        var selectedIndicates = Arrays.stream(
                    scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        System.out.println("\nCreativity goods by selected type(s):");
        showAllGoods(manager.findGoodsByType(availableTypes, selectedIndicates));
    }
}
