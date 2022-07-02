package controller;

import bo.BOFactory;
import bo.custom.ReportsBO;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.util.HashMap;

public class ReportManagementFormController {
    public LineChart Annualchart;
    public LineChart MonthlyCHart;
    public LineChart DailyCHart;
    public JFXComboBox<Integer> cmbYears;

    private ReportsBO rBO = (ReportsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REPORTS);
    public void initialize(){
         Annualchart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
         MonthlyCHart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
         DailyCHart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        try {
            cmbYears.getItems().addAll(rBO.getDistinctYears());
            cmbYears.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=null)setMonthlySalesChart(newValue);
            });

            setMonthlySalesChart(LocalDate.now().getYear());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDAilySalesChart();
         setAnnualSalesChart();
    }

    private void setMonthlySalesChart(Integer newValue) {
        try {
            XYChart.Series series = new XYChart.Series();
            HashMap<String, Double> monthlySales = rBO.getMonthlySales(newValue);
            for (String date : monthlySales.keySet()) {
                series.getData().add(new XYChart.Data<>(date, monthlySales.get(date)));
            }
            MonthlyCHart.getData().add(series);
        }catch(Exception e){
            new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setAnnualSalesChart() {
        try {
            XYChart.Series series = new XYChart.Series();
            HashMap<String, Double> annualSales = rBO.getAnnualSales();
            for (String date : annualSales.keySet()) {
                series.getData().add(new XYChart.Data<>(date, annualSales.get(date)));
            }
            Annualchart.getData().add(series);
        }catch(Exception e){
            new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setDAilySalesChart() {
        try {
            XYChart.Series series = new XYChart.Series();
            HashMap<String, Double> distinctSales = rBO.getDistinctSales();
            for (String date : distinctSales.keySet()) {
                series.getData().add(new XYChart.Data<>(date, distinctSales.get(date)));
            }
            DailyCHart.getData().add(series);
        }catch(Exception e){
            new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
        }
    }
}
