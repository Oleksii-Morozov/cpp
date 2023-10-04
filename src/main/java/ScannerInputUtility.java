import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerInputUtility {
        public int getNumOfIterations(){
            System.out.print("Enter the number of iterations: ");
            Scanner scanner = new Scanner(System.in);
            int count;
    
            while(true){
                try {
                    count = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("You entered an incorrect character, please try again:");
                    scanner.next();
                }
            }
            return count;
        }
        public boolean doYouWantToContinue(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to continue? (y/n):");
            while (true){
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("n")) {
                    System.out.println("Goodbye!");
                    return false;
                }
                else if(input.equalsIgnoreCase("y")){
                    return true;
                }
                System.out.println("You entered an incorrect character, please try again:");
            }
        }
    }