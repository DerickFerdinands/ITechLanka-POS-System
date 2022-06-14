package controller;

import Model.Customer;
import Model.Item;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.CustomerDAO;
import dao.Custom.ItemDAO;
import dao.Custom.impl.CustomerCRUDController;
import dao.Custom.impl.ItemCRUDController;
import javafx.collections.FXCollections;

import java.sql.SQLException;
import java.util.HashMap;

public class QuotationManagementFormController {
    private final CustomerDAO cDAO = new CustomerCRUDController();
    private final ItemDAO iDAO = new ItemCRUDController();
    public JFXTextField txtQuotationID;
    public JFXComboBox<String> cmbCustomerName;
    public JFXTextField txtAddress;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXTextField txtTotal;
    public JFXTextField txtAdvance;
    public JFXTextField txtBalance;

    public void initialize() {
        try {

            cmbCustomerName.setItems(FXCollections.observableArrayList(cDAO.getALlCustomerNICAndNames()));
            cmbItemCode.setItems(FXCollections.observableArrayList(iDAO.getAllItemCodesAndNames()));
            cmbCustomerName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setCustomerFields(newValue);
                }
            });
            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    setItemFields(newValue);
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemFields(String code) {
        try {
            String[] split = code.split(" - ");
            Item item = iDAO.get(split[0]);
            txtQtyOnHand.setText(item.getQty()+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerFields(String id) {

        try {
            String[] split = id.split(" - ");
            Customer customer = cDAO.get(split[1]);
            if (customer != null) {
                txtAddress.setText(customer.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
