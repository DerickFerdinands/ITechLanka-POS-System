package controller;

import Model.Customer;
import Util.NotificationUtil;
import View.TM.CustomerTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.CustomerCRUDController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtMobile;
    public JFXTextField txtAddress;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colMobile;
    public TableColumn colAddress;
    public TableColumn colOption;
    public JFXButton btnAddCustomer;
    public JFXTextField txtSearch;

    public void frequentFunctions() {
        clearAllFields();
        loadALlCustomers();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
        try {
            if (btnAddCustomer.getText().equals("Add Client")) {
                if (CustomerCRUDController.saveCustomer(new Customer(txtId.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText()))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Added!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            } else {
                if (CustomerCRUDController.updateCustomer(new Customer(txtId.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText()))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Updated!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
        }
    }

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadALlCustomers();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                CustomerTM tm = newValue;
                setUpdateFields(tm);
            }

        });
    }

    private void setUpdateFields(CustomerTM tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtNIC.setText(tm.getNic());
        txtMobile.setText(tm.getMobile());
        txtAddress.setText(tm.getAddress());
        btnAddCustomer.setText("Update Client");


    }

    private void loadALlCustomers() {
        ObservableList<CustomerTM> CustomerTableList = FXCollections.observableArrayList();
        try {
            for (Customer c : CustomerCRUDController.getAllCustomers()) {
                JFXButton btn = new JFXButton("Delete");
                btn.setStyle("-fx-border-color: #003171");
                btn.setCursor(Cursor.HAND);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    ButtonType buttonType = alert.getResult();
                    if (buttonType.equals(ButtonType.YES)) {
                        try {
                            if (CustomerCRUDController.deleteCustomer(c.getId())) {
                                NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
                                frequentFunctions();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                            NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.ERROR, Duration.millis(3000));
                        }
                    }
                });
                CustomerTableList.add(new CustomerTM(c.getId(), c.getName(), c.getNic(), c.getMobile(), c.getAddress(), btn));
            }

            tblCustomer.setItems(CustomerTableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearAllFields() {
        txtId.clear();
        txtName.clear();
        txtNIC.clear();
        txtMobile.clear();
        txtAddress.clear();
        btnAddCustomer.setText("Add Client");
        tblCustomer.getSelectionModel().clearSelection();
    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void searchMatchingItems(KeyEvent keyEvent) {
        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                ArrayList<Customer> list = CustomerCRUDController.getMatching(search);
                ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
                for (Customer c : list) {

                    obList.add(new CustomerTM(c.getId(), c.getName(), c.getNic(), c.getMobile(), c.getAddress(), getJFXButton(c.getId())));
                }
                tblCustomer.getItems().clear();
                tblCustomer.getItems().addAll(obList);
                tblCustomer.refresh();


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public JFXButton getJFXButton(String id) {
        JFXButton btn = new JFXButton("Delete");
        btn.setStyle("-fx-border-color: #003171");
        btn.setCursor(Cursor.HAND);
        btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType.equals(ButtonType.YES)) {
                try {
                    if (CustomerCRUDController.deleteCustomer(id)) {
                        NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
                        frequentFunctions();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.ERROR, Duration.millis(3000));
                }
            }
        });
        return btn;
    }
}
