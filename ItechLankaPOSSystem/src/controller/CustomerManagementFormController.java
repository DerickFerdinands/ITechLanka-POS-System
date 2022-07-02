package controller;

import Model.CustomerDTO;
import Util.GenerateAutoId;
import Util.NotificationUtil;
import Util.ValidationUtil;
import View.TM.CustomerTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.CustomerDAO;
import dao.Custom.impl.CustomerCRUDController;
import entity.Customer;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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

    LinkedHashMap<JFXTextField, Pattern> RegexMap = new LinkedHashMap<>();
    private final CustomerDAO customerCrudOps = new CustomerCRUDController();

    public void frequentFunctions() {
        clearAllFields();
        loadALlCustomers();

    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
        addOrUpdateCustomer();
    }

    public void addOrUpdateCustomer() {
        try {
            if (btnAddCustomer.getText().equals("Add Client")) {
                if (customerCrudOps.save(new Customer(txtId.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText()))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Added!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            } else {
                if (customerCrudOps.update(new Customer(txtId.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText()))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Updated!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
        }
    }

    public void initialize() {
        setAutoId();
        btnAddCustomer.setDisable(true);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadALlCustomers();

        RegexMap.put(txtId, Pattern.compile("^(C00-)[0-9]{1,10}$"));
        RegexMap.put(txtName, Pattern.compile("^[A-z ]+$"));
        RegexMap.put(txtNIC, Pattern.compile("^[0-9]{10,15}(V)?$"));
        RegexMap.put(txtMobile, Pattern.compile("^[+94]?[0-9]{10,11}$"));
        RegexMap.put(txtAddress, Pattern.compile("^[A-z0-9 ,/]+$"));

        txtName.requestFocus();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                CustomerTM tm = newValue;
                setUpdateFields(tm);
            }

        });
    }

    private void setAutoId() {
        txtId.setText(GenerateAutoId.autoId("SELECT Id FROM Customer ORDER BY Id DESC LIMIT 1", 1, 4, "C00-01"));
    }

    private void setUpdateFields(CustomerTM tm) {
        txtId.setEditable(false);
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtNIC.setText(tm.getNic());
        txtMobile.setText(tm.getMobile());
        txtAddress.setText(tm.getAddress());
        btnAddCustomer.setText("Update Client");
        btnAddCustomer.setDisable(false);
    }

    private void loadALlCustomers() {
        ObservableList<CustomerTM> CustomerTableList = FXCollections.observableArrayList();
        try {
            for (Customer c : customerCrudOps.getAll()) {
                CustomerTableList.add(new CustomerTM(c.getId(), c.getName(), c.getNic(), c.getMobile(), c.getAddress(), getJFXButton(c.getId())));
            }

            tblCustomer.setItems(CustomerTableList);
        } catch (Exception e) {
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
        txtName.requestFocus();
        resetFields(txtId);
        resetFields(txtName);
        resetFields(txtNIC);
        resetFields(txtMobile);
        resetFields(txtAddress);


        txtId.setEditable(true);
        btnAddCustomer.setDisable(true);
        setAutoId();
    }

    public void resetFields(JFXTextField field) {
        field.getParent().setStyle("-fx-border-color :   #EDEDF0;" + "-fx-border-width:1.5;" + "-fx-border-radius:  5;" + "-fx-background-radius:  5;");

    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void searchMatchingItems(KeyEvent keyEvent) {
        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                ArrayList<Customer> list = customerCrudOps.getMatchingResults(search);
                ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
                for (Customer c : list) {

                    obList.add(new CustomerTM(c.getId(), c.getName(), c.getNic(), c.getMobile(), c.getAddress(), getJFXButton(c.getId())));
                }
                tblCustomer.getItems().clear();
                tblCustomer.getItems().addAll(obList);
                tblCustomer.refresh();


            } catch (Exception e) {
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
                    if (customerCrudOps.delete(id)) {
                        NotificationUtil.playNotification(AnimationType.POPUP, "Client Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
                        frequentFunctions();
                        setAutoId();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.ERROR, Duration.millis(3000));
                }
            }
        });
        return btn;
    }

    public void validateFields(KeyEvent keyEvent) {
        Object o = ValidationUtil.Validate(RegexMap, btnAddCustomer);

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (o instanceof JFXTextField) {
                JFXTextField field = (JFXTextField) o;
                field.requestFocus();
            } else {
                addOrUpdateCustomer();
            }
        }
    }
}
