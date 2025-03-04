package org.example;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HistogramController {

    @FXML
    private BarChart<String, Number> histogramChart;

    /**
     * Called automatically after the FXML is loaded.
     * This method populates the histogram with the click cost distribution,
     * calculating frequency density (frequency divided by bin width).
     */
    @FXML
    public void initialize() {
        // Sample click cost data in pence (replace with real data if available)
        List<Integer> clickCosts = Arrays.asList(5, 12, 7, 20, 15, 22, 8, 14, 19, 27, 33, 17, 10, 9, 11, 45, 38, 25, 30, 16);

        // Define the width of each bin (e.g., 10 pence per bin)
        int binWidth = 10;

        // Determine the minimum and maximum click costs
        int minCost = clickCosts.stream().min(Integer::compareTo).orElse(0);
        int maxCost = clickCosts.stream().max(Integer::compareTo).orElse(0);

        // Calculate the number of bins (round up)
        int numBins = ((maxCost - minCost) / binWidth) + 1;

        // Use a TreeMap to maintain the order of bins (key: bin label, value: frequency)
        Map<String, Integer> histogramData = new TreeMap<>();

        // Initialize bins with zero frequency
        for (int i = 0; i < numBins; i++) {
            int lower = minCost + i * binWidth;
            int upper = lower + binWidth - 1;
            String binLabel = lower + "-" + upper;
            histogramData.put(binLabel, 0);
        }

        // Count the frequency of click costs in each bin
        for (int cost : clickCosts) {
            int binIndex = (cost - minCost) / binWidth;
            int lower = minCost + binIndex * binWidth;
            int upper = lower + binWidth - 1;
            String binLabel = lower + "-" + upper;
            histogramData.put(binLabel, histogramData.get(binLabel) + 1);
        }

        // Create a new data series for the histogram and calculate frequency density
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Click Cost Distribution");

        // Add each bin's frequency density (frequency / binWidth) to the series
        for (Map.Entry<String, Integer> entry : histogramData.entrySet()) {
            double density = (double) entry.getValue() / binWidth;
            series.getData().add(new XYChart.Data<>(entry.getKey(), density));
        }

        // Add the series to the BarChart
        histogramChart.getData().add(series);
    }
}
