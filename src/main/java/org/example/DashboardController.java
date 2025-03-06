package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.Axis;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardController {


    @FXML
    private LineChart<String, Number> LineChart;

    /**
     * Populates the LineChart with a data series using two lists and sets the y-axis label.
     * Also sets the x-axis label to "Time".
     * @param timeList   List of time values (x-axis categories)
     * @param valuesList List of numeric values corresponding to the time values (y-axis)
     * @param seriesName Label for the data series
     * @param yAxisLabel Label for the y-axis
     */
    public void populateGraph(List<String> timeList, List<Number> valuesList, String seriesName, String yAxisLabel) {
        //Checks if there is a coresponding time for each value
        if (timeList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Time list and values list must be of the same size.");
        }
        //sets the x-axis label to "Time"
        LineChart.getXAxis().setLabel("Time");
        Axis<Number> yAxis = LineChart.getYAxis();
        yAxis.setLabel(yAxisLabel);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        // Loops through the lists and add data points to the series.
        for (int i = 0; i < timeList.size(); i++) {
            series.getData().add(new XYChart.Data<>(timeList.get(i), valuesList.get(i)));
        }
        LineChart.getData().add(series);
    }

    /**
     * The initialize() method is called automatically when the FXML is loaded and populates the graph with demo data(This is primarily for testing purposes)
     */
    @FXML
    public void initialize() {
        // Demo data for testing; in a real scenario these lists might come from another part of your app.
        List<String> times = List.of("10:00", "10:05", "10:10", "10:15");
        List<Number> values = List.of(100, 200, 150, 300);

        populateGraph(times, values, "Demo Series", "Number of Clicks");
    }

    /**
     * Handles the "Switch to Histogram" button click event to switch the scene to the histogram page.
     * @param event
     */
    @FXML
    public void switchToHistogram(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Histogram.fxml"));
            Parent histogramView = loader.load();
            Scene histogramScene = new Scene(histogramView);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(histogramScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the LineChart with new data series using two lists and sets the y-axis label.
     * Also sets the x-axis label to "Time".
     * @param newTimeList   List of new time values (x-axis categories)
     * @param newValuesList List of new numeric values corresponding to the time values (y-axis)
     * @param seriesName    Label for the new data series
     * @param yAxisLabel    Label for the y-axis
     */
    public void updateGraph(List<String> newTimeList, List<Number> newValuesList, String seriesName, String yAxisLabel) {
        if (newTimeList.size() != newValuesList.size()) {
            throw new IllegalArgumentException("Time list and values list must be of the same size.");
        }
        // Set the x-axis label to "Time"
        LineChart.getXAxis().setLabel("Time");

        // Update y-axis label
        LineChart.getYAxis().setLabel(yAxisLabel);

        // Clear existing data and repopulate
        LineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        for (int i = 0; i < newTimeList.size(); i++) {
            series.getData().add(new XYChart.Data<>(newTimeList.get(i), newValuesList.get(i)));
        }
        LineChart.getData().add(series);
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
}
