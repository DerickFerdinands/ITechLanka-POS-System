package controller;

import View.TM.DirectorTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DirectorManagementFormController {
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

    public void AddDirectorOnAction(ActionEvent actionEvent) {
    }

    public void clearAllFieldsOnAction(ActionEvent actionEvent) {
    }

    public void initialize(){
    colID.setCellValueFactory(new PropertyValueFactory<>("id"));
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    colProfit.setCellValueFactory(new PropertyValueFactory<>("profitMargin"));
    colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

    loadAllDirectors();
    }

    private void loadAllDirectors() {

    }
}
