package controller;

import Model.DashboardButton;
import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DashBoardFormController {
    public AnchorPane MainContext;
    public JFXTextField txtSearch;
    public JFXButton btnItem;
    public JFXButton btnDashboard;
    public ImageView imgDash;
    public JFXButton btnCustomer;
    public JFXButton btnQuote;
    public JFXButton btnOrders;
    public JFXButton btnPackages;
    public JFXButton btnDIrector;
    public JFXButton btnSupplier;
    public JFXButton btnReport;
    public JFXButton btnProfit;
    public JFXButton btnSettings;
    public ImageView imgCus;
    public ImageView imgQuote;
    public ImageView imgOrder;
    public ImageView imgPackage;
    public ImageView imgItem;
    public ImageView imgDirector;
    public ImageView imgSupply;
    public ImageView imgReport;
    public ImageView imgProfit;
    public ImageView imgSettings;
    public AnchorPane NavigationContext;

    public ImageView imgPules1;
    public ImageView imgPules2;
    public ImageView imgPules3;
    public ImageView imgPules4;

    public Label lblDash1;
    public Label lblDash2;
    public Label lblDash3;
    public Label lblDash4;
    public BarChart barChartPerformance;
    public AnchorPane ReviewsPane;
    public AnchorPane ProductsPane;
    public AnchorPane CategoriesPane;
    public AnchorPane ExtrasPane;

    ArrayList<DashboardButton> btnList = new ArrayList<>();
    DashboardButton lastClicked;
    ImageView image = new ImageView(new Image("/Assets/image 222(40).png"));

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        DBConnection.getInstance().getConnection();
        new FadeIn(MainContext).setSpeed(10).play();
        new FadeIn(NavigationContext).setSpeed(5).play();

        btnList.add(new DashboardButton(btnDashboard, imgDash, new Image("/Assets/image (25).png"), 95, new Image("/Assets/image (4).png")));
        btnList.add(new DashboardButton(btnCustomer, imgCus, new Image("/Assets/image (26).png"), 172, new Image("/Assets/image (6).png")));
        btnList.add(new DashboardButton(btnQuote, imgQuote, new Image("/Assets/image (27).png"), 243, new Image("/Assets/image (7).png")));
        btnList.add(new DashboardButton(btnOrders, imgOrder, new Image("/Assets/image (28).png"), 316, new Image("/Assets/image (10).png")));
        btnList.add(new DashboardButton(btnPackages, imgPackage, new Image("/Assets/image (29).png"), 389, new Image("/Assets/image (11).png")));
        btnList.add(new DashboardButton(btnItem, imgItem, new Image("/Assets/image (30).png"), 468, new Image("/Assets/image (14).png")));
        btnList.add(new DashboardButton(btnDIrector, imgDirector, new Image("/Assets/image (31).png"), 540, new Image("/Assets/image (15).png")));
        btnList.add(new DashboardButton(btnSupplier, imgSupply, new Image("/Assets/image (32).png"), 611, new Image("/Assets/image (18).png")));
        btnList.add(new DashboardButton(btnReport, imgReport, new Image("/Assets/image (33).png"), 686, new Image("/Assets/image (19).png")));
        btnList.add(new DashboardButton(btnProfit, imgProfit, new Image("/Assets/image (34).png"), 756, new Image("/Assets/image (22).png")));
        btnList.add(new DashboardButton(btnSettings, imgSettings, new Image("/Assets/image (35).png"), 828, new Image("/Assets/image (23).png")));
        lastClicked = btnList.get(0);

        barChartPerformance.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        setBarchart();
    }

    public void goToItemsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ItemManagementForm");
    }

    public void changeButtonUI(MouseEvent mouseEvent) {
        Object o = mouseEvent.getSource();
        if (o instanceof JFXButton) {
            JFXButton btn = (JFXButton) o;
            for (DashboardButton button : btnList) {
                if (button.getBtn() == btn) {
                    lastClicked = button;
                    button.getBtn().setTextFill(Color.color(0.20, 0.18, 0.29));
                    button.getView().setImage(button.getSelectedImage());

                } else {
                    button.getBtn().setTextFill(Color.color(0.52, 0.55, 0.58));
                    button.getView().setImage(button.getUnselectedImage());
                }
            }
        }
    }

    public void goToSettingsOnAction(ActionEvent actionEvent) {
    }

    public void goToProfitOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ProfitManagementForm");
    }

    public void goToReportsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ReportManagementForm");
    }

    public void goToSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SupplierMangementForm");
    }

    public void goToDirectorsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DirectorManagementForm");
    }

    public void goToPackagesOnAction(ActionEvent actionEvent) throws IOException {
        setUI("OrderHistoryForm");
    }

    public void goToOrdersOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PurchaseManagementForm");
    }

    public void goToQuotationOnAction(ActionEvent actionEvent) throws IOException {
        setUI("QuotationManagementForm");
    }

    public void GoToCustomersOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerManagementForm");
    }

    public void GoToDashboardOnAction(ActionEvent actionEvent) throws IOException {
        goToDashboard();
    }

    public void MaximizeButton(MouseEvent mouseEvent) throws IOException {
        Object o = mouseEvent.getSource();
        if (o instanceof JFXButton) {
            JFXButton btn = (JFXButton) o;
            for (DashboardButton button : btnList) {
                if (button.getBtn() == btn) {
                    button.getBtn().setStyle("-fx-font-size: 15;");
                    button.getBtn().setTextFill(Color.color(0.20, 0.18, 0.29));
                    button.getView().setFitHeight(35);
                    button.getView().setFitWidth(35);
                    button.getView().setLayoutY(button.getImageLayoutY() - 5);

                } else {
                    button.getBtn().setStyle("-fx-font-size: 13;");
                    button.getBtn().setTextFill(Color.color(0.52, 0.55, 0.58));
                    button.getView().setFitHeight(30);
                    button.getView().setFitWidth(30);
                    button.getView().setImage(button.getUnselectedImage());

                }
            }
            if (lastClicked != null) {
                lastClicked.getBtn().setTextFill(Color.color(0.20, 0.18, 0.29));
                lastClicked.getView().setImage(lastClicked.getSelectedImage());
            }
        }
    }

    public void clearButtonUI(MouseEvent mouseEvent) {
        Object o = mouseEvent.getSource();
        if (o instanceof JFXButton) {
            JFXButton btn = (JFXButton) o;
            for (DashboardButton button : btnList) {
                button.getBtn().setStyle("-fx-font-size: 13;");
                button.getBtn().setTextFill(Color.color(0.52, 0.55, 0.58));
                button.getView().setFitHeight(30);
                button.getView().setFitWidth(30);
                button.getView().setImage(button.getUnselectedImage());
                button.getView().setLayoutY(button.getImageLayoutY());
            }
            if (lastClicked != null) {
                lastClicked.getBtn().setTextFill(Color.color(0.20, 0.18, 0.29));
                lastClicked.getView().setImage(lastClicked.getSelectedImage());
            }
        }
    }

    public void customizePane(AnchorPane pane) {
        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
        scaleT.setToX(1.1);
        scaleT.setToY(1.1);
        scaleT.play();

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CORNFLOWERBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        pane.setEffect(glow);
    }

    public void ResetPane(AnchorPane pane){

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            pane.setEffect(null);


    }

    public void ShowPaneUI(MouseEvent mouseEvent) {

        Object o = mouseEvent.getSource();

        if (o instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) o;

            switch (pane.getId()) {

                case "ReviewsPane": {
                    customizePane(ReviewsPane);
                }
                break;
                case "ProductsPane": {
                    customizePane(ProductsPane);
                }
                break;
                case "CategoriesPane": {
                    customizePane(CategoriesPane);
                }
                break;
                case "ExtrasPane": {
                    customizePane(ExtrasPane);
                }
                break;
            }

        }
    }

    public void HidePaneUI(MouseEvent mouseEvent) {
        Object o = mouseEvent.getSource();

        if (o instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) o;

            switch (pane.getId()) {

                case "ReviewsPane": {
                    ResetPane(ReviewsPane);
                }
                break;
                case "ProductsPane": {
                    ResetPane(ProductsPane);
                }
                break;
                case "CategoriesPane": {
                    ResetPane(CategoriesPane);
                }
                break;
                case "ExtrasPane": {
                    ResetPane(ExtrasPane);
                }
                break;
            }

        }

    }

    public void setUI(String URI) throws IOException {
        NavigationContext.getChildren().clear();
        NavigationContext.getChildren().add(FXMLLoader.load(getClass().getResource("/View/" + URI + ".fxml")));
        new FadeIn(NavigationContext).setSpeed(5).play();
    }

    public void goToDashboard() throws IOException {
        MainContext.getChildren().clear();
        MainContext.getChildren().add(FXMLLoader.load(getClass().getResource("/View/DashBoardForm.fxml")));

    }

    public void imagesPulseOnAction(MouseEvent mouseEvent) {

//
//        Object o = mouseEvent.getSource();
//
//        if (o instanceof ImageView) {
//            ImageView i = (ImageView) o;
//
//
//            if(i.getId().equals("imgS")){
//                new Pulse(imgPules1).play();
//                new Pulse(lblDash1).play();
//
//            }else if(i.getId().equals("imgS1")){
//                new Pulse(imgPules2).play();
//                new Pulse(lblDash2).play();
//
//            }else if(i.getId().equals("imgS2")){
//                new Pulse(imgPules3).play();
//                new Pulse(lblDash3).play();
//
//            }else if(i.getId().equals("imgS3")){
//                new Pulse(imgPules4).play();
//                new Pulse(lblDash4).play();
//
//            }
//
//        }

    }

    public void lblMouseEnteredOnAction(MouseEvent mouseEvent) {

        Object o = mouseEvent.getSource();

        if (o instanceof Label) {
            Label i = (Label) o;
            if (i.getId().equals("lbl1")) {
                imgPules1.setStyle("-fx-effect: dropshadow(gaussian,#6495ED,20,0.0,0,0);");
            } else if (i.getId().equals("lbl2")) {
                imgPules2.setStyle("-fx-effect: dropshadow(gaussian,#6495ED,20,0.0,0,0);");

            } else if (i.getId().equals("lbl3")) {
                imgPules3.setStyle("-fx-effect: dropshadow(gaussian,#6495ED,20,0.0,0,0);");

            } else if (i.getId().equals("lbl4")) {
                imgPules4.setStyle("-fx-effect: dropshadow(gaussian,#6495ED,20,0.0,0,0);");

            }
        }


    }

    public void lblMouseExitedOnAction(MouseEvent mouseEvent) {
        Object o = mouseEvent.getSource();

        if (o instanceof Label) {
            Label i = (Label) o;
            if (i.getId().equals("lbl1")) {
                imgPules1.setStyle(null);
            } else if (i.getId().equals("lbl2")) {
                imgPules2.setStyle(null);
            } else if (i.getId().equals("lbl3")) {
                imgPules3.setStyle(null);
            } else if (i.getId().equals("lbl4")) {
                imgPules4.setStyle(null);
            }
        }
    }

    private void setBarchart() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Sample Name");
        //populating the series with data
        series.getData().add(new XYChart.Data("January", 577));
        series.getData().add(new XYChart.Data("February", 22));
        series.getData().add(new XYChart.Data("March", 452));
        series.getData().add(new XYChart.Data("April", 245));
        series.getData().add(new XYChart.Data("May", 87));
        series.getData().add(new XYChart.Data("June", 447));
        series.getData().add(new XYChart.Data("July", 699));
        series.getData().add(new XYChart.Data("August", 58));
        series.getData().add(new XYChart.Data("September", 45));
        series.getData().add(new XYChart.Data("October", 252));
        series.getData().add(new XYChart.Data("November", 45));
        series.getData().add(new XYChart.Data("December", 25));

        barChartPerformance.getData().add(series);
    }
}
