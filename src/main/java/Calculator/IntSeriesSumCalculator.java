package Calculator;

import Fractions.Fraction;
import Fractions.IntFraction;

public class IntSeriesSumCalculator implements IHarmonicSeriesSumCalculator {
    @Override
    public Fraction calculate(int count) {
        IntFraction fraction;
        IntFraction result = new IntFraction(0, 1);
        for (int i=1;i<=count;++i){
            fraction = new IntFraction(1,i);
            result = result.plus(fraction);
            int gcd = GreatestCommonDivisor.gcd(result.getNumerator(), result.getDenominator());
            result = result.divide(gcd);
        }
        return result;
    }
}
