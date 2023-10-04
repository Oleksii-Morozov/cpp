package Calculator;

import Fractions.BigIntegerFraction;
import Fractions.Fraction;

import java.math.BigInteger;

public class BigIntegerSeriesSumCalculator implements IHarmonicSeriesSumCalculator {
    @Override
    public Fraction calculate(int count) {
        BigIntegerFraction fraction;
        BigIntegerFraction result = new BigIntegerFraction(BigInteger.ZERO, BigInteger.ONE);
        for (int i=1;i<=count;++i){
            fraction = new BigIntegerFraction(BigInteger.ONE,BigInteger.valueOf(i));
            result = result.plus(fraction);
            BigInteger gcd = result.getNumerator().gcd(result.getDenominator());
            result = result.divide(gcd);
        }
        return result;
    }
}
