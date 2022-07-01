package controller;

import Model.ItemDTO;
import Model.OrderDTO;
import Model.PurchaseDTO;
import Model.SupplierDTO;
import Util.NotificationUtil;
import View.TM.CartTM;
import View.TM.PurchaseTM;
import bo.BOFactory;
import bo.custom.PurchaseManagementBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.Item;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PurchaseManagementFormController {
    public JFXTextField txtPurchaseID;
    public JFXTextField txtSupplierName;
    public JFXTextField txtItemName;
    public JFXTextField txtBuyingPrice;
    public JFXButton btnAddPurchase;
    public JFXButton btnCancel;
    public JFXTextField txtSupplierMobile;
    public TableView<PurchaseTM> colPurchases;
    public TableColumn colProductID;
    public TableColumn colProductName;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colCost;
    public TableColumn colOption;
    public JFXTextField txtSearch;
    public JFXComboBox<String> cmbSupplierID;
    public JFXComboBox<String> cmbProductID;
    public JFXTextField txtQty;
    public JFXTextField txtCost;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public JFXButton btnAddItem;
    public JFXButton btnRemoveItem;
    public Label lblTotal;

    private PurchaseManagementBO pBO = (PurchaseManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHASE);

    public void addPurchaseOnAction(ActionEvent actionEvent) {
        if (cmbSupplierID.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Supplier Not Selected", ButtonType.OK).show();
        } else if (colPurchases.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Cart Is Empty", ButtonType.OK).show();
        } else {
            try {
                String[] split = cmbSupplierID.getValue().split(" - ");
                PurchaseDTO p = new PurchaseDTO(pBO.getPurchaseID(), pBO.getSupplier(split[0]), calculateTotal(),LocalDate.now());
                if (pBO.addPurchase(p, colPurchases.getItems())) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Purchase Placed Successfully!", NotificationType.SUCCESS, Duration.millis(3000));
                    btnCancel.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
        cmbProductID.getSelectionModel().clearSelection();
        txtItemName.clear();
        txtBuyingPrice.clear();
        txtQty.clear();
        txtCost.clear();
        cmbSupplierID.getSelectionModel().clearSelection();
        txtSupplierName.clear();
        try {
            txtPurchaseID.setText(pBO.getPurchaseID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        colPurchases.getItems().clear();
        colPurchases.refresh();
        txtDate.setText(String.valueOf(LocalDate.now()));
        lblTotal.setText("");
    }

    public void addItemTOCartOnAction(ActionEvent actionEvent) {
        if (cmbProductID.getValue() != null) {
            try {

                ItemDTO item = pBO.getItem(cmbProductID.getValue().split(" - ")[0]);
                int qty = Integer.valueOf(txtQty.getText());
                if (colPurchases.getItems().stream().anyMatch(purchaseTM -> {
                    return purchaseTM.getItemCode().equals(item.getCode());
                })) {
                    PurchaseTM tm = colPurchases.getItems().stream().filter(cartTM -> {
                        return cartTM.getItemCode().equals(item.getCode());
                    }).collect(Collectors.toList()).get(0);
                    int tempQty = tm.getQty() + qty;
                    tm.setUnitPrice(BigDecimal.valueOf(Double.valueOf(txtBuyingPrice.getText())));
                    tm.setQty(tempQty);
                    tm.setCost(tm.getUnitPrice().multiply(BigDecimal.valueOf(tm.getQty())));
                    colPurchases.refresh();

                } else {
                    BigDecimal buyingPrice = BigDecimal.valueOf(Double.parseDouble(txtBuyingPrice.getText()));
                    colPurchases.getItems().add(new PurchaseTM(item.getCode(), item.getName(), qty, buyingPrice, buyingPrice.multiply(BigDecimal.valueOf(qty)), getButton(item.getCode())));

                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
            calculateTotal();
            txtQty.clear();
            txtBuyingPrice.clear();
            txtCost.clear();
        }
    }

    public void RemoveItemOnAction(ActionEvent actionEvent) {
        if (cmbProductID.getValue() != null) {
            try {
                ItemDTO item = pBO.getItem(cmbProductID.getValue().split(" - ")[0]);
                if (colPurchases.getItems().stream().anyMatch(cartTM -> {
                    return cartTM.getItemCode().equals(item.getCode());
                })) {
                    colPurchases.getItems().remove(colPurchases.getItems().stream().filter(o -> {
                        return o.getItemCode().equals(item.getCode());
                    }).collect(Collectors.toList()).get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JFXButton getButton(String code) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-background-color: Black;"+"-fx-text-fill: White");
        btn.setOnAction(event -> {
            colPurchases.getItems().remove(colPurchases.getItems().stream().filter(o -> {
                return o.getItemCode().equals(code);
            }).collect(Collectors.toList()).get(0));
        });

        return btn;
    }

    private double calculateTotal() {
        double total = colPurchases.getItems().stream().mapToDouble(purchaseTM -> Double.valueOf(purchaseTM.getCost() + "") ).sum();
        lblTotal.setText(total + "");
        return total;
    }

    public void initialize() {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        txtDate.setText(LocalDate.now()+"");

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime now = LocalTime.now();
            txtTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
        }),new KeyFrame(Duration.millis(1000)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        try {
            txtPurchaseID.setText(pBO.getPurchaseID());
            cmbProductID.getItems().addAll(pBO.getItemsForComboBox());
            cmbSupplierID.getItems().addAll(pBO.getSuppliersForComboBox());
            
            cmbProductID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=null) {
                    try {
                        setProductFields(pBO.getItem(newValue.split(" - ")[0]));
                        txtBuyingPrice.requestFocus();
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
                    }
                }
            });
            
            cmbSupplierID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=null) {
                    try {
                        setSupplierFields(pBO.getSupplier(newValue.split(" - ")[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING,e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setSupplierFields(SupplierDTO supplier) {
        txtSupplierName.setText(supplier.getName());
    }

    private void setProductFields(ItemDTO item) {
        txtItemName.setText(item.getName());
    }

    public void calculateCost(KeyEvent keyEvent) {
        try{
            if((!txtBuyingPrice.getText().isEmpty())&&(!txtQty.getText().isEmpty())){
                txtCost.setText(Integer.valueOf(txtQty.getText())*Double.valueOf(txtBuyingPrice.getText())+"");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            txtBuyingPrice.clear();
            txtQty.clear();
            txtCost.clear();
            txtBuyingPrice.requestFocus();
        }
    }

    public void focusOnQty(ActionEvent actionEvent) {
        txtQty.requestFocus();
    }

    public void fireAddToCart(ActionEvent actionEvent) {
        btnAddItem.fire();
    }
}
