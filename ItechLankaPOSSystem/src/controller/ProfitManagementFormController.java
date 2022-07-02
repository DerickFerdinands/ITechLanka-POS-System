package controller;

import View.TM.ProfitTM;
import bo.BOFactory;
import bo.custom.ProfitBO;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class ProfitManagementFormController {
    public TableView<ProfitTM> tblDirProfits;
    public TableColumn colDirector;
    public TableColumn colDirName;
    public TableColumn colProfitMargin;
    public TableColumn colTotalProfit;
    public BarChart barChart;

    private ProfitBO pBO = (ProfitBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DIRECTOR_PROFIT);

    public void initialize(){
         colDirector.setCellValueFactory(new PropertyValueFactory<>("DirID"));
         colDirName.setCellValueFactory(new PropertyValueFactory<>("DirName"));
         colProfitMargin.setCellValueFactory(new PropertyValueFactory<>("profitMargin"));
         colTotalProfit.setCellValueFactory(new PropertyValueFactory<>("TotalProfit"));

         loadAllDirectors();
         loadBarChartValues();
    }

    private void loadBarChartValues() {
        XYChart.Series series = new XYChart.Series();
        tblDirProfits.getItems().forEach(profitTM -> {
            series.getData().add(new XYChart.Data<>(profitTM.getDirID(),profitTM.getTotalProfit()));
        });
        barChart.getData().add(series);
        barChart.setMaxWidth(30);
    }

    private void loadAllDirectors() {

        try {
            double profit = pBO.getProfit();
            System.out.println(profit);
            tblDirProfits.getItems().addAll(pBO.getAllDirectors().stream().map(dto -> {return new ProfitTM(dto.getId(),dto.getName(),dto.getProfitMargin(), BigDecimal.valueOf(profit*dto.getProfitMargin()/100));}).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
