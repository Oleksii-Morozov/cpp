package alerix.dev.util;

import alerix.dev.util.io.terminal.TerminalOutput;

import java.util.Scanner;

public class Menu {
    public static int showMenu(Scanner scanner) {
        TerminalOutput.print("Pizzeria App Menu:");
        TerminalOutput.print("1. View Dinners and Menu");
        TerminalOutput.print("2. View Dinners Sorted by Delivery Time");
        TerminalOutput.print("3. View Dinners Sorted by Price");
        TerminalOutput.print("4. View Dinners with More Pizzas Than");
        TerminalOutput.print("5. View Dinner with the Biggest Order");
        TerminalOutput.print("6. View Dinners Grouped by Pizza");
        TerminalOutput.print("7. View Expired Dinners");
        TerminalOutput.print("8. View Dinners with Pizza");
        TerminalOutput.print("9. View Dinners with Vegan Pizza");
        TerminalOutput.print("11. Serialize pizzas collection");
        TerminalOutput.print("0. Exit");
        TerminalOutput.print("Enter your choice: ");

        int choice = scanner.nextInt();
        while(choice < 0 || choice > 11) {
            TerminalOutput.print("Invalid choice. Try again: ");
            choice = scanner.nextInt();
        }
        scanner.nextLine();
        return choice;
    }
}
