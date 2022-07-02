package controller;

import Model.OrderDTO;
import Util.NotificationUtil;
import View.TM.OrderHistoryTM;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import bo.BOFactory;
import bo.custom.OrderHistoryBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

public class OrderHistoryFormController {
    public TableView<OrderHistoryTM> tblOrders;
    public TableColumn colQuotationID;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableColumn colTotal;
    public TableColumn colAdvancedAmount;
    public TableColumn colBalance;
    public TableColumn colStatus;
    public TableColumn colUpdateOption;
    public TableColumn colBillOption;
    public JFXTextField txtSearch;
    public AnchorPane updateOrderPane;
    public JFXTextField txtPayedAmt;
    public JFXCheckBox chbxPaidInFull;
    public JFXButton btnUpdatePayment;
    public JFXButton btnCancel;
    public Label lblOrderID;

    private final OrderHistoryBO ohBO = (OrderHistoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_HISTORY);

    public void searchMatchingOrders(ActionEvent actionEvent) {
    }

    public void initialize() {
        colQuotationID.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAdvancedAmount.setCellValueFactory(new PropertyValueFactory<>("advancedAmount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUpdateOption.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));
        colBillOption.setCellValueFactory(new PropertyValueFactory<>("billBtn"));
        loadAllOrders();
    }

    private void loadAllOrders() {
        try {
            tblOrders.getItems().clear();
            tblOrders.getItems().addAll(ohBO.getAll().stream().map(orderDTO -> {
                Customer c = orderDTO.getCustomer();
                return new OrderHistoryTM(orderDTO.getId(), c.getName(), c.getAddress(), BigDecimal.valueOf(orderDTO.getTotal()), BigDecimal.valueOf(orderDTO.getPayedAmount()), BigDecimal.valueOf(orderDTO.getTotal() - orderDTO.getPayedAmount()), orderDTO.getPaymentStatus(), getUpdateButton(orderDTO.getPaymentStatus(), orderDTO.getId()), getBillButton(orderDTO.getPaymentStatus(), orderDTO.getId()));
            }).collect(Collectors.toList()));

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).show();
        }
    }

    private JFXButton getUpdateButton(String status, String OrderID) {
        JFXButton btn = new JFXButton("Update");
        btn.setStyle("-fx-background-color: Black;" + "-fx-text-fill: White");
        btn.setOnAction(event -> {
            updateOrderPane.setVisible(true);
            new FadeIn(updateOrderPane).setSpeed(5).play();
            lblOrderID.setText(OrderID);
        });
        if (status.equals("Payed")) btn.setDisable(true);
        lblOrderID.setText(OrderID);
        return btn;
    }

    private JFXButton getBillButton(String status, String OrderID) {

        JFXButton btn = new JFXButton("Receipt");
        btn.setStyle("-fx-background-color: Black;" + "-fx-text-fill: White");
        btn.setOnAction(event -> {
            try {
                OrderDTO o = ohBO.getOrder(OrderID);
                if (status.equals("Payed")) {
                    HashMap params = new HashMap();
                    params.put("OrderId", o.getId());
                    params.put("Customer Name", o.getCustomer().getName());
                    params.put("Contact", o.getCustomer().getMobile());
                    params.put("Date", Date.valueOf(o.getDate()));
                    params.put("Total", o.getTotal() + "");

                    JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/Reciept.jasper"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                }else{
                    HashMap params = new HashMap();
                    params.put("OrderId", o.getId());
                    params.put("Customer Name",o.getCustomer().getName());
                    params.put("Contact",o.getCustomer().getMobile());
                    params.put("Date", Date.valueOf(o.getDate()));
                    params.put("Total",o.getTotal()+"");
                    params.put("Advance",o.getPayedAmount()+"");
                    params.put("Balance",o.getTotal()-o.getPayedAmount()+"");

                    JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/Quotation.jasper"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).show();
            }

        });
        return btn;
    }

    public void fireUpdatePayment(ActionEvent actionEvent) {
        btnUpdatePayment.fire();
    }

    public void setEditableFalse(ActionEvent actionEvent) {
        txtPayedAmt.setEditable(!chbxPaidInFull.isSelected());
    }

    public void updatePaymentOnACtion(ActionEvent actionEvent) {
        try {
            System.out.println(lblOrderID.getText());
            OrderDTO order = ohBO.getOrder(lblOrderID.getText());
            if ((chbxPaidInFull.isSelected()) || (Double.valueOf(txtPayedAmt.getText()) + order.getPayedAmount() >= order.getTotal())) {
                order.setPaymentStatus("Payed");
                order.setPayedAmount(order.getTotal());
                System.out.println(order);
                if (ohBO.updateOrder(order)) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Order Updated Successfully!", NotificationType.SUCCESS, Duration.millis(3000));
                    btnCancel.fire();
                    loadAllOrders();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Went Wrong!", ButtonType.OK).show();
                }
            } else {
                order.setPayedAmount(order.getPayedAmount() + Double.valueOf(txtPayedAmt.getText()));
                if (ohBO.updateOrder(order)) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Order Updated Successfully!", NotificationType.SUCCESS, Duration.millis(3000));
                    btnCancel.fire();
                    loadAllOrders();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Went Wrong!", ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).show();
        }
        txtPayedAmt.clear();
    }


    public void hidePaneOnAction(ActionEvent actionEvent) {
        new FadeOut(updateOrderPane).play();
        updateOrderPane.setVisible(false);
    }
}
