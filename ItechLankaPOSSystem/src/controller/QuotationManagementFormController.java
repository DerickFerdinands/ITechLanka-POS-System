package controller;

import Model.CustomerDTO;
import Model.ItemDTO;
import View.TM.CartTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.CustomerDAO;
import dao.Custom.ItemDAO;
import dao.Custom.impl.CustomerCRUDController;
import dao.Custom.impl.ItemCRUDController;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static dao.Custom.ItemVIewPane.getButton;

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
    public ImageView itemimageview;
    public TableView<CartTM> tblCart;
    public TableColumn colCOde;
    public TableColumn colQtyOnHand;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colOption;
    public JFXButton btnAddQuotation;
    public JFXButton btnAddToCart;
    public JFXButton btnRemoveFromCart;
    public JFXButton btnBuyNow;
    public JFXButton btnCancel;
    public ToggleGroup grouped;
    public Label lblTotal;
    public JFXRadioButton rdbxBuyNow;
    public JFXRadioButton rdbxQuotation;

    public void initialize() {
        colCOde.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
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
            itemimageview.setImage(new Image("file:" + item.getImageLocation()));
            txtQtyOnHand.setText(item.getQty() + "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerFields(String id) {

        try {
            String[] split = id.split(" - ");
            Customer customerDTO = cDAO.get(split[1]);
            txtAddress.setText(customerDTO.getAddress());
            if (customerDTO != null) {
                txtAddress.setText(customerDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addQuotationOnAction(ActionEvent actionEvent) {
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        try {
            String[] split = cmbItemCode.getValue().split(" - ");
            Item item = iDAO.get(split[0]);
            int qty = Integer.valueOf(txtQty.getText());
            if (tblCart.getItems().stream().anyMatch(cartTM -> {
                return cartTM.getItemCode().equals(item.getCode());
            })) {
                CartTM tm = tblCart.getItems().stream().filter(cartTM -> {
                    return cartTM.getItemCode().equals(item.getCode());
                }).collect(Collectors.toList()).get(0);
                int tempQty = tm.getQty() + qty;
                if (tempQty <= item.getQty()) {
                    tm.setQty(tempQty);
                    tblCart.refresh();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Insufficient Qty! ", ButtonType.OK).show();
                }
            } else {
                if (qty <= item.getQty())
                    tblCart.getItems().add(new CartTM(item.getCode(), item.getQty(), qty, item.getSellingPrice(), item.getSellingPrice().multiply(BigDecimal.valueOf(qty)), getButton(item.getCode())));
                else new Alert(Alert.AlertType.WARNING, "Insufficient Qty! ", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    private JFXButton getButton(String itemCode) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-background-color: Black");
        btn.setOnAction(event -> {
            tblCart.getItems().remove(tblCart.getItems().stream().filter(o -> {
                return o.getItemCode().equals(itemCode);
            }).collect(Collectors.toList()).get(0));
        });

        return btn;
    }

    public void removeFromCartOnAction(ActionEvent actionEvent) {
        try {
            String[] split = cmbItemCode.getValue().split(" - ");
            Item item = iDAO.get(split[0]);
            System.out.println(item);
            if (tblCart.getItems().stream().anyMatch(cartTM -> {
                return cartTM.getItemCode().equals(item.getCode());
            })) {
                System.out.println("true");
                tblCart.getItems().remove(tblCart.getItems().stream().filter(o -> {
                    return o.getItemCode().equals(item.getCode());
                }).collect(Collectors.toList()).get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyNowOnAction(ActionEvent actionEvent) {
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
        cmbCustomerName.getSelectionModel().clearSelection();
        txtAddress.clear();
        rdbxBuyNow.setSelected(false);
        rdbxQuotation.setSelected(false);
        txtTotal.clear();
        txtAdvance.clear();
        txtBalance.clear();
        btnAddQuotation.setDisable(true);
        btnBuyNow.setDisable(true);
        cmbItemCode.getSelectionModel().clearSelection();
        txtQtyOnHand.clear();

    }

    public void enableBuyNowFields(ActionEvent actionEvent) {
        btnBuyNow.setDisable(false);
        btnAddQuotation.setDisable(true);
        txtAdvance.setEditable(false);
    }

    public void enableQuotationFields(ActionEvent actionEvent) {
        btnBuyNow.setDisable(true);
        btnAddQuotation.setDisable(false);
        txtAdvance.requestFocus();
        txtAdvance.setEditable(true);
    }
}
