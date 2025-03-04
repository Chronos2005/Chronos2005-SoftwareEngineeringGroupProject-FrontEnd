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

    // This fx:id must match the one in your FXML file.
    @FXML
    private LineChart<String, Number> LineChart;

    /**
     * Populates the LineChart with a data series using two lists and sets the y-axis label.
     *
     * @param timeList   List of time values (x-axis categories)
     * @param valuesList List of numeric values corresponding to the time values (y-axis)
     * @param seriesName Label for the data series
     * @param yAxisLabel Label for the y-axis
     */
    public void populateGraph(List<String> timeList, List<Number> valuesList, String seriesName, String yAxisLabel) {
        if (timeList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Time list and values list must be of the same size.");
        }

        // Set the y-axis label
        Axis<Number> yAxis = LineChart.getYAxis();
        yAxis.setLabel(yAxisLabel);

        // Create a new data series and set its name.
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        // Loop through the lists and add data points to the series.
        for (int i = 0; i < timeList.size(); i++) {
            series.getData().add(new XYChart.Data<>(timeList.get(i), valuesList.get(i)));
        }

        // Add the series to the LineChart.
        LineChart.getData().add(series);
    }

    /**
     * The initialize() method is called automatically when the FXML is loaded.
     * Here we call populateGraph with demo data.
     */
    @FXML
    public void initialize() {
        // Demo data for testing; in a real scenario these lists might come from another part of your app.
        List<String> times = List.of("10:00", "10:05", "10:10", "10:15");
        List<Number> values = List.of(100, 200, 150, 300);

        populateGraph(times, values, "Demo Series", "Response Time (ms)");
    }
    @FXML
    public void switchToHistogram(ActionEvent event) {
        try {
            // Load the Histogram FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Histogram.fxml"));
            Parent histogramView = loader.load();

            // Create a new Scene with the histogram view
            Scene histogramScene = new Scene(histogramView);

            // Get the current stage using the event's source node
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage and show it
            currentStage.setScene(histogramScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
