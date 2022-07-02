package controller;

import Model.DirectorDTO;
import Util.NotificationUtil;
import Util.ValidationUtil;
import View.TM.DirectorTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.DirectorDAO;
import dao.Custom.impl.DirectorCRUDController;
import entity.Director;
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

public class DirectorManagementFormController {
    private final LinkedHashMap<JFXTextField, Pattern> RegexMap = new LinkedHashMap<>();
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtMobile;
    public JFXButton btnAddDir;
    public JFXTextField txtAddress;
    public JFXTextField txtMargin;
    public TableView<DirectorTM> tblDirec;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colMobile;
    public TableColumn colAddress;
    public TableColumn colProfit;
    public TableColumn colOption;
    public JFXTextField txtSearch;

    private final DirectorDAO DirectorCrudOps = new DirectorCRUDController();

    public void AddDirectorOnAction(ActionEvent actionEvent) {
        addOrUpdateDirector();
    }

    public void addOrUpdateDirector() {
        try {
            if (btnAddDir.getText().equals("Add Director")) {
                if (DirectorCrudOps.save(new Director(txtID.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText(), Double.valueOf(txtMargin.getText())))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Director Successfully Added!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            } else {
                if (DirectorCrudOps.update(new Director(txtID.getText(), txtName.getText(), txtNIC.getText(), txtMobile.getText(), txtAddress.getText(), Double.valueOf(txtMargin.getText())))) {
                    frequentFunctions();
                    NotificationUtil.playNotification(AnimationType.POPUP, "Director Successfully Updated!", NotificationType.SUCCESS, Duration.millis(3000));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.SUCCESS, Duration.millis(3000));
        }
    }

    public void clearAllFieldsOnAction(ActionEvent actionEvent) {
        resetForm();
    }

    public void resetForm() {
        try {
            txtID.setText(DirectorCrudOps.getNextId());
        } catch (Exception e) {
         e.printStackTrace();
        }

        txtName.clear();
        txtNIC.clear();
        txtMobile.clear();
        txtAddress.clear();
        txtMargin.clear();
        btnAddDir.setText("Add Director");
        tblDirec.getSelectionModel().clearSelection();

        resetFields(txtID);
        resetFields(txtName);
        resetFields(txtNIC);
        resetFields(txtMobile);
        resetFields(txtAddress);
        resetFields(txtMargin);

        txtID.setEditable(false);
        btnAddDir.setDisable(true);
    }

    private void resetFields(JFXTextField field) {
        field.getParent().setStyle("-fx-border-color :   #EDEDF0;" + "-fx-border-width:1.5;" + "-fx-border-radius:  5;" + "-fx-background-radius:  5;");
    }

    public void initialize() {
        btnAddDir.setDisable(true);
        txtID.setEditable(false);
        try {
            txtID.setText(DirectorCrudOps.getNextId());
        } catch (Exception e) {
           e.printStackTrace();
        }
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("profitMargin"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllDirectors();

        RegexMap.put(txtID, Pattern.compile("^(D00-)[0-9]{1,10}$"));
        RegexMap.put(txtName, Pattern.compile("^[A-z ]+$"));
        RegexMap.put(txtNIC, Pattern.compile("^[0-9]{10,15}(V)?$"));
        RegexMap.put(txtMobile, Pattern.compile("^[+94]?[0-9]{10,11}$"));
        RegexMap.put(txtAddress, Pattern.compile("^[A-z0-9 ,/]+$"));
        RegexMap.put(txtMargin, Pattern.compile("^[1-9][0-9]?$|^100$"));

        txtID.requestFocus();

        tblDirec.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                DirectorTM tm = newValue;
                setUpdateFields(tm);
            }

        });
    }

    private void setUpdateFields(DirectorTM tm) {
        txtID.setEditable(false);
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtNIC.setText(tm.getNic());
        txtMobile.setText(tm.getMobile());
        txtAddress.setText(tm.getAddress());
        txtMargin.setText(String.valueOf(tm.getProfitMargin()));
        btnAddDir.setText("Update Director");
        btnAddDir.setDisable(false);
    }

    private void loadAllDirectors() {
        try {
            ArrayList<Director> dirList = DirectorCrudOps.getAll();
            ObservableList<DirectorTM> obList = FXCollections.observableArrayList();

            for (Director d : dirList) {
                obList.add(new DirectorTM(d.getId(), d.getName(), d.getNic(), d.getMobile(), d.getAddress(), d.getProfitMargin(), getJFXButton(d.getId())));
            }

            tblDirec.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
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
                    if (DirectorCrudOps.delete(id)) {
                        NotificationUtil.playNotification(AnimationType.POPUP, "Director Successfully Deleted!", NotificationType.SUCCESS, Duration.millis(3000));
                        frequentFunctions();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    NotificationUtil.playNotification(AnimationType.POPUP, e.getMessage(), NotificationType.ERROR, Duration.millis(3000));
                }
            }
        });
        return btn;
    }

    private void frequentFunctions() {
        loadAllDirectors();
        resetForm();
    }

    public void validateFields(KeyEvent keyEvent) {
        Object o = ValidationUtil.Validate(RegexMap, btnAddDir);

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (o instanceof JFXTextField) {
                JFXTextField field = (JFXTextField) o;
                field.requestFocus();
            } else {
                addOrUpdateDirector();
            }
        }
    }

    public void searchDirectorsOnAction(ActionEvent actionEvent) {
        String search = "%" + txtSearch.getText() + "%";

        try {
            ArrayList<Director> list = DirectorCrudOps.getMatchingResults(search);
            ObservableList<DirectorTM> obList = FXCollections.observableArrayList();
            for (Director d : list) {

                obList.add(new DirectorTM(d.getId(), d.getName(), d.getNic(), d.getMobile(), d.getAddress(), d.getProfitMargin(), getJFXButton(d.getId())));
            }
            tblDirec.getItems().clear();
            tblDirec.getItems().addAll(obList);
            tblDirec.refresh();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
