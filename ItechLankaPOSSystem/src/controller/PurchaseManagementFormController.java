package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PurchaseManagementFormController {
    public JFXTextField txtPurchaseID;
    public JFXTextField txtSupplierName;
    public JFXTextField txtItemName;
    public JFXTextField txtBuyingPrice;
    public JFXButton btnAddPurchase;
    public JFXButton btnCancel;
    public JFXTextField txtSupplierMobile;
    public TableView colPurchases;
    public TableColumn colPurchaseID;
    public TableColumn colProductID;
    public TableColumn colProductName;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colCost;
    public TableColumn colOption;
    public JFXTextField txtSearch;
    public JFXComboBox cmbSupplierID;
    public JFXComboBox cmbProductID;
    public JFXTextField txtQty;
    public JFXTextField txtCost;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public JFXButton btnAddItem;
    public JFXButton btnRemoveItem;

    public void addPurchaseOnAction(ActionEvent actionEvent) {
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
    }

    public void addItemTOCartOnAction(ActionEvent actionEvent) {
    }

    public void RemoveItemOnAction(ActionEvent actionEvent) {
    }
}
