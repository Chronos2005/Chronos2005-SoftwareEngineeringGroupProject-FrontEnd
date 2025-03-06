package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HistogramController {

    @FXML
    private BarChart<String, Number> histogramChart;

    private List<Integer> clickCosts;

    public void setClickCosts(List<Integer> clickCosts) {
        this.clickCosts = clickCosts;
        generateHistogram();
    }

    @FXML
    public void initialize() {
        if (clickCosts == null) {
            clickCosts = Arrays.asList(5, 12, 7, 20, 15, 22, 8, 14, 19, 27,
                    33, 17, 10, 9, 11, 45, 38, 25, 30, 16);
        }
        generateHistogram();
    }

    private void generateHistogram() {
        int binWidth = 10;

        int minCost = clickCosts.stream().min(Integer::compareTo).orElse(0);
        int maxCost = clickCosts.stream().max(Integer::compareTo).orElse(0);

        int numBins = ((maxCost - minCost) / binWidth) + 1;
        Map<String, Integer> histogramData = new TreeMap<>();

        for (int i = 0; i < numBins; i++) {
            int lowerBound = minCost + i * binWidth;
            int upperBound = lowerBound + binWidth - 1;
            String binLabel = lowerBound + "-" + upperBound;
            histogramData.put(binLabel, 0);
        }

        for (int cost : clickCosts) {
            int binIndex = (cost - minCost) / binWidth;
            int lowerBound = minCost + binIndex * binWidth;
            int upperBound = lowerBound + binWidth - 1;
            String binLabel = lowerBound + "-" + upperBound;
            histogramData.put(binLabel, histogramData.get(binLabel) + 1);
        }
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Click Cost Distribution");
        //Calculates frequency density for each bin
        histogramData.forEach((binLabel, frequency) -> {
            double density = (double) frequency / binWidth;
            series.getData().add(new XYChart.Data<>(binLabel, density));
        });
        histogramChart.getData().clear();
        histogramChart.getData().add(series);
    }
    /**
     * Handles the "Switch to File Uploader" button click event to switch the scene to the file uploader page.
     * @param event
     */
    @FXML
    public void switchToFileUploader(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/FileUploader.fxml"));
            Parent FileUploaderView = loader.load();
            Scene uploaderScene = new Scene(FileUploaderView);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(uploaderScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchtoLineChart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Dashboard.fxml"));
            Parent FileUploaderView = loader.load();
            Scene uploaderScene = new Scene(FileUploaderView);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(uploaderScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
