package Fractions;

import java.math.BigInteger;

public class BigIntegerFraction implements Fraction {
    BigInteger numerator;
    BigInteger denominator;

    public BigIntegerFraction() {
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
    }

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }


    @Override
    public BigIntegerFraction plus(Fraction right) {
        if (right instanceof BigIntegerFraction) {
            BigIntegerFraction other = (BigIntegerFraction) right;
            BigInteger commonDenominator = this.denominator.multiply(other.getDenominator());
            BigInteger newNumerator = (this.numerator.multiply(other.getDenominator())).add(
                    (other.getNumerator().multiply(this.denominator)));
            return new BigIntegerFraction(newNumerator, commonDenominator);
        } else {
            throw new IllegalArgumentException("Unsupported Fractions.Fraction type");
        }
    }

    public BigIntegerFraction divide(BigInteger num){
        return new BigIntegerFraction(this.numerator.divide(num),this.denominator.divide(num));
    }

    @Override
    public String toString() {
        return numerator.toString() + "/" + denominator.toString();
    }
}
