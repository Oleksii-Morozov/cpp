import Calculator.BigIntegerSeriesSumCalculator;
import Calculator.IHarmonicSeriesSumCalculator;
import Calculator.IntSeriesSumCalculator;

public class CalculatorSelector {
    public static IHarmonicSeriesSumCalculator selectCalculator(int count) {
        if (count >= Constants.HARMONIC_SERIES_INT_USING_LIMIT) {
            System.out.println("Calculation with biginteger fraction class");
            return new BigIntegerSeriesSumCalculator();
        } else {
            System.out.println("Calculation with integer fraction class");
            return new IntSeriesSumCalculator();
        }
    }
}