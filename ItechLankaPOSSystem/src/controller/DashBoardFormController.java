package controller;

import Model.DashboardButton;
import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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

    ArrayList<DashboardButton> btnList = new ArrayList<>();
    DashboardButton lastClicked;
    ImageView image = new ImageView(new Image("/Assets/image 222(40).png"));

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        DBConnection.getInstance().getConnection();
        new FadeIn(MainContext).setSpeed(10).play();
        new FadeIn(NavigationContext).setSpeed(5).play();

        btnList.add(new DashboardButton(btnDashboard, imgDash, new Image("/Assets/image (25).png"),95, new Image("/Assets/image (4).png")));
        btnList.add(new DashboardButton(btnCustomer, imgCus, new Image("/Assets/image (26).png"),172, new Image("/Assets/image (6).png")));
        btnList.add(new DashboardButton(btnQuote, imgQuote, new Image("/Assets/image (27).png"),243, new Image("/Assets/image (7).png")));
        btnList.add(new DashboardButton(btnOrders, imgOrder, new Image("/Assets/image (28).png"),316, new Image("/Assets/image (10).png")));
        btnList.add(new DashboardButton(btnPackages, imgPackage, new Image("/Assets/image (29).png"),389, new Image("/Assets/image (11).png")));
        btnList.add(new DashboardButton(btnItem, imgItem, new Image("/Assets/image (30).png"),468, new Image("/Assets/image (14).png")));
        btnList.add(new DashboardButton(btnDIrector, imgDirector, new Image("/Assets/image (31).png"),540, new Image("/Assets/image (15).png")));
        btnList.add(new DashboardButton(btnSupplier, imgSupply, new Image("/Assets/image (32).png"),611, new Image("/Assets/image (18).png")));
        btnList.add(new DashboardButton(btnReport, imgReport, new Image("/Assets/image (33).png"),686, new Image("/Assets/image (19).png")));
        btnList.add(new DashboardButton(btnProfit, imgProfit, new Image("/Assets/image (34).png"),756, new Image("/Assets/image (22).png")));
        btnList.add(new DashboardButton(btnSettings, imgSettings, new Image("/Assets/image (35).png"),828, new Image("/Assets/image (23).png")));
        lastClicked = btnList.get(0);

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
                    button.getView().setLayoutY(button.getImageLayoutY()-5);

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

    public void ShowPaneUI(MouseEvent mouseEvent) {

        image.setFitWidth(353);
        image.setFitHeight(213);
        image.setId("AddedImg");
        image.setOpacity(0.9);
        Object o = mouseEvent.getSource();
        if (o instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) o;
            pane.getChildren().add(image);
            new Pulse(image).play();
        }
    }

    public void HidePaneUI(MouseEvent mouseEvent) {
        Object o = mouseEvent.getSource();
        if (o instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) o;
            pane.getChildren().remove(image);
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
}
