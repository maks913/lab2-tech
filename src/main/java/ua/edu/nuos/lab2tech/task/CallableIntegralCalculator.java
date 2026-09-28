package ua.edu.nuos.lab2tech.task;

import java.util.concurrent.Callable;

public class CallableIntegralCalculator implements Callable<Double> {
    private final IntegralCalculator integralCalculator;

    public CallableIntegralCalculator(double a, double b, int n, Function f) {
        this.integralCalculator = new IntegralCalculator(a, b, n, f);
    }

    @Override
    public Double call() {
        return integralCalculator.calculate();
    }
}
