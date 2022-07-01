package controller;

import Model.CustomerDTO;
import Model.ItemDTO;
import Model.OrderDTO;
import Util.NotificationUtil;
import View.TM.CartTM;
import bo.BOFactory;
import bo.custom.QuotationManagementBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.CustomerDAO;
import dao.Custom.ItemDAO;
import dao.Custom.impl.CustomerCRUDController;
import dao.Custom.impl.ItemCRUDController;
import db.DBConnection;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
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

    private final QuotationManagementBO qBO = (QuotationManagementBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.QUOTATION);
    public JFXTextField txtUnitPrice;

    public void initialize() {
        colCOde.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        try {
            txtQuotationID.setText(qBO.getOrderId());
            cmbCustomerName.setItems(FXCollections.observableArrayList(cDAO.getALlCustomerNICAndNames()));
            cmbItemCode.setItems(FXCollections.observableArrayList(iDAO.getAllItemCodesAndNames()));
            cmbCustomerName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setCustomerFields(newValue);
                }
            });
            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null)
                    setItemFields(newValue);
                txtQty.requestFocus();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemFields(String code) {
        try {
            String[] split = code.split(" - ");
            Item item = iDAO.get(split[0]);
            itemimageview.setImage(new Image("file:" + item.getImageLocation()));
            txtQtyOnHand.setText(item.getQty() + "");
            txtUnitPrice.setText(item.getSellingPrice()+"");
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addQuotationOnAction(ActionEvent actionEvent) {
        if (cmbCustomerName.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Customer Not Selected", ButtonType.OK).show();
        } else if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Cart Is Empty", ButtonType.OK).show();
        } else {
            try {
                String[] split = cmbCustomerName.getValue().split(" - ");
                OrderDTO o = new OrderDTO(qBO.getOrderId(), cDAO.get(split[1]), LocalDate.now(), calculateTotal(), "Quoted", Double.valueOf(txtAdvance.getText()));
                if (qBO.addOrder(o, tblCart.getItems())) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Quotation Placed Successfully!", NotificationType.SUCCESS, Duration.millis(3000));
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
                    btnCancel.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if (cmbItemCode.getValue() != null) {
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
            calculateTotal();
            txtQty.clear();
        }
    }

    private JFXButton getButton(String itemCode) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-background-color: Black;"+"-fx-text-fill: White");
        btn.setOnAction(event -> {
            tblCart.getItems().remove(tblCart.getItems().stream().filter(o -> {
                return o.getItemCode().equals(itemCode);
            }).collect(Collectors.toList()).get(0));
        });

        return btn;
    }

    public void removeFromCartOnAction(ActionEvent actionEvent) {
        if (cmbItemCode.getValue() != null) {
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
    }

    public void buyNowOnAction(ActionEvent actionEvent) {
        if (cmbCustomerName.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Customer Not Selected", ButtonType.OK).show();
        } else if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Cart Is Empty", ButtonType.OK).show();
        } else {
            try {
                String[] split = cmbCustomerName.getValue().split(" - ");
                OrderDTO o = new OrderDTO(qBO.getOrderId(), cDAO.get(split[1]), LocalDate.now(), calculateTotal(), "Payed", calculateTotal());
                if (qBO.addOrder(o, tblCart.getItems())) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Order Placed Successfully!", NotificationType.SUCCESS, Duration.millis(3000));
                    HashMap params = new HashMap();
                    params.put("OrderId", o.getId());
                    params.put("Customer Name",o.getCustomer().getName());
                    params.put("Contact",o.getCustomer().getMobile());
                    params.put("Date", Date.valueOf(o.getDate()));
                    params.put("Total",o.getTotal()+"");

                    JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/Reciept.jasper"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jasperPrint,false);
                    btnCancel.fire();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private double calculateTotal() {
        double total = tblCart.getItems().stream().mapToDouble(cartTM -> Double.valueOf(cartTM.getTotal() + "") ).sum();
        lblTotal.setText(total + "");
        txtTotal.setText(total + "");
        if (rdbxQuotation.isSelected()) {
            if ((!txtAdvance.getText().isEmpty()) && (!tblCart.getItems().isEmpty())) {
                try {
                    txtBalance.setText(total - Double.valueOf(txtAdvance.getText()) + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    txtAdvance.clear();
                    new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).show();
                }
            }else if((txtAdvance.getText().isEmpty()) && (!tblCart.getItems().isEmpty())){
                txtBalance.setText(total+"");
            }
        }
        return total;
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
        cmbCustomerName.getSelectionModel().clearSelection();
        txtAddress.clear();
        rdbxBuyNow.setSelected(false);
        rdbxQuotation.setSelected(false);
        txtTotal.clear();
        txtAdvance.clear();
        txtBalance.clear();
        txtUnitPrice.clear();
        btnAddQuotation.setDisable(true);
        btnBuyNow.setDisable(true);
        cmbItemCode.getSelectionModel().clearSelection();
        txtQtyOnHand.clear();
        try {
            txtQuotationID.setText(qBO.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemimageview.setImage(new Image("Assets/Screenshot 2022-04-28 145657.jpg"));
        tblCart.getItems().clear();
        tblCart.refresh();
        lblTotal.setText("");
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

    public void calculateBalance(KeyEvent keyEvent) {
        calculateTotal();
    }

    public void fireAddToCartButton(ActionEvent actionEvent) {
        btnAddToCart.fire();
    }
}
