package com.slyone.design.strategy;

public class CompoundInterestCalculator implements InterestCalculationStrategy {
    @Override
    public double calculateInterest(final double principal, final double rate, final int term) {
        return (principal * Math.pow(1.0 + rate/100.0, term) - principal);
    }
    @Override
    public String toString() {
        return "Compound Interest";
    }
}
