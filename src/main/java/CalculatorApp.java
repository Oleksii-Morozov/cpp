import Calculator.IHarmonicSeriesSumCalculator;

public class CalculatorApp {
    public static void run() {
        ScannerInputUtility scannerInputUtility = new ScannerInputUtility();
        do{
            int count = scannerInputUtility.getNumOfIterations();
            IHarmonicSeriesSumCalculator calculator = CalculatorSelector.selectCalculator(count);
            System.out.println("Result:" + calculator.calculate(count).toString());
        }while (scannerInputUtility.doYouWantToContinue());
    }
}