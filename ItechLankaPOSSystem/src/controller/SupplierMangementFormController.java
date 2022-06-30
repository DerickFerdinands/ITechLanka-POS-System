package controller;

import Model.SupplierDTO;
import Util.NotificationUtil;
import Util.ValidationUtil;
import View.TM.SupplierTM;
import bo.custom.SupplierBo;
import bo.custom.impl.SupplierBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class SupplierMangementFormController {

    private final SupplierBo SupplierBOps = new SupplierBOImpl();
    private final Alert alert = new Alert(Alert.AlertType.ERROR, "Are You Sure? ", ButtonType.YES, ButtonType.NO);
    private final LinkedHashMap<JFXTextField, Pattern> RegexMap = new LinkedHashMap();
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtAddress;
    public JFXButton btnaddClient;
    public TableView<SupplierTM> tblSupplier;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colMobile;
    public TableColumn colAddress;
    public TableColumn colOption;
    public JFXTextField txtMobile;
    public JFXTextField txtSearch;
    public JFXButton btnCancel;

    public void addClientOnAction(ActionEvent actionEvent) {
        try {
            if (btnaddClient.getText().equals("Add Supplier")) {
                if (SupplierBOps.saveSupplier(new SupplierDTO(txtId.getText(), txtName.getText(), txtEmail.getText(), txtAddress.getText(), txtMobile.getText()))) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Supplier Successfully Added!", NotificationType.SUCCESS, Duration.millis(3000));
                } else {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Something Went Wrong", NotificationType.ERROR, Duration.millis(3000));
                }
            } else {
                if (SupplierBOps.updateSupplier(new SupplierDTO(txtId.getText(), txtName.getText(), txtEmail.getText(), txtAddress.getText(), txtMobile.getText()))) {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Supplier Successfully Updated!", NotificationType.SUCCESS, Duration.millis(3000));
                } else {
                    NotificationUtil.playNotification(AnimationType.POPUP, "Something Went Wrong", NotificationType.ERROR, Duration.millis(3000));
                }
            }
            loadAllSuppliers();
            clearFormOnAction(actionEvent);
            resetFields(RegexMap);
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.ERROR, Duration.millis(3000));
        }
    }

    public void resetFields(LinkedHashMap<JFXTextField, Pattern> map) {

        for (JFXTextField txt : map.keySet()) {
            txt.getParent().setStyle("-fx-border-color :   #EDEDF0;" + "-fx-border-width:1.5;" + "-fx-border-radius:  5;" + "-fx-background-radius:  5;");
        }
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtId.setEditable(true);
        btnaddClient.setText("Add Supplier");
        tblSupplier.getSelectionModel().clearSelection();
    }

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setUpdateFields(newValue);
            }
        });
        loadAllSuppliers();
        RegexMap.put(txtId, Pattern.compile("^(S00-)[0-9]{1,10}$"));
        RegexMap.put(txtName, Pattern.compile("^[A-z ]+$"));
        RegexMap.put(txtEmail, Pattern.compile("^[A-z1-9]+@[A-z]+[.][A-z]+$"));
        RegexMap.put(txtAddress, Pattern.compile("^[A-z0-9 ,/]+$"));
        RegexMap.put(txtMobile, Pattern.compile("^[+94]?[0-9]{10,11}$"));
    }

    private void setUpdateFields(SupplierTM tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtEmail.setText(tm.getEmail());
        txtAddress.setText(tm.getAddress());
        txtMobile.setText(tm.getMobile());
        txtId.setEditable(false);
        btnaddClient.setText("Update Supplier");
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
        try {
            for (SupplierDTO s : SupplierBOps.getAllSuppliers()) {
                obList.add(new SupplierTM(s.getId(), s.getName(), s.getEmail(), s.getAddress(), s.getMobile(), getDeleteButton(s.getId())));
            }
            tblSupplier.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JFXButton getDeleteButton(String SupplierID) {
        JFXButton deleteButton = new JFXButton("Delete");
        deleteButton.setStyle("-fx-border-color: Black");
        deleteButton.setOnAction(event -> {
            alert.showAndWait();
            ButtonType btnTyep = alert.getResult();
            if (btnTyep.equals(ButtonType.YES)) {
                try {
                    if (SupplierBOps.deleteSupplier(SupplierID)) {
                        loadAllSuppliers();
                        NotificationUtil.playNotification(AnimationType.POPUP, "Supplier Successfully Deleted", NotificationType.SUCCESS, Duration.millis(3000));
                    } else {
                        NotificationUtil.playNotification(AnimationType.POPUP, "Something Went Wrong!", NotificationType.ERROR, Duration.millis(3000));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return deleteButton;
    }

    public void validateFields(KeyEvent keyEvent) {
        Object validate = ValidationUtil.Validate(RegexMap, btnaddClient);
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (validate instanceof JFXTextField) {
                ((JFXTextField) validate).requestFocus();
            } else {
                btnaddClient.fire();
            }
        }
    }

    public void searchMatchingOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<SupplierDTO> matchingSupplierDTOS = SupplierBOps.getMatchingSuppliers("%" + txtSearch.getText() + "%");
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

            for(SupplierDTO s: matchingSupplierDTOS){
                obList.add(new SupplierTM(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile(),getDeleteButton(s.getId())));
            }

            tblSupplier.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
