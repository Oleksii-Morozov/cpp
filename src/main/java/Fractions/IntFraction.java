package Fractions;

public class IntFraction implements Fraction {
    int numerator;
    int denominator;

    public IntFraction() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public IntFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public IntFraction plus(Fraction right) {
        if (right instanceof IntFraction) {
            IntFraction other = (IntFraction) right;
            int commonDenominator = this.denominator * other.getDenominator();
            int newNumerator = (this.numerator * other.getDenominator()) + (other.getNumerator() * this.denominator);
            return new IntFraction(newNumerator, commonDenominator);
        } else {
            throw new IllegalArgumentException("Unsupported Fractions.Fraction type");
        }
    }

    public IntFraction divide(int num){
        return new IntFraction(numerator/num,denominator/num);
    }

    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }
}
