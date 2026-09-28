package ua.edu.nuos.lab2tech.task;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

    @FXML
    private TextField intervals;
    @FXML
    private TextField threads;
    @FXML
    private Label resultLabel;

    @FXML
    private void calculate() throws ExecutionException, InterruptedException {
        double a = 1.0;
        double b = 4.0;

        Function f = new Function();
        int n = Integer.parseInt(intervals.getText());
        int numberOfThreads = Integer.parseInt(threads.getText());

        long start = System.currentTimeMillis();
        double delta = (b - a) / numberOfThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            double ai = a + i * delta;
            double bi = ai + delta;
            int ni = n / numberOfThreads;

            Future<Double> future = executor.submit(new CallableIntegralCalculator(ai, bi, ni, f));
            futures.add(future);
        }

        executor.shutdown();

        double totalSum = 0;
        for (Future<Double> future : futures) {
            totalSum += future.get();
        }

        long end = System.currentTimeMillis();
        resultLabel.setText("Total time: " + (end - start) + "ms\n" + " Result: " + totalSum);
    }
}