import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuUtil {
    private FootballClubController controller = new FootballClubController();
    private List<FootballClub> footballClubList = null;

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Football Club Manager!");
        System.out.println("Choose the input file option:");
        System.out.println("1. Use one input file");
        System.out.println("2. Use two input files");
        System.out.println("3. Exit the program");

        int fileChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (fileChoice == 1) {
            // Use one input file
            loadEmployeesFromSingleFile(scanner);
        } else if (fileChoice == 2) {
            // Use two input files
            loadEmployeesFromTwoFiles(scanner);
        } else if (fileChoice == 3) {
            // Exit the program
            System.out.println("Exiting the program.");
            scanner.close();
            return;
        } else {
            // Invalid choice
            System.out.println("Invalid choice. Exiting the program.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Display all football clubs");
            System.out.println("2. Display first n clubs by city");
            System.out.println("3. Count cities with identical club names");
            System.out.println("4. Find common clubs from two files");
            System.out.println("5. Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 :
                    // Display all football clubs
                    FootballClubPrinter.printFootballClubList(footballClubList);
                    break;
                case 2 :
                    // Display first n clubs by city
                    controller.printFirstNFromEachCity(
                            controller.createCityClubMap(footballClubList)
                            , getAmountOfClubs(scanner));
                    break;
                case 3 :
                    // Count cities with identical club names
                    controller.countCitiesWithSameNameClub(footballClubList);
                    break;
                case 4 :
                    // Find common clubs from two files
                    loadEmployeesFromTwoFiles(scanner);
                    break;
                case 5 :
                    // Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default :
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private int getAmountOfClubs(Scanner scanner){
        System.out.print("Enter amount of clubs to show:");
        while (true){
            int num = scanner.nextInt();
            if(num<0){
                System.out.println("Invalid number, try again");
                scanner.nextLine();
            }
            else{
                return num;
            }
        }
    }

    private void loadEmployeesFromSingleFile(Scanner scanner) {
        System.out.print("Enter the file path for the input file: ");
        String filePath = scanner.nextLine();
        try {
            footballClubList = FileReaderUtil.readFootballClubsFromFile(filePath);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void loadEmployeesFromTwoFiles(Scanner scanner) {
        System.out.print("Enter the file path for the first input file: ");
        String filePath1 = scanner.nextLine();
        System.out.print("Enter the file path for the second input file: ");
        String filePath2 = scanner.nextLine();
        try {
            footballClubList = FileReaderUtil.readFootballClubsFromFile(filePath1);
            var tempFootballClubs = FileReaderUtil.readFootballClubsFromFile(filePath2);
            footballClubList.addAll(tempFootballClubs);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
